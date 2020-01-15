package cz.vse.chan01.mi.api.contract;

import java.io.IOException;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import cz.vse.chan01.mi.model.customer.Customer;

@FeignClient("api-customer")
public interface CustomerFeignService {

	@GetMapping("/customer")
	List<Customer> customers() throws IOException;

	@GetMapping("/customer/{customerId}")
	List<Customer> customer(@PathVariable long customerId) throws IOException;
}
