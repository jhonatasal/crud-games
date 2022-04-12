package com.rentgame.services.consumers;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.rentgame.models.requests.GameRequest;
import com.rentgame.services.producers.ProducerKafkaTopic1;

@Component
public class ConsumerDlq {

	@Autowired
	private ProducerKafkaTopic1 producerKafkaTopic1;

	@KafkaListener(topics = "${topic.nameDlq}")
	public void listenTopicGame1(ConsumerRecord<String, GameRequest> record) {
		System.out.println("---------------------");
		System.out.println("Reenviando msg armazenada em DLQ");
		producerKafkaTopic1.sendMessage(record.value(), record.key());
		System.out.println("---------------------");
	}
}
