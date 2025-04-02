package com.api.parking_control.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.parking_control.models.OwnerModel;

@Repository
public interface OwnerRepository extends JpaRepository<OwnerModel, UUID>{

}
