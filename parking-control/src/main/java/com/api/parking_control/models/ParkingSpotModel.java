package com.api.parking_control.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TB_PARKING_SPOT")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkingSpotModel implements Serializable {
	private static final long serialVersionUID = 1L; //Conversões de objetos java para bites para serem salvas no banco de dados.
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(nullable = false, unique = true, length = 10)
	private String parkingSpotNumber;
	
	@Column(nullable = false)
	private LocalDateTime registrationDate;
	
	@ManyToOne
	@JoinColumn(name = "owner_id", nullable = false)
	private OwnerModel owner;
	
	@OneToOne(mappedBy = "parkingSpot")
	private CarModel car;

}
