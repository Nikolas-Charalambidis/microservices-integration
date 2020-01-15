package cz.vse.chan01.mi.api.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class NotificationSpringAplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationSpringAplication.class, args);
	}
}