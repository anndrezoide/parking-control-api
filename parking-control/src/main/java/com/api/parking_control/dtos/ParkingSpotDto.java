package com.api.parking_control.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingSpotDto {

	@NotBlank
	private String parkingSpotNumber;
	@NotBlank
	@Size(max = 7)
	private String licensePlateCar;
	@NotBlank
	private String brandCar;
	@NotBlank
	private String modelCar;
	@NotBlank
	private String colorCar;
	@NotBlank
	private String responsibleName;
	@NotBlank
	private String apartament;
	@NotBlank
	private String block;
	
	
}
