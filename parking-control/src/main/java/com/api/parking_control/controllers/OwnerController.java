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
import com.api.parking_control.dtos.OwnerDto;
import com.api.parking_control.models.CarModel;
import com.api.parking_control.models.OwnerModel;
import com.api.parking_control.services.CarService;
import com.api.parking_control.services.OwnerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/owners")
public class OwnerController {

	@Autowired
	private OwnerService ownerService;
	
	@Autowired
	private CarService carService;
	
	@PostMapping
	public ResponseEntity<Object> save(@RequestBody @Valid OwnerDto ownerDto){
		if(ownerService.existsByDocument(ownerDto.getDocument())){
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Owner's document is already in use!");
		}
		var ownerModel = new OwnerModel();
		
		BeanUtils.copyProperties(ownerDto, ownerModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(ownerService.save(ownerModel));
	}
	
	@GetMapping                                                     
	public ResponseEntity<Page<OwnerModel>> getAllOwners(Pageable pageable){
		return ResponseEntity.status(HttpStatus.OK).body(ownerService.findAll(pageable));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getOwnerById(@PathVariable(value = "id") UUID id){
		Optional<OwnerModel> ownerOptional = ownerService.findById(id);
		if(!ownerOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Owner not found for id: " + id);
		}
		return ResponseEntity.status(HttpStatus.OK).body(ownerOptional.get()); 
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteOwner(@PathVariable(value = "id") UUID id){
		Optional<OwnerModel> ownerOptional = ownerService.findById(id);
		if(!ownerOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Owner not found for id: " + id);
		}
		ownerService.delete(ownerOptional.get());
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Owner deleted successfully");
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateOwner(@PathVariable(value = "id") UUID id, @RequestBody @Valid OwnerDto ownerDto){
		Optional<OwnerModel> ownerOptional = ownerService.findById(id);
		if(!ownerOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Owner not found for id: " + id);
		}
		var ownerModel = new OwnerModel();
		BeanUtils.copyProperties(ownerDto, ownerModel);
		ownerModel.setId(ownerOptional.get().getId());
		return ResponseEntity.status(HttpStatus.OK).body(ownerService.save(ownerModel));
	}
	
}
