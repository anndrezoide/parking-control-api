package com.api.parking_control.controllers;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.parking_control.dtos.CarDto;
import com.api.parking_control.models.CarModel;
import com.api.parking_control.models.OwnerModel;
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
}