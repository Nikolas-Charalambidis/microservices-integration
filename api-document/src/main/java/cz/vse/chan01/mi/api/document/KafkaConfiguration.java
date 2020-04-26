package cz.vse.chan01.mi.api.document;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.fasterxml.jackson.databind.ObjectMapper;

import cz.vse.chan01.swagger.notification.model.Notification;

@Configuration
public class KafkaConfiguration {

	@Value("${spring.kafka.producer.bootstrap-servers}")
	private String bootstrapServers;

	@Value("${spring.kafka.template.default-topic}")
	private String topic;

	@Autowired
	private ObjectMapper objectMapper;

	@Bean
	public KafkaAdmin admin() {
		Map<String, Object> configs = new HashMap<>();
		configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		return new KafkaAdmin(configs);
	}

	@Bean
	public NewTopic documentTopic() {
		return new NewTopic(topic, 3, (short) 1);
	}

	@Bean
	public NewTopic documentDltTopic() {
		return new NewTopic(topic + ".DLT", 3, (short) 1);
	}

	@Bean
	public ProducerFactory<String, Notification> producerFactory() {
		Map<String, Object> configProps = new HashMap<>();
		configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		return new DefaultKafkaProducerFactory<>(configProps, new StringSerializer(), new JsonSerializer<>(this.objectMapper));
	}

	@Bean
	public KafkaTemplate<String, Notification> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}
}
