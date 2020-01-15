package cz.vse.chan01.mi.eureka.sidecar.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisHealthController {

	private final HealthIndicator healthIndicator;

	@Autowired
	public RedisHealthController(@Qualifier("redisHealthIndicator") final HealthIndicator healthIndicator) {
		this.healthIndicator = healthIndicator;
	}

	@GetMapping(name = "/redis/health", produces = MediaType.APPLICATION_JSON_VALUE)
	public Health sidecarHealthStatus() {
		return this.healthIndicator.health();
	}
}