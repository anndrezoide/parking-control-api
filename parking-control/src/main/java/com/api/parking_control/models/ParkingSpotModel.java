package com.api.parking_control.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_PARKING_SPOT")
public class ParkingSpotModel implements Serializable {
	private static final long serialVersionUID = 1L; //Conversões de objetos java para bites para serem salvas no banco de dados.
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(nullable = false, unique = true, length = 10)
	private String parkingSpotNumber;
	
	@Column(nullable = false, unique = true, length = 7)
	private String licensePlateCar;
	
	@Column(nullable = false, length = 70)
	private String brandCar;
	
	@Column(nullable = false, length = 70)
	private String modelCar;
	
	@Column(nullable = false, length = 70)
	private String colorCar;
	
	@Column(nullable = false)
	private LocalDateTime registrationDate;
	
	@Column(nullable = false, length = 130)
	private String responsibleName;
	
	@Column(nullable = false, length = 30)
	private String apartament;
	
	@Column(nullable = false, length = 30)
	private String block;
}
