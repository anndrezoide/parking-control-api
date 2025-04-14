package com.api.parking_control.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.api.parking_control.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonProperty;

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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_OWNER")
@Getter
@Setter
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

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "owner")
    private Set<CarModel> cars = new HashSet<>();

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "owner")
    private Set<ParkingSpotModel> parkingSpots = new HashSet<>();
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CarModel)) return false;
        CarModel that = (CarModel) o;
        return id != null && id.equals(that.getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }

}