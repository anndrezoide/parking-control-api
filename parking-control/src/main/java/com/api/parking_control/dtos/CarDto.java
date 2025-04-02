package com.api.parking_control.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {
	
	@NotBlank
	@Size(max = 7)
	private String licensePlate;
	
	@NotBlank
	private String brand;
	
	@NotBlank
	private String model;
	
	@NotBlank
	private String color;
	
	@NotBlank
	private UUID ownerId; //relacionamento com o owner
}
