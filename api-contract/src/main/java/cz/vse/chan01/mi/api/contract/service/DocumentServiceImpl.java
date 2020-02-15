package cz.vse.chan01.mi.api.contract.service;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

import cz.vse.chan01.mi.api.contract.rabbitmq.UuidMessagePostProcessor;
import cz.vse.chan01.swagger.contract.model.Contract;
import cz.vse.chan01.swagger.document.model.Document;

@Service
public class DocumentServiceImpl implements DocumentService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DocumentServiceImpl.class);

	private final RabbitTemplate rabbitTemplate;

	private final AsyncRabbitTemplate asyncRabbitTemplate;

	private final DirectExchange exchange;

	@Value("${document-service.routing-key.contract}")
	private String routingKeyContract;

	@Value("${document-service.routing-key.document}")
	private String routingKeyDocument;

	public DocumentServiceImpl(final RabbitTemplate rabbitTemplate,
		final AsyncRabbitTemplate asyncRabbitTemplate, final DirectExchange exchange) {
		this.rabbitTemplate = rabbitTemplate;
		this.asyncRabbitTemplate = asyncRabbitTemplate;
		this.exchange = exchange;
	}

	@Override
	public void createDocument(final Contract contract) {
		final UUID uuid = UUID.randomUUID();
		LOGGER.info(String.format("[%s] Sending message of %s class to exchange: %s using routing key: %s",
			uuid, contract.getClass().getName(), exchange.getName(), routingKeyContract));
		//rabbitTemplate.convertAndSend(exchange.getName(), routingKeyContract, contract, new UuidMessagePostProcessor(uuid));

		AsyncRabbitTemplate.RabbitConverterFuture<Document> future =
			asyncRabbitTemplate.convertSendAndReceive(exchange.getName(), routingKeyContract, contract, new UuidMessagePostProcessor(uuid));

		future.addCallback(new ListenableFutureCallback<>() {
			@Override
			public void onFailure(Throwable throwable) {
				throwable.printStackTrace();
			}

			@Override
			public void onSuccess(Document document) {
				LOGGER.info(String.format("[%s] Received message of %s class from exchange: %s, message: %s",
					uuid, document.getClass().getName(), exchange.getName(), routingKeyContract, document));
			}
		});
	}

	public List<Document> findCustomerDocuments(final Long customerId) {
		final UUID uuid = UUID.randomUUID();
		LOGGER.info(String.format("[%s] Sending message of %s class to exchange: %s using routing key: %s, message: %s",
			uuid, customerId.getClass().getName(), exchange.getName(), routingKeyDocument, customerId));
		return (List<Document>) rabbitTemplate.convertSendAndReceive(exchange.getName(), routingKeyDocument, customerId,
			new UuidMessagePostProcessor(uuid));
	}
}
