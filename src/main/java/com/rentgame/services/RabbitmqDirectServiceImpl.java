package com.rentgame.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rentgame.models.requests.GameRequest;
import com.rentgame.services.interfaces.RabbitmqDirectService;

@Service
public class RabbitmqDirectServiceImpl implements RabbitmqDirectService {
	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Value("${nome.routingKey.dlq}")
	private String dlq;

	@Value("${nome.exchange.rabbitmq}")
	private String nomeDaExchangeDirect;

	@Override
	public void sendMessageDirectKey(GameRequest game, String routingKeyName) {
		rabbitTemplate.convertAndSend(nomeDaExchangeDirect, routingKeyName, game);
	}

	@Override
	public void sendMessageDlq(GameRequest game, String routingKey) {
		Map<String, Object> map = new HashMap<>();
		map.put("object", game);
		map.put("routingKey", routingKey);
		rabbitTemplate.convertAndSend(nomeDaExchangeDirect, dlq, map);
	}

}
