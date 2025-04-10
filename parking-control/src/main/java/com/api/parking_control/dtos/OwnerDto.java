package com.api.parking_control.dtos;

import java.util.Set;
import java.util.UUID;

import com.api.parking_control.enums.UserRole;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnerDto {
	
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
	
    private UUID carId;
}
