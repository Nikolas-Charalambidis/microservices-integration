package cz.vse.chan01.mi.api.contract.service;

import java.util.List;

import org.springframework.stereotype.Component;

import cz.vse.chan01.mi.api.contract.exception.FeignClientException;
import cz.vse.chan01.swagger.customer.model.Customer;

@Component
public class CustomerFeignClientFallback implements CustomerFeignClient {

	@Override
	public List<Customer> customers() {
		throw new FeignClientException("The /customer endpoint is not available");
	}

	@Override
	public Customer customer(final long customerId) {
		// Provide a fake object to be completed later
		// Send asynchronous notification to customer service operators for assistance
		final Customer customer = new Customer();
		customer.setCustomerId(customerId);
		customer.setName("-");
		customer.setSurname("-");
		customer.setEmail("-");
		return customer;
	}
}
