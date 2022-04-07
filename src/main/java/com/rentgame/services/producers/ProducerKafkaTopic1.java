package com.rentgame.services.producers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.rentgame.models.requests.GameRequest;

@Service
public class ProducerKafkaTopic1 {

	@Value("${topic.name1}")
	private String topicName;

	@Autowired
	private KafkaTemplate<String, GameRequest> kafkaTemplate;

	public void sendMessage(GameRequest game) {
		kafkaTemplate.send(topicName, game);
	}
}
