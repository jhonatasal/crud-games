package com.rentgame.services.interfaces;

import com.rentgame.models.requests.GameRequest;

public interface RabbitmqFanoutService {

	void sendMessageFanoutKey(GameRequest game, String routingKeyName, String exchangeName);
}
