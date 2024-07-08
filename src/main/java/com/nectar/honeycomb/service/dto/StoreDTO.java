package com.nectar.honeycomb.service.dto;

import java.util.List;

public class StoreDTO {

	private Long profileId;
 	private String name;
    private String profile;
    private boolean active;
    private List<DayBreaksDTO> days;
    
    
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
	public List<DayBreaksDTO> getDays() {
		return days;
	}
	public void setDays(List<DayBreaksDTO> days) {
		this.days = days;
	}
	
    
    
}
