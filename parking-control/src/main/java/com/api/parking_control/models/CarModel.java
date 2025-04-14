package com.api.parking_control.models;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.CascadeType;
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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_CAR")
@Getter
@Setter
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
    @JoinColumn(name = "owner_id")
    private OwnerModel owner;

    @OneToOne(mappedBy = "car", cascade = CascadeType.ALL)
    private ParkingSpotModel parkingSpot;
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CarModel)) return false;
        CarModel that = (CarModel) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
