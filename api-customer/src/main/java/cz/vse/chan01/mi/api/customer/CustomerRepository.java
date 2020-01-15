package cz.vse.chan01.mi.api.customer;

import java.util.List;

import cz.vse.chan01.mi.model.customer.Customer;

public interface CustomerRepository {

	List<Customer> customers();

	List<Customer> customers(long customerId);
}
