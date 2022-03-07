package com.carsforyouproject.base.model;

import java.util.List;

import com.carsforyouproject.base.dto.CarDetails;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarResponse {
	
	private List<CarDetails> carDetails;
	private String message;
	private boolean error;
}
