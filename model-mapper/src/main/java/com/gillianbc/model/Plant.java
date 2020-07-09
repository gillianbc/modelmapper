package com.gillianbc.model;


public class Plant {
	private String plantName;

	public String getPlantName() {
		return plantName;
	}

	public void setPlantName(String plantName) {
		this.plantName = plantName;
	}

	@Override
	public String toString() {
		return "Plant [plantName=" + plantName + "]";
	}
	
	
}
