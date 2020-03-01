package cz.vse.chan01.mi.api.document;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;

import cz.vse.chan01.mi.api.document.service.DocumentService;
import cz.vse.chan01.swagger.contract.model.Contract;
import cz.vse.chan01.swagger.document.model.Document;

@Component
public class DocumentListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(DocumentListener.class);

	private final DocumentService documentService;

	public DocumentListener(final DocumentService documentService) {
		this.documentService = documentService;
	}

	@RabbitListener(queues = "document-queue.contract")
	public Document createDocument(
		Contract contract,
		Channel channel,
		@Header(AmqpHeaders.DELIVERY_TAG) long tag,
		@Header(AmqpHeaders.RECEIVED_EXCHANGE) String exchange,
		@Header(AmqpHeaders.RECEIVED_ROUTING_KEY) String routingKey,
		@Header(AmqpHeaders.CORRELATION_ID) String correlationId) throws IOException
	{

		LOGGER.info("[{}] Requested creation of new Document from Contract with id={}", correlationId, contract.getContractId());
		final Document document =  this.documentService.document(contract);
		channel.basicAck(tag, false);
		LOGGER.info("[{}] Created a new Document with id={}", correlationId, document.getDocumentId());
		return document;
	}

	@RabbitListener(queues = "document-queue.document")
	public List<Document> readDocument(
		Long contractId,
		Channel channel,
		@Header(AmqpHeaders.DELIVERY_TAG) long tag,
		@Header(AmqpHeaders.RECEIVED_EXCHANGE) String exchange,
		@Header(AmqpHeaders.RECEIVED_ROUTING_KEY) String routingKey,
		@Header(AmqpHeaders.CORRELATION_ID) String correlationId) throws IOException
	{
		LOGGER.info("[{}] Requested List<Document> belonging to Contract with id={}", correlationId, contractId);
		List<Document> documents =  this.documentService.documents(Optional.of(contractId), Optional.empty());
		channel.basicAck(tag, false);
		LOGGER.info("[{}] Returning {} entities", correlationId, documents.size());
		return documents;
	}
}
