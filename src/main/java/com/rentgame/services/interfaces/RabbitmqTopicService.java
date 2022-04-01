package com.rentgame.services.interfaces;

import com.rentgame.models.requests.GameRequest;

public interface RabbitmqTopicService {

	void sendMessageTopicKey(GameRequest game, String routingKeyName, String exchangeName);
}
