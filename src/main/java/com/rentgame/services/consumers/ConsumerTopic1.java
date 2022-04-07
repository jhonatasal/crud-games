package com.rentgame.services.consumers;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.rentgame.models.requests.GameRequest;

@Component
public class ConsumerTopic1 {
	@Value("${topic.name1}")
	private String topicName;

	@Value("${spring.kafka.consumer.group-id}")
	private String groupIdPadrão;

	@KafkaListener(topics = "${topic.name1}") // como o grupo não é informado aqui ele é pego da configuração inicial
	public void listenTopicGame1(ConsumerRecord<String, GameRequest> record) {
		System.out.println("Consumer que recebeu a msg "+1);
		System.out.println("Grupo que recebeu a mensagem: " + groupIdPadrão);
		System.out.println("Particao que recebeu a msg: " + record.partition());
		System.out.println("Msg recebida" + record.value());
		System.out.println("--------------------------------");
	}

	@KafkaListener(topics = "${topic.name1}") // como o grupo não é informado aqui ele é pego da configuração inicial
	public void listenTopicGame2(ConsumerRecord<String, GameRequest> record) {
		System.out.println("Consumer que recebeu a msg "+2);
		System.out.println("Grupo que recebeu a mensagem: " + groupIdPadrão);
		System.out.println("Particao que recebeu a msg: " + record.partition());
		System.out.println("Msg recebida" + record.value());
		System.out.println("--------------------------------");
	}

	@KafkaListener(topics = "${topic.name1}") // como o grupo não é informado aqui ele é pego da configuração inicial
	public void listenTopicGame3(ConsumerRecord<String, GameRequest> record) {
		System.out.println("Consumer que recebeu a msg "+3);
		System.out.println("Grupo que recebeu a mensagem: " + groupIdPadrão);
		System.out.println("Particao que recebeu a msg: " + record.partition());
		System.out.println("Msg recebida" + record.value());
		System.out.println("--------------------------------");
	}
	
	@KafkaListener(topics = "${topic.name1}",groupId = "grupo2") // 
	public void listenTopicGame4(ConsumerRecord<String, GameRequest> record) {
		System.out.println("Consumer que recebeu a msg "+4);
		System.out.println("Grupo que recebeu a mensagem: grupo2");
		System.out.println("Particao que recebeu a msg: " + record.partition());
		System.out.println("Msg recebida" + record.value());
		System.out.println("--------------------------------");
	}
	
	@KafkaListener(topics = "${topic.name1}", groupId = "grupo2") 
	public void listenTopicGame5(ConsumerRecord<String, GameRequest> record) {
		System.out.println("Consumer que recebeu a msg "+5);
		System.out.println("Grupo que recebeu a mensagem: grupo2");
		System.out.println("Particao que recebeu a msg: " + record.partition());
		System.out.println("Msg recebida" + record.value());
		System.out.println("--------------------------------");
	}

	@KafkaListener(topics = "${topic.name1}", groupId = "grupo3") 
	public void listenTopicGame6(ConsumerRecord<String, GameRequest> record) {
		System.out.println("Consumer que recebeu a msg "+6);
		System.out.println("Grupo que recebeu a mensagem: grupo3");
		System.out.println("Particao que recebeu a msg: " + record.partition());
		System.out.println("Msg recebida" + record.value());
		System.out.println("--------------------------------");
	}

}
