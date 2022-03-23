package com.rentgame.services.interfaces;

import java.util.List;

import javax.validation.Valid;

import com.rentgame.models.dtos.GameDto;
import com.rentgame.models.requests.GameRequest;

public interface GameInterfaceService {

	List<GameDto> findAllGames();

	GameDto createGame(@Valid GameRequest request);

	void deleteGame(String id);

	GameDto updateGame(String id, GameRequest request);

	GameDto findGameById(String id);

}
