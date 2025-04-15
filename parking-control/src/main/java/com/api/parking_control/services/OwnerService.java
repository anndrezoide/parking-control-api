package com.api.parking_control.services;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.parking_control.dtos.OwnerDto;
import com.api.parking_control.models.CarModel;
import com.api.parking_control.models.OwnerModel;
import com.api.parking_control.models.ParkingSpotModel;
import com.api.parking_control.repositories.CarRepository;
import com.api.parking_control.repositories.OwnerRepository;
import com.api.parking_control.repositories.ParkingSpotRepository;

@Service
public class OwnerService {
    
	@Autowired
    private OwnerRepository ownerRepository;

	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private ParkingSpotRepository parkingSpotRepository;
    
    
    @Transactional
    public OwnerModel save(OwnerModel ownerModel) {
        return ownerRepository.save(ownerModel);
    }
    
    public boolean existsByDocument(String document) {
        return ownerRepository.existsByDocument(document);
    }

    public Page<OwnerModel> findAll(Pageable pageable) {
        return ownerRepository.findAll(pageable);
    }

    public Optional<OwnerModel> findById(UUID id) {
        return ownerRepository.findById(id);
    }

    @Transactional
    public void delete(OwnerModel ownerModel) {
        ownerRepository.delete(ownerModel);
    }

	public OwnerModel saveOwner(OwnerDto ownerDto) {

		var ownerModel = new OwnerModel();
		ownerModel.setName(ownerDto.name());
		ownerModel.setDocument(ownerDto.document());
		ownerModel.setApartment(ownerDto.apartment());
		ownerModel.setBlock(ownerDto.block());
		ownerModel.setRole(ownerDto.role());
		
		Set<CarModel> cars = carRepository.findAllById(ownerDto.carId()).stream().collect(Collectors.toSet());
		cars.forEach(car -> car.setOwner(ownerModel));

		Set<ParkingSpotModel> spots = parkingSpotRepository.findAllById(ownerDto.parkingSpotId()).stream().collect(Collectors.toSet());
		spots.forEach(spot -> spot.setOwner(ownerModel));
		
		ownerModel.setCars(cars);
		ownerModel.setParkingSpots(spots);
		return ownerRepository.save(ownerModel);
	}

}
