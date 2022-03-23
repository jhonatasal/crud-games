package com.rentgame.models.requests;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class GameRequest {
	@NotBlank
	private String name;
	@NotBlank
	private String category;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateOfRelease;
}
