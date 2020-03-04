package cz.vse.chan01.mi.api.notification.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import cz.vse.chan01.swagger.notification.model.Notification;

@Service
public class Consumer {

	private final Logger logger = LoggerFactory.getLogger(Consumer.class);

	@KafkaListener(topics = "document-topic")
	public void consume(@Payload Notification message) {
		logger.info(String.format("#### -> Consumed message -> %s", message.getNotificationId()));
	}
}