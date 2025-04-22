package com.api.parking_control.dtos;

import java.util.Set;
import java.util.UUID;

import com.api.parking_control.enums.UserRole;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/*
@Data
@AllArgsConstructor
@NoArgsConstructor*/
public record OwnerDto(
		@NotBlank @Size(max = 130, message = "The name must have util 100 caracteres") String name, 
		@NotBlank
		String document, 
		@NotBlank
		String apartment, 
		@NotBlank
		String block,
		@NotBlank
		UserRole role, 
		Set<UUID> carId, 
		Set<UUID> parkingSpotId) {
	/*
	@NotBlank
	@Size(max = 130, message = "The name must have util 100 caracteres")
	private String name;
	
	@NotBlank
	private String document;
	
	@NotBlank
	private String apartment;
	
	@NotBlank
	private String block;
	
	@NotNull
	private UserRole role;
	
	//private List<CarModel> cars;
    private UUID carId;*/
}
