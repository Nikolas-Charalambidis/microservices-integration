package cz.vse.chan01.mi.api.notification.email;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import cz.vse.chan01.swagger.notification.model.Notification;

@Service
public class Producer {

	AtomicInteger atomicInteger = new AtomicInteger();

	private static final Logger logger = LoggerFactory.getLogger(Producer.class);
	private static final String TOPIC = "document-topic";

	@Autowired
	private KafkaTemplate<String, Notification> kafkaTemplate;

	public void sendMessage(Notification notification) {
		UUID uuid = UUID.randomUUID();
		notification.setNotificationId(uuid.toString());
		Message<Notification> message = new GenericMessage<>(notification, Map.of(
			KafkaHeaders.CORRELATION_ID, uuid.toString(),
			KafkaHeaders.TOPIC, TOPIC
		));

		logger.info(String.format("#### -> Producing message -> %s", notification.getNotificationId()));

		ListenableFuture<SendResult<String, Notification>> future = this.kafkaTemplate.send(message);
		future.addCallback(new ListenableFutureCallback<>() {
			@Override
			public void onFailure(final Throwable ex) {
				logger.error(String.format("#### -> Failed -> %s", notification.getNotificationId()));
			}

			@Override
			public void onSuccess(final SendResult<String, Notification> result) {
				logger.info(String.format("#### -> Succeed -> %s", notification.getNotificationId()));
			}
		});
	}
}