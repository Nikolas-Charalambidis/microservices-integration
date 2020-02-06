package cz.vse.chan01.mi.eureka.sidecar.redis;
/*
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.redis.RedisHealthIndicator;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;

import org.springframework.boot.actuate.health.Health;

@Component
public class RedisHealthCheck implements SidecarHealthIndicator {

	Health up = Health.up().build();
	Health down = Health.up().build();

	private static final Logger LOGGER = LoggerFactory.getLogger(RedisHealthCheck.class);

	@Value("${sidecar.hostname}")
	private String redisHostname;

	@Value("${sidecar.port}")
	private int redisPort;

	@Override
	public Health health() {
		Health.Builder result = Health.unknown();
		try (JedisPool pool = new JedisPool(this.redisHostname, this.redisPort)) {
			final Jedis jedis = pool.getResource();
			if (jedis != null) {
				result = Health.up();
			}
		} catch (JedisConnectionException e) {
			result = Health.down();
		}
		final Health health = result.build();
		LOGGER.info(String.format("Redis health check %s:%s results in %s", this.redisHostname, this.redisPort, health.getStatus().getCode()));
		return result.build();
	}
}
*/