package com.nectar.ticks.serviceImpl;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nectar.honeycomb.service.beans.Break;
import com.nectar.honeycomb.service.beans.Building;
import com.nectar.honeycomb.service.beans.DayBreaks;
import com.nectar.honeycomb.service.beans.Profile;
import com.nectar.honeycomb.service.dto.BreakDTO;
import com.nectar.honeycomb.service.dto.BuildingOperatingHoursRequest;
import com.nectar.honeycomb.service.dto.DayBreaksDTO;
import com.nectar.honeycomb.service.dto.StoreDTO;
import com.nectar.ticks.repository.BuildingRepository;
import com.nectar.ticks.repository.DayBreaksRepository;
import com.nectar.ticks.repository.ProfileRepository;
import com.nectar.ticks.service.NonOperatingHoursService;


@Service
public class NonOperatingHoursServiceImpl implements NonOperatingHoursService {
	
	@Autowired
	private BuildingRepository buildingRepository;
	
	@Autowired
	private DayBreaksRepository dayBreaksRepository;

	@Autowired
	private ProfileRepository profileRepository;
	
	
	@Override
	public ResponseEntity<String> addOperatingHoursOfBuilding(StoreDTO storeDTO) {

		Optional<Building> existingBuildingOpt = buildingRepository.findByName(storeDTO.getName());
		if(existingBuildingOpt.isPresent()) {
			Building building = existingBuildingOpt.get();
			
			if(storeDTO.getProfile() != null) {
				Profile newProfile = createNewProfile(storeDTO.getProfile(), storeDTO.isActive() ,building);
				
				for (DayBreaksDTO dayBreakDTO : storeDTO.getDayBreaksDTO()) {
						DayBreaks dayBreak = new DayBreaks();
						dayBreak.setBaseline(dayBreakDTO.getBaseline());
						dayBreak.setDayOfWeek(dayBreakDTO.getDay());
						dayBreak.setProfile(newProfile);
						List<Break> newBreaks = createBreaksFromDTO(dayBreakDTO.getBreaks());
						dayBreak.setBreaks(newBreaks);
						dayBreaksRepository.save(dayBreak);
				}
				
			}else {
				//TODO if request doesn't contain profile name and active set this building hours to default
				System.out.println("profile name is not provided");
			}
		}else {
			System.out.println("building with Name is not find in DB");
		}
		
		return ResponseEntity.ok("saved to db");
	}

	private List<Break> createBreaksFromDTO(List<BreakDTO> breaksDTOs) {
		List<Break> newBreaks = new ArrayList<>();
			for (BreakDTO breakDTO : breaksDTOs) {
				Break timeBreak = new Break();
				timeBreak.setStartTime(formatDateTime(breakDTO.getStartTime()));
				timeBreak.setEndTime(formatDateTime(breakDTO.getEndTime()));
	
				newBreaks.add(timeBreak);
			}
		return newBreaks;
	}

	private Timestamp formatDateTime(Time date) {
	    DateTime dateTime = new DateTime(DateTimeZone.UTC).withMillis(date.getTime());
	    return new Timestamp(dateTime.getMillis());
	}
	private Profile createNewProfile(String profileName, boolean active, Building building) {
		Profile profile = new Profile();
		profile.setName(profileName);
		profile.setActive(active);
		profile.setBuilding(building);
		return profile;
	}

	@Override
	public ResponseEntity<String> updateOperatingHoursOfBuilding(StoreDTO request) {
		 	Profile profile = null;
		    Optional<Profile> existingProfile = profileRepository.findById(request.getProfileId());
		    if (existingProfile.isPresent()) {
		        profile = existingProfile.get();
		        profile.setActive(request.isActive()); 
		        profile.setName(request.getProfile());

		        List<DayBreaks> updatedDayBreaks = new ArrayList<>();
		        List<DayBreaksDTO> dayBreaksDtos = request.getDayBreaksDTO();
		        List<DayBreaks> existingDayBreaks = profile.getDayBreaks();
		        
		        for (int i = 0; i < dayBreaksDtos.size(); i++) {
		        	DayBreaks newDayBreak;
		        	
		        	if(i < existingDayBreaks.size()) {
		        		newDayBreak = existingDayBreaks.get(i); 
		        		newDayBreak.setId(existingDayBreaks.get(i).getId());
		        		List<Break> updatedBreaks = createBreaksWithNewDTO(newDayBreak.getBreaks(), dayBreaksDtos.get(i).getBreaks());
			            newDayBreak.setBreaks(updatedBreaks);
		        	}else {
		        		newDayBreak = new DayBreaks();
		        		newDayBreak.setBreaks(createBreaks(dayBreaksDtos.get(i).getBreaks()));
		        	}
		        	newDayBreak.setProfile(profile);
		            newDayBreak.setBaseline(dayBreaksDtos.get(i).getBaseline());
		            newDayBreak.setDayOfWeek(dayBreaksDtos.get(i).getDay());
		            updatedDayBreaks.add(newDayBreak);
		        }
		        
		        profile.setDayBreaks(updatedDayBreaks);
		        profileRepository.save(profile);
		    } else {
		        System.out.println("Profile with id is not present!!");
		    }

		    return ResponseEntity.ok("updated");
	}
	
