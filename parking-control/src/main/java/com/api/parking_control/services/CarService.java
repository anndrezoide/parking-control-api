package com.api.parking_control.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.parking_control.models.CarModel;
import com.api.parking_control.repositories.CarRepository;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    @Transactional
    public CarModel save(CarModel carModel) {
        return carRepository.save(carModel);
    }

    public Optional<CarModel> findById(UUID id) {
        return carRepository.findById(id);
    }
}
