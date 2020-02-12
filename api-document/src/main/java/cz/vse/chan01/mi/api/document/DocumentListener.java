package cz.vse.chan01.mi.api.document;

import java.io.IOException;
import java.util.Random;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.core.env.Environment;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import com.rabbitmq.client.Channel;

import cz.vse.chan01.swagger.contract.model.Contract;
import cz.vse.chan01.swagger.document.model.Document;

@Component
public class DocumentListener {

	private final Environment environment;

	private final DocumentService documentService;

	public DocumentListener(final Environment environment,
		final DocumentService documentService) {
		this.environment = environment;
		this.documentService = documentService;
	}

	@RabbitListener(queues = "file-queue.post")
	public void createFile(Contract contract) {
		System.out.println(" [x] Received request for " + contract);
		this.documentService.document(contract);
	}


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

}
