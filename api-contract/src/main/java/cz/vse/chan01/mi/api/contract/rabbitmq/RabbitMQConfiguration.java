package cz.vse.chan01.mi.api.contract.rabbitmq;

import org.springframework.amqp.core.AnonymousQueue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
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
	public Binding contractBinding(DirectExchange exchange) {
		return BindingBuilder.bind(contractQueue()).to(exchange).with("contract");
	}

	@Bean
	public Binding documentBinding(DirectExchange exchange) {
		return BindingBuilder.bind(documentQueue()).to(exchange).with("document");
	}


	/////////////

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
