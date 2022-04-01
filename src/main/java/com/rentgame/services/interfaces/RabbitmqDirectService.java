package com.rentgame.services.interfaces;

import com.rentgame.models.requests.GameRequest;

public interface RabbitmqDirectService {

	void sendMessageDirectKey(GameRequest game, String routingKeyName, String exchangeName);
}
