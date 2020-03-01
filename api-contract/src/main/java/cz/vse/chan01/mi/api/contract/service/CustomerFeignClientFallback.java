package cz.vse.chan01.mi.api.contract.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import cz.vse.chan01.mi.api.contract.exception.FeignClientException;
import cz.vse.chan01.swagger.customer.model.Customer;

@Component
public class CustomerFeignClientFallback implements CustomerFeignClient {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerFeignClientFallback.class);

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
		LOGGER.info("Hystrix fallback returning fake Customer with id={}", customer.getCustomerId());
		return customer;
	}
}
