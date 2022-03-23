package com.rentgame.resources;

import java.util.List;

import javax.validation.Valid;

import com.rentgame.models.dtos.GameDto;
import com.rentgame.models.requests.GameRequest;
import com.rentgame.services.interfaces.GameInterfaceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/games")
@Api(value = "API games")
public class GameResource {
	@Autowired
	private GameInterfaceService service;

	@GetMapping
	@ApiOperation(value = "Retornar a lista de games Cadastrados")
	public ResponseEntity<List<GameDto>> findAll() {
		return ResponseEntity.ok(service.findAllGames());
	}

	@PostMapping
	@ApiOperation(value = "Salvar um novo game")
	public ResponseEntity<GameDto> saveGame(@Valid @RequestBody GameRequest request) {
		return ResponseEntity.ok(service.createGame(request));
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deletar um game por id")
	public ResponseEntity<Void> deleteGameById(@PathVariable String id) {
		service.deleteGame(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "Atualizar dados de um game")
	public ResponseEntity<GameDto> updateGame(@RequestBody GameRequest request, @PathVariable String id) {
		return ResponseEntity.ok(service.updateGame(id, request));
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Buscar dados de um game por id")
	public ResponseEntity<GameDto> findGameById(@PathVariable String id) {
		return ResponseEntity.ok(service.findGameById(id));
	}
}
