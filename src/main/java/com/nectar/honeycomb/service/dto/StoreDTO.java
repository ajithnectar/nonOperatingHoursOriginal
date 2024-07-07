package com.nectar.honeycomb.service.dto;

import java.util.List;

public class StoreDTO {

	private Long profileId;
 	private String name;
    private String profile;
    private boolean active;
    private List<DayBreaksDTO> dayBreaksDTO;
    
    
	public Long getProfileId() {
		return profileId;
	}
	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public List<DayBreaksDTO> getDayBreaksDTO() {
		return dayBreaksDTO;
	}
	public void setDayBreaksDTO(List<DayBreaksDTO> dayBreaksDTO) {
		this.dayBreaksDTO = dayBreaksDTO;
	}
    
    
}
