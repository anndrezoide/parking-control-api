package com.api.parking_control.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
}
