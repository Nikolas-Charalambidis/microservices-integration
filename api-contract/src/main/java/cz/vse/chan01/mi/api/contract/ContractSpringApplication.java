package cz.vse.chan01.mi.api.contract;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class ContractSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContractSpringApplication.class, args);
	}

	@Bean
	public DirectExchange exchange() {
		return new DirectExchange("file-exchange");
	}
}