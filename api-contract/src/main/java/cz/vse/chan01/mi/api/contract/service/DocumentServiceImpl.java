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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.server.ResponseStatusException;

import cz.vse.chan01.mi.api.contract.rabbitmq.UuidMessagePostProcessor;
import cz.vse.chan01.swagger.contract.model.Contract;
import cz.vse.chan01.swagger.document.model.Document;

@Service
public class DocumentServiceImpl implements DocumentService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DocumentServiceImpl.class);

	private final AsyncRabbitTemplate asyncRabbitTemplate;

	private final DirectExchange exchange;

	private final ModelMapper modelMapper;

	@Value("${document-service.routing-key.contract}")
	private String contractRoutingKey;

	@Value("${document-service.routing-key.document}")
	private String documentRountingKey;

	public DocumentServiceImpl(
		final AsyncRabbitTemplate asyncRabbitTemplate,
		final DirectExchange exchange,
		final ModelMapper modelMapper
	) {
		this.asyncRabbitTemplate = asyncRabbitTemplate;
		this.exchange = exchange;
		this.modelMapper = modelMapper;
	}

	@Override
	public void document(final Contract contract) {
		final UUID uuid = UUID.randomUUID();
		LOGGER.info("[{}] Requested creation of new Document from Contract with id={}", uuid, contract.getContractId());
		final AsyncRabbitTemplate.RabbitConverterFuture<Document> future =
			asyncRabbitTemplate.convertSendAndReceive(exchange.getName(), contractRoutingKey, contract, new UuidMessagePostProcessor(uuid));

		future.addCallback(new ListenableFutureCallback<>() {
			@Override
			public void onFailure(Throwable throwable) {
				LOGGER.error("[{}] Creation of Document from Contract with id={} is not confirmed", uuid, contract.getContractId());
			}

			@Override
			public void onSuccess(Document document) {
				LOGGER.info("[{}] Creation of Document from Contract with id={} succeed. New Document id={}",
					uuid, contract.getContractId(), document.getDocumentId());
			}
		});
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<cz.vse.chan01.swagger.contract.model.Document> documentsByContractId(final Long contractId) {
		final UUID uuid = UUID.randomUUID();
		LOGGER.info("[{}] Requested List<Document> belonging to Contract with id={}", uuid, contractId);
		try {
			final List<cz.vse.chan01.swagger.contract.model.Document> documents = ((List<Document>) asyncRabbitTemplate
				.convertSendAndReceive(exchange.getName(), documentRountingKey, contractId, new UuidMessagePostProcessor(uuid))
				.get(5L, TimeUnit.SECONDS))
				.stream()
				.map(d -> modelMapper.map(d, cz.vse.chan01.swagger.contract.model.Document.class))
				.collect(Collectors.toList());
			LOGGER.info("[{}] Returning List<Document> with {} entities belonging to Contract with id={}", uuid, documents.size(), contractId);
			return documents;
		} catch (InterruptedException | ExecutionException e) {
			final String message = String.format("[%s] Thread interrupted upon requesting List<Document> belonging to Contract with id=%s", uuid, contractId);
			LOGGER.error(message);
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, message, e);
		} catch (TimeoutException e) {
			final String message = String.format("[%s] Request timeout upon requesting List<Document> belonging to Contract with id=%s", uuid, contractId);
			LOGGER.error(message);
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, message, e);
		}
	}
}
