package cz.vse.chan01.mi.api.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import cz.vse.swagger.customer.api.CustomerApi;
import cz.vse.swagger.customer.model.Customer;

@RestController("/")
public class CustomerController implements CustomerApi {

	private final CustomerService customerService;

	@Autowired
	public CustomerController(final CustomerService customerService) {
		this.customerService = customerService;
	}

	@Override
	@GetMapping("/customer")
	public ResponseEntity<List<Customer>> customers() {
		return new ResponseEntity<>(this.customerService.customers(), HttpStatus.OK);
	}

	@Override
	@GetMapping("/customer/{customerId}")
	public ResponseEntity<Customer> customerById(@PathVariable Long customerId) {
		return new ResponseEntity<>(this.customerService.customer(customerId), HttpStatus.OK);
	}
}
