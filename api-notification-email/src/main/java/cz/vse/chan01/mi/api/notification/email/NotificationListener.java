package cz.vse.chan01.mi.api.notification.email;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.AcknowledgingMessageListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.DefaultKafkaHeaderMapper;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.stereotype.Component;

import cz.vse.chan01.swagger.notification.model.Notification;
import cz.vse.chan01.swagger.notification.model.NotificationType;
import cz.vse.chan01.swagger.notification.model.NotificationType.TypeEnum;

@Component
public class NotificationListener implements AcknowledgingMessageListener<String, Notification> {

	private static final Logger LOGGER = LoggerFactory.getLogger(NotificationListener.class);

	private final DefaultKafkaHeaderMapper kafkaHeaderMapper;

	public NotificationListener(final DefaultKafkaHeaderMapper kafkaHeaderMapper) {
		this.kafkaHeaderMapper = kafkaHeaderMapper;
	}

	@KafkaListener(topics = "document-topic")
	public void onMessage(final ConsumerRecord<String, Notification> record, final Acknowledgment acknowledgment) {

		final Notification payload = record.value();
		final Map<String, Object> customHeaders = new HashMap<>();
		kafkaHeaderMapper.toHeaders(record.headers(), customHeaders);

		final String correlationId = (String) customHeaders.get(KafkaHeaders.CORRELATION_ID);
		final String topic = record.topic();

		final List<String> emails = payload.getNotificationType().stream()
			.filter(notificationType -> TypeEnum.EMAIL.equals(notificationType.getType()))
			.map(NotificationType::getValue)
			.collect(Collectors.toList());

		LOGGER.info("[{}] Requested Email Notification with id={} from {}, received {} emails", correlationId, payload.getNotificationId(), topic, emails.size());

		this.sendNotification(payload.getNotificationId(), emails);

		acknowledgment.acknowledge();
	}

	private void sendNotification(final String notificationId, @SuppressWarnings("unused") final List<String> emails) {
		for (String email: emails) {
			try {
				// IMPLEMENTATION OF NOTIFICATION SENDING THROUGH EMAIL GATEWAY
				Thread.sleep(1000);
				LOGGER.info("Sending email to {} from notification {} succeed", email, notificationId);
			} catch (InterruptedException e) {
				LOGGER.error("Sending email to {} from notification {} failed", email, notificationId);
			}
		}
	}
}
