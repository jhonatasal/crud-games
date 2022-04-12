package com.rentgame.services.producers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.rentgame.models.requests.GameRequest;

@Service
public class ProducerKafkaTopic1 {

	@Value("${topic.name1}")
	private String topicName;

	@Value("${topic.nameDlq}")
	private String nameDlq;

	@Autowired
	private Gson gson;

	@Autowired
	private KafkaTemplate<String, GameRequest> kafkaTemplate;

	public void sendMessage(GameRequest game) {
		kafkaTemplate.send(topicName, game);
	}

	public void sendMessage(Object game, String key) {
		kafkaTemplate.send(topicName, key, gson.fromJson(game.toString(), GameRequest.class));
	}

	public void sendMessageDlq(Object game, String groupId) {
		kafkaTemplate.send(nameDlq, groupId, gson.fromJson(game.toString(), GameRequest.class));
	}
}
