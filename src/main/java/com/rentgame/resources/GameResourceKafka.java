package com.rentgame.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentgame.models.requests.GameRequest;
import com.rentgame.services.producers.ProducerKafkaTopic1;


@RestController
@RequestMapping(path = GameResourceKafka.URI)
public class GameResourceKafka {

	protected static final String URI = "/games/kafka";

	@Autowired
	private ProducerKafkaTopic1 producerKafkaTopic1;

	@PostMapping("/1")
	public ResponseEntity<?> enviarMensagem(@RequestBody GameRequest gameRequest) {
		System.out.println("Enviando msg para Topico 1");
		producerKafkaTopic1.sendMessage(gameRequest);
		return ResponseEntity.ok("");
	}
}
