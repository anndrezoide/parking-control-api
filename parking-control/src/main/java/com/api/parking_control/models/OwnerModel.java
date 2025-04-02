package com.api.parking_control.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.api.parking_control.enums.UserRole;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TB_OWNER")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnerModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(nullable = false, length = 130)
	private String name;
	
	@Column(nullable = false, unique = true, length = 50)
	private String document;
	
	@Column(nullable = false, length = 30)
	private String apartment;
	
	@Column(nullable = false, length = 30)
	private String block;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 30)
	private UserRole role;
	
	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
	private Set<CarModel> cars = new HashSet<>();
	
	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
	private Set<ParkingSpotModel> parkingSpots = new HashSet<>();
	
}
