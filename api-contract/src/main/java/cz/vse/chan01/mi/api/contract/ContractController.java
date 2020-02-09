package cz.vse.chan01.mi.api.contract;

import java.net.URI;
import java.util.List;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import cz.vse.chan01.mi.api.contract.service.ContractService;
import cz.vse.chan01.swagger.contract.api.ContractApi;
import cz.vse.chan01.swagger.contract.model.Contract;

@RestController("/")
public class ContractController implements ContractApi {

	@Value("${eureka.instance.hostname}")
	private String hostname;

	private final ContractService contractService;

	public ContractController(final ContractService contractService) {
		this.contractService = contractService;
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
	public ResponseEntity<Void> postContract(Contract contract) {
		long id = this.contractService.contract(contract);
		final URI location = ServletUriComponentsBuilder
			.fromCurrentRequest().path("/{id}")
			.buildAndExpand(id).toUri();
		System.out.println(location);
		return ResponseEntity.created(location).build();
	}
}
