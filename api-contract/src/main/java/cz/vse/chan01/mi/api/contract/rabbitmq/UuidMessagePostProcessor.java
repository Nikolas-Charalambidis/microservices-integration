package cz.vse.chan01.mi.api.contract.rabbitmq;

import java.util.UUID;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;

public class UuidMessagePostProcessor implements MessagePostProcessor {

	private final UUID uuid;

	public UuidMessagePostProcessor(final UUID uuid) {
		this.uuid = uuid;
	}

	@Override
	public Message postProcessMessage(final Message message) throws AmqpException {
		message.getMessageProperties().setCorrelationId(uuid.toString());
		return message;
	}
}
