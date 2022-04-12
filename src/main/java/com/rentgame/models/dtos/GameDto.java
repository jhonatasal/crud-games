package com.rentgame.models.dtos;

import com.rentgame.models.Game;

import lombok.Data;

@Data
public class GameDto {
	private String id;
	private String name;
	private String category;
	private String dateOfRelease;

	public GameDto(Game game) {
		this.id = game.getId();
		this.name = game.getName();
		this.category = game.getCategory();
		this.dateOfRelease = game.getDateOfRelease();
	}
}
