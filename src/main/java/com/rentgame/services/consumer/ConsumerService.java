package com.rentgame.services.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.rentgame.models.requests.GameRequest;

@Service
public class ConsumerService {

	@RabbitListener(queues = "${nome.queue.fila1}")
	public void listenerFila1(@Payload GameRequest mensagem) {
		System.out.println("Recebendo mensangem da fila 1 (Capital MG)-> " + mensagem);
	}

	@RabbitListener(queues = "${nome.queue.fila2}")
	public void listenerFila2(@Payload GameRequest mensagem) {
		System.out.println("Recebendo mensangem da fila 2 (Capital SP)-> " + mensagem);
	}

	@RabbitListener(queues = "${nome.queue.fila3}")
	public void listenerFila3(@Payload GameRequest mensagem) {
		System.out.println("Recebendo mensangem da fila 3 (Interior SP)-> " + mensagem);
	}

	@RabbitListener(queues = "${nome.queue.fila4}")
	public void listenerFila4(@Payload GameRequest mensagem) {
		System.out.println("Recebendo mensangem da fila 4 (Qualquer capital)-> " + mensagem);
	}

	@RabbitListener(queues = "${nome.queue.fila5}")
	public void listenerFila5(@Payload GameRequest mensagem) {
		System.out.println("Recebendo mensangem da fila 5 (Qualquer interior)-> " + mensagem);
	}
}
