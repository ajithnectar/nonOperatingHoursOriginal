package com.nectar.ticks.resources.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nectar.honeycomb.service.dto.BuildingOperatingHoursRequest;
import com.nectar.honeycomb.service.dto.StoreDTO;
import com.nectar.ticks.service.NonOperatingHoursService;

@RestController
@RequestMapping("/nonOperatingHours")
public class NonOperatingHoursController {

	@Autowired
	public NonOperatingHoursService nonOperatingHoursService;
	
	@PostMapping("")
	public ResponseEntity<String> addOperatingHours( @RequestBody StoreDTO storeDTO){
		return nonOperatingHoursService.addOperatingHoursOfBuilding(storeDTO);
	}
	
	@PutMapping("")
	public ResponseEntity<String> updateOperatingHours(@RequestBody StoreDTO storeDTO){
		return nonOperatingHoursService.updateOperatingHoursOfBuilding(storeDTO);
	}
	
	@GetMapping("")
	public ResponseEntity<StoreDTO> findOperatingHours(@RequestBody BuildingOperatingHoursRequest request, @RequestHeader("Client-Timezone") String clientTimeZone){
		return nonOperatingHoursService.findOperatingHoursOfBuilding(request ,clientTimeZone);
	}
	
	@PostMapping("/delete")
	public ResponseEntity<String> deleteOperatingHours(@RequestBody BuildingOperatingHoursRequest request){
		return nonOperatingHoursService.deleteOperatingHoursOfBuilding(request);
	}
	
	@PostMapping("/bulk")
	public ResponseEntity<String> addOperatingHours( @RequestBody List<StoreDTO> storeDTOList){
		return nonOperatingHoursService.addListOfOperatingHoursOfBuilding(storeDTOList);
	}
	
}
