package cz.vse.chan01.mi.api.notification;

import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import cz.vse.chan01.mi.model.file.File;

@Configuration
public class RedisConfiguration {

	private static final Logger LOGGER = LoggerFactory.getLogger(RedisConfiguration.class);

	@Autowired
	private DiscoveryClient discoveryClient;

	@Autowired
	private Environment env;

	@Value("${sidecar.appName:eureka-sidecar-redis}")
	private String dbServiceName;

	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		final RedisStandaloneConfiguration redisStandaloneConfiguration;

		final Iterator<ServiceInstance> iterator = this.discoveryClient.getInstances(this.dbServiceName).iterator();
		if (iterator.hasNext()) {
			final ServiceInstance serviceInstance = iterator.next();
			LOGGER.info(serviceInstance.getScheme());
			LOGGER.info(serviceInstance.getMetadata().toString());
			redisStandaloneConfiguration = new RedisStandaloneConfiguration(
				serviceInstance.getHost(),
				serviceInstance.getPort());
			LOGGER.info("Redis instance discovered");
		} else {
			redisStandaloneConfiguration = new RedisStandaloneConfiguration(
				env.getProperty("spring.redis.host"),
				env.getProperty("spring.redis.port", Integer.class));
			LOGGER.info("Redis instance configured from properties");
		}

		LOGGER.info("Connected to Redis instance {}:{}",
			redisStandaloneConfiguration.getHostName(), redisStandaloneConfiguration.getPort());

		return new JedisConnectionFactory(redisStandaloneConfiguration);
	}

	@Bean
	ChannelTopic topic() {
		return new ChannelTopic("files");
	}

	@Bean
	public RedisTemplate<String, File> redisTemplate() {
		final RedisTemplate<String, File> template = new RedisTemplate<>();
		template.setConnectionFactory(jedisConnectionFactory());
		template.setValueSerializer(new Jackson2JsonRedisSerializer<File>(File.class));
		return template;
	}
}

