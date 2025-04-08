package com.api.parking_control.models;

import java.io.Serializable;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
@Table(name = "TB_CAR")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true, length = 7)
    private String licensePlate;

    @Column(nullable = false, length = 70)
    private String brand;

    @Column(nullable = false, length = 70)
    private String model;

    @Column(nullable = false, length = 70)
    private String color;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    @JsonBackReference
    private OwnerModel owner;

    @OneToOne(mappedBy = "car")
    @JsonBackReference
    private ParkingSpotModel parkingSpot;
}
