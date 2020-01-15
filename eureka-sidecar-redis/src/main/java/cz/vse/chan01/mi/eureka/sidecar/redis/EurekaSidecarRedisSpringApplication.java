package cz.vse.chan01.mi.eureka.sidecar.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.sidecar.EnableSidecar;

@SpringBootApplication
@EnableSidecar
@EnableDiscoveryClient
public class EurekaSidecarRedisSpringApplication {
	public static void main(String[] args) {
		SpringApplication.run(EurekaSidecarRedisSpringApplication.class, args);
	}
}