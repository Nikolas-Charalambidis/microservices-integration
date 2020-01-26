package cz.vse.chan01.mi.api.customer;

import java.util.List;

import cz.vse.swagger.customer.model.Customer;

public interface CustomerService {

	List<Customer> customers();

	Customer customer(long customerId);
}
