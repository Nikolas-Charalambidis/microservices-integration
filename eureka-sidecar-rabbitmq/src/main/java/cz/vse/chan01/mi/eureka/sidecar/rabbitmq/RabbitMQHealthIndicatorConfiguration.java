package cz.vse.chan01.mi.eureka.sidecar.rabbitmq;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.amqp.RabbitHealthIndicator;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQHealthIndicatorConfiguration {

	@Value("${sidecar.hostname}")
	private String rabbitmqHostname;

	@Value("${sidecar.port}")
	private int rabbitmqPort;

	@Value("${sidecar.username}")
	private String rabbitmqUsername;

	@Value("${sidecar.password}")
	private String rabbitmqPassword;

	@Value("${sidecar.timeout.close}")
	private int closeTimeout;

	@Value("${sidecar.timeout.connection}")
	private int connectionTimeout;

	@Bean
	RabbitTemplate rabbitTemplate() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setHost(rabbitmqHostname);
		connectionFactory.setPort(rabbitmqPort);
		connectionFactory.setUsername(rabbitmqUsername);
		connectionFactory.setPassword(rabbitmqPassword);
		connectionFactory.setCloseTimeout(closeTimeout);
		connectionFactory.setConnectionTimeout(connectionTimeout);
		return new RabbitTemplate(connectionFactory);
	}

	@Bean
	HealthIndicator rabbitmqHealthIndicator(final RabbitTemplate rabbitTemplate) {
		return new RabbitHealthIndicator(rabbitTemplate);
	}
}
