package com.api.parking_control.controllers;


import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.parking_control.dtos.CarDto;
import com.api.parking_control.models.CarModel;
import com.api.parking_control.services.CarService;
import com.api.parking_control.services.OwnerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cars")
public class CarController {
    @Autowired
    private CarService carService;

    @Autowired
    private OwnerService ownerService;

    @PostMapping
    public ResponseEntity<Object> saveCar(@RequestBody @Valid CarDto carDto) {
        var ownerOptional = ownerService.findById(carDto.getOwnerId());
        if (ownerOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Owner not found");
        }
        var carModel = new CarModel();
        BeanUtils.copyProperties(carDto, carModel);
        carModel.setOwner(ownerOptional.get());
        return ResponseEntity.status(HttpStatus.CREATED).body(carService.save(carModel));
    }
    
    @GetMapping
    public ResponseEntity<Page<CarModel>> getAllCars(Pageable pageable){
    	return ResponseEntity.status(HttpStatus.OK).body(carService.findAll(pageable));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Object> getCarById(@PathVariable(value = "id") UUID id){
    	Optional<CarModel> carOptional = carService.findById(id);
    	if(!carOptional.isPresent()) {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car not found for id: " + id);
    	}
    	return ResponseEntity.status(HttpStatus.OK).body(carOptional.get());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCar(@PathVariable(value = "id") UUID id){
    	Optional<CarModel> carOptional = carService.findById(id);
    	if(!carOptional.isPresent()) {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car not found for id: " + id);
    	}
    	carService.delete(carOptional.get());
    	return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Car deleted successfully");
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCar(@PathVariable(value = "id") UUID id, @RequestBody @Valid CarDto carDto){
    	Optional<CarModel> carOptional = carService.findById(id);
    	if(!carOptional.isPresent()) {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car not found for id: " + id);
    	}
    	
    	var ownerOptional = ownerService.findById(carDto.getOwnerId());
    	var carModel = new CarModel();
    	BeanUtils.copyProperties(carDto, carModel);
    	carModel.setId(carOptional.get().getId());
    	carModel.setOwner(ownerOptional.get());
    	return ResponseEntity.status(HttpStatus.OK).body(carService.save(carModel));
    }
}