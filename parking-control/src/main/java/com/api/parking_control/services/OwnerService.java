package com.api.parking_control.services;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.parking_control.models.OwnerModel;
import com.api.parking_control.repositories.OwnerRepository;

@Service
public class OwnerService {

	@Autowired
	private OwnerRepository ownerRepository;

	@Transactional
	public Object save(OwnerModel ownerModel) {
		return ownerRepository.save(ownerModel);
	}

	public Page<OwnerModel> findAll(Pageable pageable) {
		return ownerRepository.findAll(pageable);
	}
	
	
}
