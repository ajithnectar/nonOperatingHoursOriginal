package com.nectar.honeycomb.service.dto;

import java.util.List;

import com.nectar.honeycomb.service.enums.DaysOfWeek;

public class DayBreaksDTO {
	private DaysOfWeek day;
	private Long baseline;
    private List<BreakDTO> breaks;
    

	
	public DaysOfWeek getDay() {
		return day;
	}
	public void setDay(DaysOfWeek day) {
		this.day = day;
	}
	public List<BreakDTO> getBreaks() {
		return breaks;
	}
	public void setBreaks(List<BreakDTO> breaks) {
		this.breaks = breaks;
	}
	public Long getBaseline() {
		return baseline;
	}
	public void setBaseline(Long baseline) {
		this.baseline = baseline;
	}
    
}
