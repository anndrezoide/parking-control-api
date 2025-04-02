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

import com.api.parking_control.dtos.OwnerDto;
import com.api.parking_control.models.OwnerModel;
import com.api.parking_control.services.OwnerService;

@RestController
@RequestMapping("/owners")
public class OwnerController {

	@Autowired
	private OwnerService ownerService;
	
	@PostMapping
	public ResponseEntity<Object> save(@RequestBody OwnerDto ownerDto){
		var ownerModel = new OwnerModel();
		
		BeanUtils.copyProperties(ownerDto, ownerModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(ownerService.save(ownerModel));
	}
	
	@GetMapping                                                     
	public ResponseEntity<Page<OwnerModel>> getAllOwners(Pageable pageable){
		return ResponseEntity.status(HttpStatus.OK).body(ownerService.findAll(pageable));
	}
}
