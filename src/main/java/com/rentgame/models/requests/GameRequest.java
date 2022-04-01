package com.rentgame.models.requests;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class GameRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotBlank
	private String name;
	@NotBlank
	private String category;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateOfRelease;
}
