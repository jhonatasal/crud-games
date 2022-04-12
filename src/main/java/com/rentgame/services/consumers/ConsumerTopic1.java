package com.rentgame.services.consumers;

import java.util.Random;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaUtils;
import org.springframework.stereotype.Component;

import com.rentgame.models.requests.GameRequest;
import com.rentgame.services.producers.ProducerKafkaTopic1;

@Component
public class ConsumerTopic1 {
	@Value("${topic.name1}")
	private String topicName;

	@Autowired
	private ProducerKafkaTopic1 producerKafkaTopic1;

	@KafkaListener(topics = "${topic.name1}") // como o grupo não é informado aqui ele é pego da configuração inicial
	public void listenTopicGame1(ConsumerRecord<String, GameRequest> record) {
		processarMsg(KafkaUtils.getConsumerGroupId(), record, 1);
	}

	@KafkaListener(topics = "${topic.name1}") // como o grupo não é informado aqui ele é pego da configuração inicial
	public void listenTopicGame2(ConsumerRecord<String, GameRequest> record) {
		processarMsg(KafkaUtils.getConsumerGroupId(), record, 2);
	}

	@KafkaListener(topics = "${topic.name1}") // como o grupo não é informado aqui ele é pego da configuração inicial
	public void listenTopicGame3(ConsumerRecord<String, GameRequest> record) {
		processarMsg(KafkaUtils.getConsumerGroupId(), record, 3);
	}

	@KafkaListener(topics = "${topic.name1}", groupId = "grupo2") //
	public void listenTopicGame4(ConsumerRecord<String, GameRequest> record) {
		processarMsg(KafkaUtils.getConsumerGroupId(), record, 4);
	}

	@KafkaListener(topics = "${topic.name1}", groupId = "grupo2")
	public void listenTopicGame5(ConsumerRecord<String, GameRequest> record) {
		processarMsg(KafkaUtils.getConsumerGroupId(), record, 5);
	}

	@KafkaListener(topics = "${topic.name1}", groupId = "grupo3")
	public void listenTopicGame6(ConsumerRecord<String, GameRequest> record) {
		processarMsg(KafkaUtils.getConsumerGroupId(), record, 6);
	}

	private void processarMsg(String grupo, ConsumerRecord<String, GameRequest> record, Integer consumer) {
		if (record.key() == null || record.key().equals(grupo)) {
			if (new Random().nextInt() % 2 == 0) {
				System.out.println("Consumer que recebeu a msg " + consumer);
				System.out.println("Grupo que recebeu a mensagem: " + grupo);
				System.out.println("Particao que recebeu a msg: " + record.partition());
				System.out.println("Msg recebida" + record.value());
				System.out.println("--------------------------------");
			} else {
				System.out.println("Erro ao processar msg no grupo " + grupo);
				System.out.println("Enviando msg para DLQ");
				producerKafkaTopic1.sendMessageDlq(record.value(), grupo);
				System.out.println("--------------------------------");
			}
		}
	}

}