	private List<Break> createBreaks(List<BreakDTO> breakDtos) {
		List<Break> updatedBreaks = new ArrayList<>();
		 for (int i = 0; i < breakDtos.size(); i++) {
			 
			 	BreakDTO breakDto = breakDtos.get(i);
				Break newBreak = new Break();
		        newBreak.setStartTime(Timestamp.valueOf(breakDto.getStartTime().toLocalTime().atDate(LocalDate.now())));
		        newBreak.setEndTime(Timestamp.valueOf(breakDto.getEndTime().toLocalTime().atDate(LocalDate.now())));
		        updatedBreaks.add(newBreak);
		 }
		return updatedBreaks;
	}


	private List<Break> createBreaksWithNewDTO(List<Break> existingBreaks, List<BreakDTO> breakDtos) {
	    List<Break> updatedBreaks = new ArrayList<>();
	    
	    for (int i = 0; i < breakDtos.size(); i++) {
	    	BreakDTO breakDto = breakDtos.get(i);
	            Break existingBreak = existingBreaks.get(i);
	            existingBreak.setId(existingBreak.getId());
	            existingBreak.setStartTime(Timestamp.valueOf(breakDto.getStartTime().toLocalTime().atDate(LocalDate.now())));
	            existingBreak.setEndTime(Timestamp.valueOf(breakDto.getEndTime().toLocalTime().atDate(LocalDate.now())));
	            updatedBreaks.add(existingBreak);
	        
	    }
	    
	    return updatedBreaks;
	}


	@Override
	public ResponseEntity<StoreDTO> findOperatingHoursOfBuilding(BuildingOperatingHoursRequest request ,String clientTimeZone) {
		StoreDTO newStoreDTO = new StoreDTO();
		Optional<Profile> existingProfileOpt = profileRepository.findProfileByBuildingIdAndProfileName(request.getProfileName(),request.getBuildingId());
		if(existingProfileOpt.isPresent()) {
			Profile profile = existingProfileOpt.get();
			
			newStoreDTO.setActive(profile.getActive());
			newStoreDTO.setName(profile.getBuilding().getName());
			newStoreDTO.setProfile(profile.getName());
			newStoreDTO.setProfileId(profile.getId());
			
			List<DayBreaksDTO> dayBreaksDTOs = new ArrayList();
			for (DayBreaks dayBreaks : profile.getDayBreaks()) {
				DayBreaksDTO dayBreaksDTO = new DayBreaksDTO();
				dayBreaksDTO.setBaseline(dayBreaks.getBaseline());
				dayBreaksDTO.setDay(dayBreaks.getDayOfWeek());
				
				List<BreakDTO> breakDTOs = new ArrayList();
				for(Break existingBreak : dayBreaks.getBreaks()) {
					BreakDTO breakDTO = new BreakDTO();
					breakDTO.setStartTime(convertToClientTimeZone(existingBreak.getStartTime(), clientTimeZone));
					breakDTO.setEndTime(convertToClientTimeZone(existingBreak.getEndTime(), clientTimeZone));
					breakDTOs.add(breakDTO);
				}
				dayBreaksDTO.setBreaks(breakDTOs);
				dayBreaksDTOs.add(dayBreaksDTO);
			}
			
			
			newStoreDTO.setDayBreaksDTO(dayBreaksDTOs);
			
			
		}else {
			System.out.println("can't find the profile with name " + request.getProfileName());
		}
		
		return ResponseEntity.ok(newStoreDTO);
	}

	public static Time convertToClientTimeZone(Timestamp timestamp, String clientTimeZone) {
        ZonedDateTime zonedDateTime = timestamp.toInstant().atZone(ZoneId.of("UTC"));
        ZonedDateTime clientDateTime = zonedDateTime.withZoneSameInstant(ZoneId.of(clientTimeZone));
        return Time.valueOf(clientDateTime.toLocalTime());
    }
	
	@Override
	public ResponseEntity<String> deleteOperatingHoursOfBuilding(BuildingOperatingHoursRequest request) {
		Optional<Profile> existingProfile = profileRepository.findProfileByBuildingIdAndProfileName(request.getProfileName(),request.getBuildingId());
	    if(!existingProfile.isPresent()) {
	    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body("profile not exist with given name");
	    }
		
		profileRepository.deleteById(existingProfile.get().getId());
		return ResponseEntity.ok("deleted");
	}

}
