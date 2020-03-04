package cz.vse.chan01.mi.api.notification.email;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableEurekaClient
@EnableKafka
public class NotificationEmailSpringAplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationEmailSpringAplication.class, args);
	}
}