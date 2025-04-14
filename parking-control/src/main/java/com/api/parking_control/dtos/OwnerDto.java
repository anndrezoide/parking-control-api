package com.api.parking_control.dtos;

import java.util.Set;
import java.util.UUID;

import com.api.parking_control.enums.UserRole;

/*
@Data
@AllArgsConstructor
@NoArgsConstructor*/
public record OwnerDto(String name, String document, String apartment, String block, UserRole role, Set<UUID> carId, Set<UUID> parkingSpotId) {
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
