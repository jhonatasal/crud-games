package com.rentgame.resources;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentgame.models.requests.GameRequest;
import com.rentgame.services.interfaces.RabbitmqDirectService;


@RestController
@RequestMapping(path = GameRabbitMqResourceDirect.URI)
public class GameRabbitMqResourceDirect {
	protected static final String URI = "/games/direct";

	@Autowired
	private RabbitmqDirectService rabbitmqDirectService;

	@Autowired
	private @Qualifier("mapnames") Map<String, String> mapNames;

	@PostMapping("/1")
	public void enviarMessageFila1(@Valid @RequestBody GameRequest request) {
		rabbitmqDirectService.sendMessageDirectKey(request, mapNames.get("routingkey1"), mapNames.get("exchange"));
	}

	@PostMapping("/2")
	public void enviarMessageFila2(@Valid @RequestBody GameRequest request) {
		rabbitmqDirectService.sendMessageDirectKey(request, mapNames.get("routingkey2"), mapNames.get("exchange"));
	}

	@PostMapping("/3")
	public void enviarMessageFila3(@Valid @RequestBody GameRequest request) {
		rabbitmqDirectService.sendMessageDirectKey(request, mapNames.get("routingkey3"), mapNames.get("exchange"));
	}
}
