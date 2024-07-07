package com.nectar.honeycomb.service.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Building {
	
	@Id       
	@Column(name = "identifier")
	private String identifier;
	
	@Column(name="name" ,unique = true)
	private String name;

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
