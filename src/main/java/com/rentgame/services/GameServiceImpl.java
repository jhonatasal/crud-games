package com.rentgame.services;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentgame.models.Game;
import com.rentgame.models.dtos.GameDto;
import com.rentgame.models.requests.GameRequest;
import com.rentgame.repositories.GameRepository;
import com.rentgame.services.exceptions.ResourceNotFoundException;
import com.rentgame.services.interfaces.GameInterfaceService;

@Service
public class GameServiceImpl implements GameInterfaceService {

	@Autowired
	private GameRepository repository;

	@Override
	public List<GameDto> findAllGames() {
		return repository.findAll().stream().map(game -> new GameDto(game)).collect(Collectors.toList());
	}

	@Override
	public GameDto createGame(GameRequest gameRequest) {
		Game game = Game.builder()
				.name(gameRequest.getName())
				.category(gameRequest.getCategory())
				.dateOfRelease(gameRequest.getDateOfRelease())
				.build();
		
		return new GameDto(repository.save(game));
	}

	@Override
	public void deleteGame(String id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
		} else {
			throw new ResourceNotFoundException(id);
		}
	}

	@Override
	public GameDto findGameById(String id) {
		return new GameDto(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id)));
	}

	@Override
	public GameDto updateGame(String id, GameRequest request) {
		Game game = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		
		game.setName(request.getName().isBlank() ? game.getName() : request.getName());
		game.setDateOfRelease(Objects.isNull(request.getDateOfRelease()) ? game.getDateOfRelease() : request.getDateOfRelease());
		game.setCategory(request.getCategory().isBlank() ? game.getCategory() : request.getCategory());

		return new GameDto(repository.save(game));
	}

}
