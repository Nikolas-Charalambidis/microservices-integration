package cz.vse.chan01.mi.api.contract.service;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import cz.vse.chan01.swagger.contract.model.Contract;

@Service
public class DocumentServiceImpl implements DocumentService {

	private final RabbitTemplate template;

	private final DirectExchange exchange;

	public DocumentServiceImpl(final RabbitTemplate template, final DirectExchange exchange) {
		this.template = template;
		this.exchange = exchange;
	}

	@Override
	public void createDocument(final Contract contract) {
		System.out.println("[x] Sending request(" + contract.getContractId() + ")");
		template.convertAndSend(exchange.getName(), "post", contract);
	}
}
