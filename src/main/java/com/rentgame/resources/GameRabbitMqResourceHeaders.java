package com.rentgame.resources;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentgame.models.requests.GameRequest;
import com.rentgame.services.interfaces.RabbitmqHeadersService;

@RestController
@RequestMapping(path = GameRabbitMqResourceHeaders.URI)
public class GameRabbitMqResourceHeaders {
	protected static final String URI = "/games/headers";

	@Autowired
	private RabbitmqHeadersService rabbitmqHeadersService;

	@Autowired
	private @Qualifier("mapnames") Map<String, String> mapNames;

	@PostMapping("/1")
	public void enviarMessageFila1(@Valid @RequestBody GameRequest request) {
		MessageProperties messageProperties = new MessageProperties();
		messageProperties.setHeader("Jhonatas", null);
		messageProperties.setHeader("Alves", null);
		messageProperties.setHeader("Souza", null);
		System.out.println("-------------------------------");
		System.out.println("Enviando mensagem onde onde contem todos parametros exigidos pelo header");
		rabbitmqHeadersService.sendMessageHeadersKey(mapNames.get("exchange"), messageProperties, request);
	}

	@PostMapping("/2")
	public void enviarMessageFila2(@Valid @RequestBody GameRequest request) {
		MessageProperties messageProperties = new MessageProperties();
		messageProperties.setHeader("Jhonatas", "");
		System.out.println("-------------------------------");
		System.out.println("Enviando mensagem onde apenas uma contem no algum parametro exigido pelo header");
		rabbitmqHeadersService.sendMessageHeadersKey(mapNames.get("exchange"), messageProperties, request);
	}

	@PostMapping("/3")
	public void enviarMessageFila3(@Valid @RequestBody GameRequest request) {
		MessageProperties messageProperties = new MessageProperties();
		messageProperties.setHeader("Nome", "Jhonatas");
		messageProperties.setHeader("Idade", 26);
		System.out.println("-------------------------------");
		System.out.println("Enviando mensagem onde todos os parametros do header batem");
		rabbitmqHeadersService.sendMessageHeadersKey(mapNames.get("exchange"), messageProperties, request);
	}
	
	@PostMapping("/4")
	public void enviarMessageFila4(@Valid @RequestBody GameRequest request) {
		MessageProperties messageProperties = new MessageProperties();
		messageProperties.setHeader("Nome", "Jhonatas");
		System.out.println("-------------------------------");
		System.out.println("Enviando mensagem onde algums ou todos os parametros do header batem");
		rabbitmqHeadersService.sendMessageHeadersKey(mapNames.get("exchange"), messageProperties, request);
	}
}
