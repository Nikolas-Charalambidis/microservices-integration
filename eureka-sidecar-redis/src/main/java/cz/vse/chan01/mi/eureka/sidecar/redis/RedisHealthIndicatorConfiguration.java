package cz.vse.chan01.mi.eureka.sidecar.redis;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.redis.RedisHealthIndicator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import io.lettuce.core.ClientOptions;
import io.lettuce.core.SocketOptions;

@Configuration
public class RedisHealthIndicatorConfiguration {

	@Value("${sidecar.hostname}")
	private String redisHostname;

	@Value("${sidecar.port}")
	private int redisPort;

	@Value("${sidecar.timeout.socket}")
	private int socketTimeout;

	@Value("${sidecar.timeout.shutdown}")
	private int shutdownTimeout;

	@Value("${sidecar.timeout.command}")
	private int commandTimeout;

	@Bean
	RedisConnectionFactory redisConnectionFactory() {
		final RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(this.redisHostname, this.redisPort);
		final SocketOptions socketOptions = SocketOptions.builder().connectTimeout(Duration.ofMillis(socketTimeout)).build();
		final LettuceClientConfiguration clientConfiguration = LettuceClientConfiguration.builder()
			.shutdownTimeout(Duration.ofMillis(shutdownTimeout))
			.commandTimeout(Duration.ofMillis(commandTimeout))
			.clientOptions(ClientOptions.builder().socketOptions(socketOptions).build())
			.build();
		return new LettuceConnectionFactory(redisStandaloneConfiguration, clientConfiguration);
	}

	@Bean
	HealthIndicator redisHealthIndicator(final RedisConnectionFactory redisConnectionFactory) {
		return new RedisHealthIndicator(redisConnectionFactory);
	}
}
