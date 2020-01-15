package cz.vse.chan01.mi.api.customer;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import cz.vse.chan01.mi.model.customer.Customer;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

	private static final List<Customer> customerDatabase = Arrays.asList(
		new Customer(1, "First", "First", LocalDate.of(1987, 11, 2), "M", null),
		new Customer(2, "Second", "Second", LocalDate.of(1987, 11, 2), "M", null),
		new Customer(3, "Third", "Third", LocalDate.of(1987, 11, 2), "M", null)
	);

	@Override
	public List<Customer> customers() {
		return customerDatabase;
	}

	@Override
	public List<Customer> customers(final long customerId) {
		return customerDatabase.stream().filter(customer -> customer.getCustomerId() == customerId).collect(Collectors.toList());
	}
}
