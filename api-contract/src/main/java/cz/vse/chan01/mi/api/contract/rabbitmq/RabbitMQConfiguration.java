package cz.vse.chan01.mi.api.contract.rabbitmq;

import org.springframework.amqp.core.AnonymousQueue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

	@Bean
	public DirectExchange exchange() {
		return new DirectExchange("document-exchange");
	}

	@Bean
	public Queue contractQueue() {
		return new Queue("document-queue.contract");
	}

	@Bean
	public Queue documentQueue() {
		return new Queue("document-queue.document");
	}

	@Bean
	public Binding contractBinding(DirectExchange exchange, @Qualifier("contractQueue") Queue queue) {
		return BindingBuilder.bind(queue).to(exchange).with("contract");
	}

	@Bean
	public Binding documentBinding(DirectExchange exchange, @Qualifier("documentQueue") Queue queue) {
		return BindingBuilder.bind(queue).to(exchange).with("document");
	}

	/////

	@Bean
	public AsyncRabbitTemplate asyncTemplate(RabbitTemplate rabbitTemplate, ConnectionFactory connectionFactory) {
		rabbitTemplate.setRoutingKey(documentQueue().getName());
		rabbitTemplate.setReplyAddress(replyQueue().getName());
		return new AsyncRabbitTemplate(rabbitTemplate, replyContainer(connectionFactory));
	}

	@Bean
	public SimpleMessageListenerContainer replyContainer(ConnectionFactory connectionFactory) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
		container.setQueueNames(replyQueue().getName());
		return container;
	}

	@Bean
	public Queue replyQueue() {
		return new AnonymousQueue();
	}
}
