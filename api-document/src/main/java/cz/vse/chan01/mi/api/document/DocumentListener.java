package cz.vse.chan01.mi.api.document;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
		LOGGER.info(String.format("[%s] Received message of %s class from exchange %s using routing key %s, tag: %s",
			correlationId, contract.getClass().getName(), exchange, routingKey, tag));
		channel.basicAck(tag, false);
		return this.documentService.document(contract);
	}

	@RabbitListener(queues = "document-queue.document")
	public List<Document> readDocument(
		Long caseid,
		Channel channel,
		@Header(AmqpHeaders.DELIVERY_TAG) long tag,
		@Header(AmqpHeaders.RECEIVED_EXCHANGE) String exchange,
		@Header(AmqpHeaders.RECEIVED_ROUTING_KEY) String routingKey,
		@Header(AmqpHeaders.CORRELATION_ID) String correlationId) throws IOException
	{
		LOGGER.info(String.format("[%s] Received message of %s class from exchange %s using routing key %s, tag: %s, message: %s",
			correlationId, caseid.getClass().getName(), exchange, routingKey, tag, caseid));
		channel.basicAck(tag, false);
		return this.documentService.documents(Optional.of(caseid), Optional.empty());
	}
	/*
	@RabbitListener(queues = "file-queue.post")
	public Document createFile(
		@Payload Contract contract,
		@Header(AmqpHeaders.CHANNEL) Channel channel,
		@Header(AmqpHeaders.DELIVERY_TAG) Long tag,
		@Header(AmqpHeaders.CORRELATION_ID) String correlationId,
		Message message
	) throws InterruptedException, IOException {

		System.out.println("Channel: " + channel.getChannelNumber() + " deliveryTag: " + tag + "=: " + message.getMessageProperties().getDeliveryTag());
		StopWatch watch = new StopWatch();
		watch.start();
		System.out.println(" [localhost:" + environment.getProperty("server.port") +"] CorrelationId: " + correlationId +  " Received " + contract);
		Random random = new Random();
		Thread.sleep(5000 + random.nextInt(2000));

		System.out.println(" [x] Received request for " + contract);
		Document file = null;
		System.out.println(" [.] Returned " + file);

		try {
			channel.basicAck(tag, false);
			System.out.println("Acknowledged");
		} catch (Exception e) {
			channel.basicNack(tag, false, true);
			System.out.println("NOT-Acknowledged");
		}

		watch.stop();
		System.out.println(" [localhost:" + environment.getProperty("server.port") +"][" + watch.getTotalTimeMillis() + "ms] CorrelationId: " + correlationId + " Done " + file);
		return file;
	}
	*/
}
