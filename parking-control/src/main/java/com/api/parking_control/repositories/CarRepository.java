package com.api.parking_control.repositories;

import java.util.Set;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;

import com.api.parking_control.models.CarModel;
import com.api.parking_control.models.OwnerModel;

@Repository
public interface CarRepository extends JpaRepository<CarModel, UUID>{
}
