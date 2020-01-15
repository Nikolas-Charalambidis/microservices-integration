package cz.vse.chan01.mi.api.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import cz.vse.chan01.mi.model.customer.Customer;

@RestController("/")
public class CustomerController {

	private final CustomerService customerService;

	@Autowired
	public CustomerController(final CustomerService customerService) {
		this.customerService = customerService;
	}

	@GetMapping("/customer")
	public ResponseEntity<List<Customer>> customers() {
		return new ResponseEntity<>(this.customerService.customers(), HttpStatus.OK);
	}

	@GetMapping("/customer/{customerId}")
	public ResponseEntity<List<Customer>> customers(@PathVariable long customerId) {
		return new ResponseEntity<>(this.customerService.customers(customerId), HttpStatus.OK);
	}
}
