package com.rentgame.services.interfaces;

import com.rentgame.models.requests.GameRequest;

public interface RabbitmqDirectService {

	void sendMessageDirectKey(GameRequest game, String routingKeyName);

	void sendMessageDlq(GameRequest game, String routingKey);
}
