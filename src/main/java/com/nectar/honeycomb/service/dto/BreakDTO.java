package com.nectar.honeycomb.service.dto;

import java.sql.Time;

public class BreakDTO {

	private Time startTime;
    private Time endTime;
	
    public Time getStartTime() {
		return startTime;
	}
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}
	public Time getEndTime() {
		return endTime;
	}
	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

}
