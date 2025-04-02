package com.api.parking_control.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.parking_control.models.ParkingSpotModel;

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpotModel, UUID>{
	/*
	boolean existsByLicensePlateCar(String licensePlateCar);
	boolean existsByParkingSpotNumber(String parkingSpotNumber);
	boolean existsByApartamentAndBlock(String apartament, String block);
	/*
	@Query("SELECT p FROM ParkingSpotModel p WHERE LOWER(p.colorCar) = LOWER(:color)")
	List<ParkingSpotModel> findParkingSpotByColorCar(@Param("color") String color);
	*/
	//List<ParkingSpotModel> findParkingSpotModelByBrandCar(String brandCar);

}
