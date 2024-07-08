package com.nectar.ticks.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.nectar.honeycomb.service.dto.BuildingOperatingHoursRequest;
import com.nectar.honeycomb.service.dto.StoreDTO;

public interface NonOperatingHoursService {

	ResponseEntity<String> addOperatingHoursOfBuilding(StoreDTO storeDTO);

	ResponseEntity<String> updateOperatingHoursOfBuilding(StoreDTO storeDTO);

	ResponseEntity<StoreDTO> findOperatingHoursOfBuilding(BuildingOperatingHoursRequest request ,String clientTimeZone);

	ResponseEntity<String> deleteOperatingHoursOfBuilding(BuildingOperatingHoursRequest request);

	ResponseEntity<String> addListOfOperatingHoursOfBuilding(List<StoreDTO> storeDTOList);

}
