package cz.vse.chan01.mi.api.contract;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import cz.vse.chan01.mi.api.contract.service.ContractService;
import cz.vse.chan01.mi.api.contract.service.DocumentServiceImpl;
import cz.vse.chan01.swagger.contract.api.ContractApi;
import cz.vse.chan01.swagger.contract.model.Contract;
import cz.vse.chan01.swagger.document.model.Document;

@RestController("/")
public class ContractController implements ContractApi {

	private static final Logger LOGGER = LoggerFactory.getLogger(ContractController.class);

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

	@GetMapping("foo")
	public ResponseEntity<List<Document>> documents(@RequestParam Long id) {
		return ResponseEntity.ok(this.documentService.findCustomerDocuments(id));
	}

	@Override
	public ResponseEntity<Void> postContract(Contract contract) {
		long id = this.contractService.contract(contract);
		final URI location = ServletUriComponentsBuilder
			.fromCurrentRequest().path("/{id}")
			.buildAndExpand(id).toUri();
		LOGGER.info(String.format("Created a new contract {id: %s}, available at: %s", id, location));
		return ResponseEntity.created(location).build();
	}
}
