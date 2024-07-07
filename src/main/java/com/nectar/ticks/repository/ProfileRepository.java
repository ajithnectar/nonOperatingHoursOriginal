package com.nectar.ticks.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nectar.honeycomb.service.beans.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long>{
	
	 @Query("SELECT p FROM Profile p WHERE p.name = :name AND p.building.identifier = :buildingId")
	 Optional<Profile> findProfileByBuildingIdAndProfileName(@Param("name") String name, @Param("buildingId") String buildingIdentifier);
}
