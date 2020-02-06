package cz.vse.chan01.mi.api.contract.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import cz.vse.chan01.swagger.customer.model.Customer;

@FeignClient(name = "api-customer", fallback = CustomerFeignClientFallback.class)
public interface CustomerFeignClient {

	@GetMapping("/customer")
	List<Customer> customers();

	@GetMapping("/customer/{customerId}")
	Customer customer(@PathVariable long customerId);
}
