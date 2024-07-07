package com.nectar.honeycomb.service.beans;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.nectar.honeycomb.service.enums.DaysOfWeek;


@Entity
public class DayBreaks {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile;
	
	@Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DaysOfWeek dayOfWeek;

    @Column(nullable = false)
    private Long baseline;
    
    @OneToMany( fetch = FetchType.LAZY, orphanRemoval = false, cascade = CascadeType.ALL)
    private List<Break> breaks;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public DaysOfWeek getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(DaysOfWeek dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public Long getBaseline() {
		return baseline;
	}

	public void setBaseline(Long baseline) {
		this.baseline = baseline;
	}

	public List<Break> getBreaks() {
		return breaks;
	}

	public void setBreaks(List<Break> breaks) {
		this.breaks = breaks;
	}
    
    
}

