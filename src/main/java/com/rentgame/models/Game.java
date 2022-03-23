package com.rentgame.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Document
@Builder
public class Game {
	@Id
	private String id;
	private String name;
	private String category;
	private LocalDate dateOfRelease;
	private LocalDateTime registrationDate = LocalDateTime.now();

}
