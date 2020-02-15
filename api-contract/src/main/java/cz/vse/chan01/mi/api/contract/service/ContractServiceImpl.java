package cz.vse.chan01.mi.api.contract.service;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import cz.vse.chan01.mi.api.contract.repository.ContractJpaRepository;
import cz.vse.chan01.mi.api.contract.entity.ContractEntity;
import cz.vse.chan01.mi.api.contract.entity.ContractStatus;
import cz.vse.chan01.swagger.contract.model.Contract;
import cz.vse.chan01.swagger.customer.model.Customer;

@Service
public class ContractServiceImpl implements ContractService {

	private final ContractJpaRepository contractJpaRepository;

	private final CustomerFeignClient customerFeignClient;

	private final DocumentService documentService;

	private final RestTemplate restTemplate;

	private final ModelMapper modelMapper;

	@Autowired
	public ContractServiceImpl(
		final ContractJpaRepository contractJpaRepository,
		final CustomerFeignClient customerFeignClient,
		final DocumentService documentService,
		final ModelMapper modelMapper,
		final RestTemplate restTemplate)
	{
		this.contractJpaRepository = contractJpaRepository;
		this.customerFeignClient = customerFeignClient;
		this.documentService = documentService;
		this.modelMapper = modelMapper;
		this.restTemplate = restTemplate;
	}

	@Override
	public List<Contract> contracts() {
		return this.contractJpaRepository.findAll().stream()
			.map(ce -> modelMapper.map(ce, Contract.class))
			.collect(Collectors.toList());
	}

	@Override
	public Contract contract(final long contractId) {
		return this.contractJpaRepository.findById(contractId)
			.map(ce -> modelMapper.map(ce, Contract.class))
			.orElseThrow(() -> new EntityNotFoundException(String.format("Contract {id: %s} not found", contractId)));
	}

	@Override
	public long contract(final Contract contract) {
		final Customer customer = this.customerFeignClient.customer(contract.getCustomerId());
		final ContractEntity contractEntity = this.modelMapper.map(contract, ContractEntity.class);
		contractEntity.setCustomerLabel(String.format("%s %s", customer.getName(), customer.getSurname()));
		contractEntity.setContractStatus(ContractStatus.NEW);
		final ContractEntity saved = this.contractJpaRepository.save(contractEntity);
		this.documentService.createDocument(modelMapper.map(saved, Contract.class));
		return saved.getContractId();
	}

	@SuppressWarnings("unused")
	private long contractWithRestTemplate(final Contract contract) {
		final String uri = String.format("http://eureka-zuul:8080/api-customer/customer/%d", contract.getCustomerId());
		final Customer customer = this.restTemplate.getForEntity(URI.create(uri), Customer.class)
			.getBody();
		final ContractEntity contractEntity = this.modelMapper.map(contract, ContractEntity.class);
		contractEntity.setCustomerLabel(String.format("%s %s", customer.getName(), customer.getSurname()));
		contractEntity.setContractStatus(ContractStatus.NEW);
		final ContractEntity saved = this.contractJpaRepository.save(contractEntity);
		return saved.getContractId();
	}
}

