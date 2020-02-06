package cz.vse.chan01.mi.api.contract;

import java.net.URI;
import java.util.List;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import cz.vse.chan01.mi.api.contract.service.ContractService;
import cz.vse.chan01.swagger.contract.api.ContractApi;
import cz.vse.chan01.swagger.contract.model.Contract;

@RestController("/")
public class ContractController implements ContractApi {

	@Value("${eureka.instance.hostname}")
	private String hostname;

	@Autowired
	private ContractService contractService;

	@Autowired
	private Environment environment;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private RabbitTemplate template;

	@Autowired
	private DirectExchange exchange;

	@Override
	public ResponseEntity<List<Contract>> contracts() {
		return ResponseEntity.ok(this.contractService.contracts());
		//return new ResponseEntity<>(this.customerFeignService.customers(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Contract> contractById(@PathVariable Long contractId) {
		return ResponseEntity.ok(this.contractService.contract(contractId));
		//return new ResponseEntity<>(this.customerFeignService.customers(), HttpStatus.OK);
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

	//@PostMapping("/contract")
	//public ResponseEntity<File> publish(@RequestBody Customer contract) throws IOException {
	//	final List<Customer> customer = this.customerFeignService.customer(2);
	//	contract.setContractId(customer.get(0).getCustomerId());
	//	System.out.println(" [x] Requesting fib(" + contract + ")");
	//	File file = (File) template.convertSendAndReceive(exchange.getName(), "post", contract);
	//	System.out.println(" [.] Got '" + file + "'");
	//	return new ResponseEntity<>(file, HttpStatus.OK);
	//}

}
