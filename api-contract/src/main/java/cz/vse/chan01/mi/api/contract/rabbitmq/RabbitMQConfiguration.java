package cz.vse.chan01.mi.api.contract.rabbitmq;

import org.springframework.amqp.core.AnonymousQueue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

	@Value("${document-service.routing-key.contract}")
	private String contractRoutingKey;

	@Value("${document-service.routing-key.document}")
	private String documentRountingKey;

	// Standard scenario exchange and queues

	@Bean
	public DirectExchange exchange() {
		return new DirectExchange("document-exchange");
	}

	@Bean
	public Queue contractQueue() {
		return QueueBuilder.durable("document-queue.contract")
			.withArgument("x-dead-letter-exchange", "dead-letter-exchange")
			.withArgument("x-dead-letter-routing-key", "dead-letter")
			.build();
	}

	@Bean
	public Queue documentQueue() {
		return QueueBuilder.durable("document-queue.document")
			.withArgument("x-dead-letter-exchange", "dead-letter-exchange")
			.withArgument("x-dead-letter-routing-key", "dead-letter")
			.build();
	}

	@Bean
	public Binding contractBinding(DirectExchange exchange) {
		return BindingBuilder.bind(contractQueue()).to(exchange).with(contractRoutingKey);
	}

	@Bean
	public Binding documentBinding(DirectExchange exchange) {
		return BindingBuilder.bind(documentQueue()).to(exchange).with(documentRountingKey);
	}

	// Dead letter exchange and queues

	@Bean
	DirectExchange deadLetterExchange() {
		return new DirectExchange("dead-letter-exchange");
	}

	@Bean
	Queue deadLetterQueue() {
		return QueueBuilder.durable("dead-letter.queue").build();
	}

	@Bean
	Binding deadLetterBinding() {
		return BindingBuilder.bind(deadLetterQueue()).to(deadLetterExchange()).with("dead-letter");
	}

	// AsynchronousRabbitTemplate

	@Bean
	public AsyncRabbitTemplate asyncTemplate(RabbitTemplate rabbitTemplate, ConnectionFactory connectionFactory) {
		rabbitTemplate.setRoutingKey(documentQueue().getName());
		rabbitTemplate.setReplyAddress(asynchronousReplyQueue().getName());
		return new AsyncRabbitTemplate(rabbitTemplate, replyContainer(connectionFactory));
	}

	@Bean
	public SimpleMessageListenerContainer replyContainer(ConnectionFactory connectionFactory) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
		container.setQueueNames(asynchronousReplyQueue().getName());
		return container;
	}

	@Bean
	public Queue asynchronousReplyQueue() {
		return new AnonymousQueue();
	}
}
