package cz.vse.chan01.mi.api.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FileSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileSpringApplication.class, args);
	}
}