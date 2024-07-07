package com.nectar.ticks.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nectar.honeycomb.service.beans.Building;

public interface BuildingRepository extends JpaRepository<Building, String> {

	Optional<Building> findByName(String name);

}
