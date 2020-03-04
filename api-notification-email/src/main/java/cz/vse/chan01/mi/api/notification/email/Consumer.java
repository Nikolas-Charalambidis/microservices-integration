package cz.vse.chan01.mi.api.notification.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import cz.vse.chan01.swagger.notification.model.Notification;

@Service
public class Consumer {

	private final Logger logger = LoggerFactory.getLogger(Producer.class);

	@KafkaListener(
		topics = "document-topic"
		//,
		//id="notification-email",
		//topicPartitions = {
		//	@TopicPartition(
		//		topic = "document-topic",
		//		partitions = { "0", "2" }
		//	)
		//}
	)
	public void consume(@Payload Notification message) {
		logger.info(String.format("#### -> Consumed message -> %s", message.getNotificationId()));
	}

}
