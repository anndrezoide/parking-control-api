package com.api.parking_control.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.parking_control.models.ParkingSpotModel;
import com.api.parking_control.repositories.ParkingSpotRepository;

@Service
public class ParkingSpotService {

	@Autowired
	private ParkingSpotRepository parkingSpotRepository;

	@Transactional //Importante para metodos construtivos e destrutivos pois se algo der errado ele garante o rollback para que não tenha dados quebrados.
	public ParkingSpotModel save(ParkingSpotModel parkingSpotModel) {
		return parkingSpotRepository.save(parkingSpotModel);
	}
	
}
