package cz.vse.chan01.mi.api.notification.sms;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;


import org.springframework.kafka.support.serializer.JsonDeserializer;

import cz.vse.chan01.swagger.notification.model.Notification;

@Configuration
public class KafkaConfiguration {

	@Value("${spring.kafka.consumer.bootstrap-servers}")
	private String bootstrapServers;

	@Bean
	public ConsumerFactory<String, Notification> consumerFactory() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "notification-sms");
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		return new DefaultKafkaConsumerFactory<>(
			props,
			new StringDeserializer(),
			new JsonDeserializer<>(Notification.class));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Notification> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, Notification> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}

	//@Bean
	//public Map<String, Object> consumerConfigs() {
	//	Map<String, Object> props = new HashMap<>();
	//	props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
	//	props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
	//	props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
	//	props.put(ConsumerConfig.GROUP_ID_CONFIG, "notification-sms");
	//	props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
	//	return props;
	//}
	//@Bean
	//public ConsumerFactory<String, Notification> consumerFactory() {
	//	return new DefaultKafkaConsumerFactory<String, Notification>(
	//		consumerConfigs(),
	//		new StringDeserializer(),
	//		new JsonDeserializer<>(Notification.class));
	//}
	//@Bean(name = "kafkaListenerContainerFactory")
	//public ConcurrentKafkaListenerContainerFactory<String, Notification> kafkaListenerContainerFactory() {
	//	ConcurrentKafkaListenerContainerFactory<String, Notification> factory =
	//		new ConcurrentKafkaListenerContainerFactory<>();
	//	factory.setConsumerFactory(consumerFactory());
	//	return factory;
	//}
}