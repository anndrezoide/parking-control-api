package com.api.parking_control.models;

import java.io.Serializable;
import java.util.UUID;

import javax.management.relation.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	private String apartament;
	
	@Column(nullable = false, length = 30)
	private String block;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 30)
	private Role role;
}
