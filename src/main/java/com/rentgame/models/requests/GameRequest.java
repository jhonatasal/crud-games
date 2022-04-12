package com.rentgame.models.requests;

import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GameRequest   {
	
	@NotBlank
	private String name;
	@NotBlank
	private String category;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String dateOfRelease;
}
