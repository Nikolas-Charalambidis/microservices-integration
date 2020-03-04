package cz.vse.chan01.mi.api.notification.email;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.client.discovery.DiscoveryClient;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.listener.ChannelTopic;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//
//import cz.vse.chan01.swagger.document.model.Document;

//@Configuration
public class RedisConfiguration {
//
	//private static final Logger LOGGER = LoggerFactory.getLogger(RedisConfiguration.class);
//
	//@Autowired
	//private DiscoveryClient discoveryClient;
//
	//@Autowired
	//private Environment env;
//
	//@Value("${sidecar.app-name:eureka-sidecar-redis}")
	//private String dbServiceName;

	//@Bean
	//JedisConnectionFactory jedisConnectionFactory() {


		//final Iterator<ServiceInstance> iterator = this.discoveryClient.getInstances(this.dbServiceName).iterator();
		//if (iterator.hasNext()) {
		//	final ServiceInstance serviceInstance = iterator.next();
		//	redisStandaloneConfiguration = new RedisStandaloneConfiguration(
		//		serviceInstance.getHost(),
		//		serviceInstance.getPort());
		//	LOGGER.info("Redis instance discovered");
		//}

	//	LOGGER.info("Configuring Redis connection from properties");
//
	//	final RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(
	//		env.getProperty("spring.redis.host"),
	//		env.getProperty("spring.redis.port", Integer.class));
	//	final JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(configuration);
//
	//	LOGGER.info("Configured Redis connection at {}:{}", configuration.getHostName(), configuration.getPort());
//
	//	return jedisConnectionFactory;
	//}

	//@Bean
	//ChannelTopic topic() {
	//	return new ChannelTopic("files");
	//}

	//@Bean
	//public RedisTemplate<String, Document> redisTemplate() {
	//	final RedisTemplate<String, Document> template = new RedisTemplate<>();
	//	template.setConnectionFactory(jedisConnectionFactory());
	//	template.setValueSerializer(new Jackson2JsonRedisSerializer<>(Document.class));
	//	return template;
	//}
}
