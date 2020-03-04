package cz.vse.chan01.mi.api.notification.email;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;

import cz.vse.chan01.swagger.notification.model.Notification;

@Service
public class Producer {

	AtomicInteger atomicInteger = new AtomicInteger();

	private static final Logger logger = LoggerFactory.getLogger(Producer.class);
	private static final String TOPIC = "document-topic";

	@Autowired
	private KafkaTemplate<String, Notification> kafkaTemplate;

	public void sendMessage(String message) {
		int key = atomicInteger.getAndIncrement();
		Notification notification = new Notification();
		notification.setNotificationId(String.valueOf(key));
		logger.info(String.format("#### -> Producing message -> %s", notification.getNotificationId()));
		this.kafkaTemplate.send(TOPIC, notification);
	}
}