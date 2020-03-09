package cz.vse.chan01.mi.api.document.service;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import cz.vse.chan01.swagger.customer.model.Customer;
import cz.vse.chan01.swagger.document.model.Document;
import cz.vse.chan01.swagger.notification.model.Notification;
import cz.vse.chan01.swagger.notification.model.Notification.StatusEnum;
import cz.vse.chan01.swagger.notification.model.NotificationType;
import cz.vse.chan01.swagger.notification.model.NotificationType.TypeEnum;

@Service
public class NotificationServiceImpl implements NotificationService {

	private static final Logger LOGGER = LoggerFactory.getLogger(NotificationServiceImpl.class);

	@Value("${spring.kafka.template.default-topic}")
	private String topic;

	private final CustomerFeignClient customerFeignClient;

	private final KafkaTemplate<String, Notification> kafkaTemplate;

	public NotificationServiceImpl(final CustomerFeignClient customerFeignClient, final KafkaTemplate<String, Notification> kafkaTemplate) {
		this.customerFeignClient = customerFeignClient;
		this.kafkaTemplate = kafkaTemplate;
	}

	@Override
	public final void sendNotification(final Document document) {
		final UUID uuid = UUID.randomUUID();
		Notification notification = new Notification();

		final Customer customer = this.customerFeignClient.customer(document.getCustomerId());

		if (customer.getEmail() != null) {
			NotificationType notificationType = new NotificationType();
			notificationType.setType(TypeEnum.EMAIL);
			notificationType.setValue(customer.getEmail());
			notification.addNotificationTypeItem(notificationType);
		}

		if (customer.getPhone() != null) {
			NotificationType notificationType = new NotificationType();
			notificationType.setType(TypeEnum.SMS);
			notificationType.setValue(customer.getPhone());
			notification.addNotificationTypeItem(notificationType);
		}

		notification.setNotificationId(uuid.toString());
		notification.setStatus(StatusEnum.REQUESTED);
		notification.setCreationDate(LocalDate.now());
		notification.setLabel(document.getName());
		notification.setMessage(String.format("Dear customer, the document %s was created.", document.getDocumentId()));
		notification.setLabel(document.getDocumentId());

		final Message<Notification> message = new GenericMessage<>(notification, Map.of(
			KafkaHeaders.CORRELATION_ID, uuid.toString(),
			KafkaHeaders.TOPIC, topic
		));

		LOGGER.info("[{}] Requested sending Notification with id={}", uuid, notification.getNotificationId());

		final ListenableFuture<SendResult<String, Notification>> future = this.kafkaTemplate.send(message);
		future.addCallback(new ListenableFutureCallback<>() {
			@Override
			public void onFailure(final Throwable throwable) {
				LOGGER.error("[{}] Requesting Notification with id={} from Document with id={} is not confirmed. Exception: {}",
					uuid, notification.getNotificationId(), document.getDocumentId(), throwable.getMessage());
			}

			@Override
			public void onSuccess(final SendResult<String, Notification> result) {
				LOGGER.info("[{}] Requesting Notification from Document with id={} succeed",
					uuid, document.getDocumentId());
			}
		});
	}
}
