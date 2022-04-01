package com.rentgame.services.interfaces;

import org.springframework.amqp.core.MessageProperties;

public interface RabbitmqHeadersService {

	void sendMessageHeadersKey(String exchangeName, MessageProperties messageProperties, Object message);
}
