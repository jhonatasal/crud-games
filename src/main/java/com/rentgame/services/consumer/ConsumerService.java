package com.rentgame.services.consumer;

import java.util.Map;
import java.util.Random;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.rentgame.models.requests.GameRequest;
import com.rentgame.services.interfaces.RabbitmqDirectService;

@Service
public class ConsumerService {
	@Autowired
	private RabbitmqDirectService rabbitmqDirectService;

	@Value("${nome.routingKey.direct1}")
	private String routingKeyDirect1;

	@Value("${nome.routingKey.direct2}")
	private String routingKeyDirect2;

	@Value("${nome.routingKey.direct3}")
	private String routingKeyDirect3;

	@RabbitListener(queues = "${nome.queue.fila1}")
	public void listenerFila1(@Payload GameRequest mensagem) {
		if (new Random().nextInt() % 2 == 0) {
			System.out.println("Recebendo mensangem da fila 1 -> " + mensagem);
		} else {
			System.out.println("Erro ao processar msg");
			System.out.println("Encaminhando para dlq");
			rabbitmqDirectService.sendMessageDlq(mensagem, routingKeyDirect1);
		}
	}

	@RabbitListener(queues = "${nome.queue.fila2}")
	public void listenerFila2(@Payload GameRequest mensagem) {
		if (new Random().nextInt() % 2 == 0) {
			System.out.println("Recebendo mensangem da fila 2-> " + mensagem);
		} else {
			System.out.println("Erro ao processar msg");
			System.out.println("Encaminhando para dlq");
			rabbitmqDirectService.sendMessageDlq(mensagem, routingKeyDirect2);
		}
	}

	@RabbitListener(queues = "${nome.queue.fila3}")
	public void listenerFila3(@Payload GameRequest mensagem) {
		if (new Random().nextInt() % 2 == 0) {
			System.out.println("Recebendo mensangem da fila 3 -> " + mensagem);
		} else {
			System.out.println("Erro ao processar msg");
			System.out.println("Encaminhando para dlq");
			rabbitmqDirectService.sendMessageDlq(mensagem, routingKeyDirect3);
		}
	}

	@RabbitListener(queues = "${nome.queue.dlq}")
	public void listenerDlq(@Payload Map<String, Object> map) {
		GameRequest gameRequest = (GameRequest) map.get("object");
		String routingKey = map.get("routingKey").toString();
		System.out.println("Recebendo mensangem da dlq -> " + gameRequest);
		System.out.println("Enviando mensagem para " + routingKey);
		rabbitmqDirectService.sendMessageDirectKey(gameRequest, routingKey);
	}
}
