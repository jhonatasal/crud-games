package com.rentgame.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.rentgame.models.Game;

public interface GameRepository extends MongoRepository<Game, String> {

}
