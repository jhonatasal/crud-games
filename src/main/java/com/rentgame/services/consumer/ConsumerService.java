package com.rentgame.services.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.rentgame.models.requests.GameRequest;

@Service
public class ConsumerService {
	
	
	@RabbitListener(queues = "${nome.queue.fila1}")
    public void listenerFila1(@Payload GameRequest mensagem) {
        System.out.println("Recebendo mensangem da fila 1 onde contem todos parametros exigidos pelo header -> " + mensagem);
    }
	@RabbitListener(queues = "${nome.queue.fila2}")
	public void listenerFila2(@Payload GameRequest mensagem) {
		System.out.println("Recebendo mensangem da fila 2 onde contem algum ou todos parametros exigidos pelo header -> " + mensagem);
	}

	@RabbitListener(queues = "${nome.queue.fila3}")
	public void listenerFila3(@Payload GameRequest mensagem) {
		System.out.println("Recebendo mensangem da fila 3 onde todas os parametros do header batem-> " + mensagem);
	}
	@RabbitListener(queues = "${nome.queue.fila4}")
	public void listenerFila4(@Payload GameRequest mensagem) {
		System.out.println("Recebendo mensangem da fila 4 onde algums dos parametros do header batem-> " + mensagem);
	}
}
