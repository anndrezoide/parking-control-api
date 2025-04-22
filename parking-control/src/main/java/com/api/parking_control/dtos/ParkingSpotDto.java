package com.api.parking_control.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParkingSpotDto {

    @NotBlank
    private String parkingSpotNumber;

    @NotNull
    private UUID ownerId;

    @NotNull
    private UUID carId;
}