package cz.vse.chan01.mi.api.file;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RestController;

import cz.vse.chan01.mi.model.contract.Contract;
import cz.vse.chan01.mi.model.file.File;

@RestController("/")
public class FileController {

	@Autowired
	private Environment environment;

	@RabbitListener(queues = "file-queue.post")
	public File createFile(Contract contract) {
		System.out.println(" [x] Received request for " + contract);
		File file = new File(contract.getContractId(), contract.getCustomerId(), contract.getLabel());
		System.out.println(" [.] Returned " + file);
		return file;
	}

	/*
	@RabbitListener(queues = "file-queue.post")
	public File createFile(
		@Payload Contract contract,
		@Header(AmqpHeaders.CHANNEL) Channel channel,
		@Header(AmqpHeaders.DELIVERY_TAG) Long tag,
		@Header(AmqpHeaders.CORRELATION_ID) String correlationId,
		Message message) throws InterruptedException, IOException {

		System.out.println("Channel: " + channel.getChannelNumber() + " deliveryTag: " + tag + "=: " + message.getMessageProperties().getDeliveryTag());
		StopWatch watch = new StopWatch();
		watch.start();
		System.out.println(" [localhost:" + environment.getProperty("server.port") +"] CorrelationId: " + correlationId +  " Received " + contract);
		Random random = new Random();
		Thread.sleep(5000 + random.nextInt(2000));

		System.out.println(" [x] Received request for " + contract);
		File file = new File(contract.getContractId(), contract.getCustomerId(), contract.getLabel());
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
