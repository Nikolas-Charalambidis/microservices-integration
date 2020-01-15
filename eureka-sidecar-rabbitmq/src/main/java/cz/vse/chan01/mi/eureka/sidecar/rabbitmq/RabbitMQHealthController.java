package cz.vse.chan01.mi.eureka.sidecar.rabbitmq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitMQHealthController {

	private final HealthIndicator healthIndicator;

	@Autowired
	public RabbitMQHealthController(@Qualifier("rabbitmqHealthIndicator") final HealthIndicator healthIndicator) {
		this.healthIndicator = healthIndicator;
	}

	@GetMapping(name = "/rabbitmq/health", produces = MediaType.APPLICATION_JSON_VALUE)
	public Health sidecarHealthStatus() {
		return this.healthIndicator.health();
	}
}
