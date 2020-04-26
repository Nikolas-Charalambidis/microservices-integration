package cz.vse.chan01.mi.api.document;

import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class DocumentSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(DocumentSpringApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public Queue contractQueue() {
		return QueueBuilder.durable("document-queue.contract")
			.build();
	}

	@Bean
	public Queue documentQueue() {
		return QueueBuilder.durable("document-queue.document")
			.build();
	}
}