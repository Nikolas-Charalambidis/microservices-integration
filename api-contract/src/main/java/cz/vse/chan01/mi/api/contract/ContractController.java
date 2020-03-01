package cz.vse.chan01.mi.api.contract;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import cz.vse.chan01.mi.api.contract.service.ContractService;
import cz.vse.chan01.mi.api.contract.service.DocumentServiceImpl;
import cz.vse.chan01.swagger.contract.api.ContractApi;
import cz.vse.chan01.swagger.contract.model.Contract;

@RestController("/")
public class ContractController implements ContractApi {

	@Value("${eureka.instance.hostname}")
	private String hostname;

	private final ContractService contractService;

	private final DocumentServiceImpl documentService;

	public ContractController(final ContractService contractService,
		final DocumentServiceImpl documentService) {
		this.contractService = contractService;
		this.documentService = documentService;
	}

	@Override
	public ResponseEntity<List<Contract>> contracts() {
		return ResponseEntity.ok(this.contractService.contracts());
	}

	@Override
	public ResponseEntity<Contract> contractById(@PathVariable Long contractId) {
		return ResponseEntity.ok(this.contractService.contract(contractId));
	}

	@Override
	public ResponseEntity<List<cz.vse.chan01.swagger.contract.model.Document>> documentByContractId(Long contractId) {
		return ResponseEntity.ok(this.documentService.documentsByContractId(contractId));
	}

	@Override
	public ResponseEntity<Void> postContract(Contract contract) {
		long id = this.contractService.contract(contract);
		final URI location = ServletUriComponentsBuilder
			.fromCurrentRequest().path("/{id}")
			.buildAndExpand(id).toUri();
		return ResponseEntity.created(location).build();
	}
}
