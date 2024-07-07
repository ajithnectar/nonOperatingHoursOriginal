package com.nectar.honeycomb.service.beans;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Profile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "building_id", nullable = false)
	private Building building;
	
	@Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Boolean active;

    @OneToMany(mappedBy = "profile",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DayBreaks> dayBreaks;

    
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Building getBuilding() {
		return building;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public List<DayBreaks> getDayBreaks() {
		return dayBreaks;
	}

	public void setDayBreaks(List<DayBreaks> dayBreaks) {
		this.dayBreaks = dayBreaks;
	}
    
    
}
