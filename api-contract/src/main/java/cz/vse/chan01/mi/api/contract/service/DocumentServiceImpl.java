package cz.vse.chan01.mi.api.contract.service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
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

	private final ModelMapper modelMapper;

	@Value("${document-service.routing-key.contract}")
	private String keyContract;

	@Value("${document-service.routing-key.document}")
	private String keyDocument;

	public DocumentServiceImpl(
		final RabbitTemplate rabbitTemplate,
		final AsyncRabbitTemplate asyncRabbitTemplate,
		final DirectExchange exchange,
		final ModelMapper modelMapper
	) {
		this.rabbitTemplate = rabbitTemplate;
		this.asyncRabbitTemplate = asyncRabbitTemplate;
		this.exchange = exchange;
		this.modelMapper = modelMapper;
	}

	@Override
	public void document(final Contract contract) {
		final UUID uuid = UUID.randomUUID();
		LOGGER.info(String.format("[%s] Sending message of %s class to exchange: %s using routing key: %s",
			uuid, contract.getClass().getName(), exchange.getName(), keyContract));
		//rabbitTemplate.convertAndSend(exchange.getName(), routingKeyContract, contract, new UuidMessagePostProcessor(uuid));

		AsyncRabbitTemplate.RabbitConverterFuture<Document> future =
			asyncRabbitTemplate.convertSendAndReceive(exchange.getName(), keyContract, contract, new UuidMessagePostProcessor(uuid));

		future.addCallback(new ListenableFutureCallback<>() {
			@Override
			public void onFailure(Throwable throwable) {
				throwable.printStackTrace();
			}

			@Override
			public void onSuccess(Document document) {
				LOGGER.info(String.format("[%s] Received message of %s class from exchange: %s, message: %s",
					uuid, document.getClass().getName(), exchange.getName(), document));
			}
		});
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<cz.vse.chan01.swagger.contract.model.Document> documentsByCustomerId(final Long customerId) {
		final UUID uuid = UUID.randomUUID();
		LOGGER.info(String.format("[%s] Sending message of %s class to exchange: %s using routing key: %s, message: %s",
			uuid, customerId.getClass().getName(), exchange.getName(), keyDocument, customerId));
		try {
			return ((List<Document>) asyncRabbitTemplate
				.convertSendAndReceive(exchange.getName(), keyDocument, customerId, new UuidMessagePostProcessor(uuid))
				.get(5L, TimeUnit.SECONDS))
				.stream()
				.map(d -> modelMapper.map(d, cz.vse.chan01.swagger.contract.model.Document.class))
				.collect(Collectors.toList());
		} catch (InterruptedException | ExecutionException e) {
			LOGGER.error("Thread inerrupted", e);
			Thread.currentThread().interrupt();
		} catch (TimeoutException e) {
			LOGGER.error("The request timeouted", e);
		}
		return Collections.emptyList();
	}
}
