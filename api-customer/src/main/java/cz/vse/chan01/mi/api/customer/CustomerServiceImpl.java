package cz.vse.chan01.mi.api.customer;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cz.vse.swagger.customer.model.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	private final CustomerJpaRepository customerJpaRepository;

	private final ModelMapper modelMapper;

	@Autowired
	public CustomerServiceImpl(final CustomerJpaRepository customerJpaRepository,
		final ModelMapper modelMapper) {
		this.customerJpaRepository = customerJpaRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public List<Customer> customers() {
		return this.customerJpaRepository.findAll().stream()
			.map(ce -> modelMapper.map(ce, Customer.class))
			.collect(Collectors.toList());
	}

	@Override
	public Customer customer(final long customerId) {
		return this.customerJpaRepository.findById(customerId)
			.map(ce -> modelMapper.map(ce, Customer.class))
			.orElseThrow(() -> new EntityNotFoundException(String.format("Customer {id: %s} not found", customerId)));
	}
}
