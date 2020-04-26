package cz.vse.chan01.mi.api.notification.sms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.DefaultKafkaHeaderMapper;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.stereotype.Service;

import cz.vse.chan01.swagger.notification.model.Notification;
import cz.vse.chan01.swagger.notification.model.NotificationType;
import cz.vse.chan01.swagger.notification.model.NotificationType.TypeEnum;

@Service
public class NotificationListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(NotificationListener.class);

	private final DefaultKafkaHeaderMapper kafkaHeaderMapper;

	public NotificationListener(final DefaultKafkaHeaderMapper kafkaHeaderMapper) {
		this.kafkaHeaderMapper = kafkaHeaderMapper;
	}

	@KafkaListener(topics = "document-topic")
	public void onMessage(final ConsumerRecord<String, Notification> record) {

		final Notification payload = record.value();

		final Map<String, Object> customHeaders = new HashMap<>();
		kafkaHeaderMapper.toHeaders(record.headers(), customHeaders);
		final String correlationId = (String) customHeaders.get(KafkaHeaders.CORRELATION_ID);
		final String topic = record.topic();

		final List<String> phoneList = payload.getNotificationType().stream()
			.filter(notificationType -> TypeEnum.SMS.equals(notificationType.getType()))
			.map(NotificationType::getValue)
			.collect(Collectors.toList());

		LOGGER.info("[{}] Requested SMS Notification with id={} from {}, received {} phone numbers", correlationId, payload.getNotificationId(), topic, phoneList.size());

		if (payload.getLabel() == null || payload.getLabel().isBlank()) {
			throw new SmsException(String.format("[%s] The SMS notification is not supposed to be sent if the label is either blank or null.", correlationId));
		}

		this.sendNotification(payload.getNotificationId(), phoneList);
	}


	@KafkaListener(topics = "document-topic.DLT")
	public void onDltMessage(final ConsumerRecord<String, Object> record) {
		Object payload = record.value();

		final Map<String, Object> customHeaders = new HashMap<>();
		kafkaHeaderMapper.toHeaders(record.headers(), customHeaders);
		final String correlationId = (String) customHeaders.get(KafkaHeaders.CORRELATION_ID);

		if (payload instanceof Notification) {
			LOGGER.warn("[{}] DLT: The SMS notification is not supposed to be sent if the label is either blank or null.", correlationId);}
		else {
			LOGGER.error("[{}] DLT: Received invalid or malformed notification: {}" , correlationId, payload);
		}
	}

	private void sendNotification(final String notificationId, @SuppressWarnings("unused") final List<String> phoneList) {
		for (String phone: phoneList) {
			try {
				// IMPLEMENTATION OF NOTIFICATION SENDING THROUGH SMS GATEWAY
				Thread.sleep(1000);
				LOGGER.info("Sending SMS to {} from notification {} succeed", phone, notificationId);
			} catch (InterruptedException e) {
				LOGGER.error("Sending SMS to {} from notification {} failed", phone, notificationId);
			}
		}
	}
}