package com.api.parking_control.dtos;

import java.util.UUID;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingSpotDto {

	@NotBlank
	private String parkingSpotNumber;
	
	@Valid
	@NotNull
	private UUID carId;
	
	@Valid
	@NotNull
	private UUID ownerID;
	
}
