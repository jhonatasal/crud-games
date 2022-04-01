package com.rentgame.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentgame.models.requests.GameRequest;
import com.rentgame.services.interfaces.RabbitmqDirectService;

@Service
public class RabbitmqDirectServiceImpl implements RabbitmqDirectService {
	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Override
	public void sendMessageDirectKey(GameRequest game, String routingKeyName, String exchangeName) {
		rabbitTemplate.convertAndSend(exchangeName, routingKeyName, game);
	}

}
