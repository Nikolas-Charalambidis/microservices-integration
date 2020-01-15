package cz.vse.chan01.mi.api.customer;

import java.util.List;

import org.springframework.stereotype.Service;

import cz.vse.chan01.mi.model.customer.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	private final CustomerRepository customerRepository;

	public CustomerServiceImpl(final CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	public List<Customer> customers() {
		return this.customerRepository.customers();
	}

	@Override
	public List<Customer> customers(final long customerId) {
		return this.customerRepository.customers(customerId);
	}
}
