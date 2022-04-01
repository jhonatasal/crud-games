package com.rentgame.services;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentgame.services.interfaces.RabbitmqHeadersService;

@Service
public class RabbitmqHeadersServiceImpl implements RabbitmqHeadersService {
	@Autowired
	private RabbitTemplate rabbitTemplate;
	private static final String ROUTING_KEY = "";

	@Override
	public void sendMessageHeadersKey(String exchangeName, MessageProperties messageProperties, Object message) {
		MessageConverter messageConverter = new SimpleMessageConverter();
		Message msg = messageConverter.toMessage(message, messageProperties);
		rabbitTemplate.convertAndSend(exchangeName, ROUTING_KEY, msg);
	}

}
