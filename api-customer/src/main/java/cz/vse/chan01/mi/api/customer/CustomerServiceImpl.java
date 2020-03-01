package cz.vse.chan01.mi.api.customer;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cz.vse.chan01.swagger.customer.model.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);

	private final CustomerJpaRepository customerJpaRepository;

	private final ModelMapper modelMapper;

	@Autowired
	public CustomerServiceImpl(final CustomerJpaRepository customerJpaRepository, final ModelMapper modelMapper) {
		this.customerJpaRepository = customerJpaRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public List<Customer> customers() {
		final List<Customer> customers =  this.customerJpaRepository.findAll().stream()
			.map(ce -> modelMapper.map(ce, Customer.class))
			.collect(Collectors.toList());
		LOGGER.info("Returning List<Customer> with {} entities", customers.size());
		return customers;
	}

	@Override
	public Customer customer(final long customerId) {
		final Customer customer = this.customerJpaRepository.findById(customerId)
			.map(ce -> modelMapper.map(ce, Customer.class))
			.orElseThrow(() -> new EntityNotFoundException(String.format("Customer {id: %s} not found", customerId)));
		LOGGER.info("Returning Customer with id={}", customer.getCustomerId());
		return customer;
	}
}
