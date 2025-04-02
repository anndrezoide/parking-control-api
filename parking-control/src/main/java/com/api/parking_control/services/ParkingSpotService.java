package com.api.parking_control.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.parking_control.models.ParkingSpotModel;
import com.api.parking_control.repositories.CarRepository;
import com.api.parking_control.repositories.OwnerRepository;
import com.api.parking_control.repositories.ParkingSpotRepository;

@Service
public class ParkingSpotService {

	@Autowired
	private ParkingSpotRepository parkingSpotRepository;
	
	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private OwnerRepository ownerRepository;

	@Transactional //Importante para metodos construtivos e destrutivos pois se algo der errado ele garante o rollback para que não tenha dados quebrados.
	public ParkingSpotModel save(ParkingSpotModel parkingSpotModel) {
		return parkingSpotRepository.save(parkingSpotModel);
	}
	
	public boolean existsByLicensePlateCar(String licensePlateCar) {
		return parkingSpotRepository.existsByLicensePlateCar(licensePlateCar);
	}

	public boolean existsByParkingSpotNumber(String parkingSpotNumber) {
		return parkingSpotRepository.existsByParkingSpotNumber(parkingSpotNumber);
	}

	public boolean existsByApartamentAndBlock(String apartament, String block) {
		return parkingSpotRepository.existsByApartamentAndBlock(apartament, block);
	}

	public Page<ParkingSpotModel> findAll(Pageable pageable) {
		return parkingSpotRepository.findAll(pageable);
	}

	public Optional<ParkingSpotModel> findById(UUID id) {
		return parkingSpotRepository.findById(id);
	}

	@Transactional
	public void delete(ParkingSpotModel parkingSpotModel) {
		parkingSpotRepository.delete(parkingSpotModel);
	}

	public List<ParkingSpotModel> findByColor(String color) {
		return parkingSpotRepository.findParkingSpotByColorCar(color);
	}

	public List<ParkingSpotModel> findByBrandCar(String brand) {
		return parkingSpotRepository.findParkingSpotModelByBrandCar(brand);
	}
	
	
}
