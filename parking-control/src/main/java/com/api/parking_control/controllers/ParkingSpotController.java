package com.api.parking_control.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.parking_control.dtos.ParkingSpotDto;
import com.api.parking_control.models.ParkingSpotModel;
import com.api.parking_control.services.ParkingSpotService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600) //permite que seja acessado de qualquer fonte.
@RequestMapping("/parking-spot")
public class ParkingSpotController {
	
	@Autowired
	private ParkingSpotService parkingSpotService;

	@PostMapping
	public ResponseEntity<Object> saveParkingSpot(@RequestBody @Valid ParkingSpotDto parkingSpotDto){
		if(parkingSpotService.existsByLicensePlateCar(parkingSpotDto.getLicensePlateCar())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: License Plate Car is already in use!");
		}
		
		if(parkingSpotService.existsByParkingSpotNumber(parkingSpotDto.getParkingSpotNumber())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Parking Spot is already in use!");
		}
		
		if(parkingSpotService.existsByApartamentAndBlock(parkingSpotDto.getApartament(), parkingSpotDto.getBlock())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Parking spot already registred for apartament: " 
		+ parkingSpotDto.getApartament() 
		+ " and Block: " + parkingSpotDto.getBlock());
		}
		
		var parkingSpotModel = new ParkingSpotModel();
		BeanUtils.copyProperties(parkingSpotDto, parkingSpotModel); //transforma um dto em um model
		parkingSpotModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
		return ResponseEntity.status(HttpStatus.CREATED).body(parkingSpotService.save(parkingSpotModel));
		
	}
	
	@GetMapping                                                     //Outro modo de fazer paginação: getAllParkingSpot(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable)
	public ResponseEntity<Page<ParkingSpotModel>> getAllParkingSpot(Pageable pageable){
		return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.findAll(pageable));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getOneParkingSpot(@PathVariable(value = "id") UUID id){
		Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotService.findById(id);
		if(!parkingSpotModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ParkingSpot not found for id: " + id);
		}
		return ResponseEntity.status(HttpStatus.OK).body(parkingSpotModelOptional.get());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteOneParkingSpot(@PathVariable(value = "id") UUID id){
		Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotService.findById(id);
		if(!parkingSpotModelOptional.isPresent()){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found for id: " + id);
		}
		parkingSpotService.delete(parkingSpotModelOptional.get());
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Parking Spot deleted with success!");
	}
	
	@PutMapping("/{id}") //Existe duas formas de fazer.
	public ResponseEntity<Object> updateParkingSpot(@PathVariable(value = "id") UUID id, @RequestBody @Valid ParkingSpotDto parkingSpotDto){
		Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotService.findById(id);
		if(!parkingSpotModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found for id: " + id);
		}
		var parkingSpotModel = new ParkingSpotModel();
		BeanUtils.copyProperties(parkingSpotDto, parkingSpotModel);
		parkingSpotModel.setId(parkingSpotModelOptional.get().getId());
		parkingSpotModel.setRegistrationDate(parkingSpotModelOptional.get().getRegistrationDate());
		
		/* maneira 2 de fazer
		var parkingSpotModel2 = parkingSpotModelOptional.get();
		
		parkingSpotModel2.setParkingSpotNumber(parkingSpotDto.getParkingSpotNumber());
		parkingSpotModel2.setLicensePlateCar(parkingSpotDto.getLicensePlateCar());
		parkingSpotModel2.setModelCar(parkingSpotDto.getModelCar());
		parkingSpotModel2.setBrandCar(parkingSpotDto.getBrandCar());
		parkingSpotModel2.setColorCar(parkingSpotDto.getColorCar());
		parkingSpotModel2.setResponsibleName(parkingSpotDto.getResponsibleName());
		parkingSpotModel2.setApartament(parkingSpotDto.getApartament());
		parkingSpotModel2.setBlock(parkingSpotDto.getBlock());
		*/
		return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.save(parkingSpotModel));
	}

	@GetMapping("/by-color")
	public ResponseEntity<Object> getParkingSpotByColorCar(@RequestParam(required = false) String color){
		List<ParkingSpotModel> parkingSpotModelOptional = parkingSpotService.findByColor(color);
		if(parkingSpotModelOptional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found for color: " + color);
		}
		return ResponseEntity.status(HttpStatus.OK).body(parkingSpotModelOptional);
	}
	
	@GetMapping("/brand-car")
	public ResponseEntity<Object> getParkingSpotByBrandCar(@RequestParam(required = false) String brand){
		List<ParkingSpotModel> parkingSpotModelBrand = parkingSpotService.findByBrandCar(brand);
		if(parkingSpotModelBrand.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found for brand: " + brand);
		}
		return ResponseEntity.status(HttpStatus.OK).body(parkingSpotModelBrand);
	}
}
