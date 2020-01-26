package cz.vse.chan01.mi.api.contract;

import java.io.IOException;
import java.util.List;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cz.vse.chan01.mi.model.contract.Contract;
import cz.vse.chan01.mi.model.file.File;
import cz.vse.swagger.customer.model.Customer;

@RestController("/")
public class ContractController {

	@Autowired
	CustomerFeignService customerFeignService;

	@Autowired
	private Environment environment;

	@Autowired
	private RabbitTemplate template;

	@Autowired
	private DirectExchange exchange;

	@GetMapping("/customer")
	public ResponseEntity<List<Customer>> customers() throws IOException {
		return new ResponseEntity<>(this.customerFeignService.customers(), HttpStatus.OK);
	}

	@PostMapping("/publish")
	public ResponseEntity<File> publish(@RequestBody Contract contract) throws IOException {
		final List<Customer> customer = this.customerFeignService.customer(2);
		contract.setContractId(customer.get(0).getCustomerId());
		System.out.println(" [x] Requesting fib(" + contract + ")");
		File file = (File) template.convertSendAndReceive(exchange.getName(), "post", contract);
		System.out.println(" [.] Got '" + file + "'");
		return new ResponseEntity<>(file, HttpStatus.OK);
	}

}
