package com.rentgame.resources.exceptions;

import java.time.Instant;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StandartError {
	private Instant timestamp;
	private Integer status;
	private String error;
	private String message;
	private String path;
	private String method;
}
