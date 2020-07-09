package com.gillianbc.model;


public class PieceOfFruit {
	private String appearance;
	private boolean tasty;
	private int numOfPips;
	private int manufacturer;
	private Plant plant;
	private int shippingNumber;
	public String getAppearance() {
		return appearance;
	}
	public void setAppearance(String appearance) {
		this.appearance = appearance;
	}
	@Override
	public String toString() {
		return "PieceOfFruit [appearance=" + appearance + ", tasty=" + tasty + ", numOfPips=" + numOfPips
				+ ", manufacturer=" + manufacturer + ", plant=" + plant + ", shippingNumber=" + shippingNumber + "]";
	}
	public boolean isTasty() {
		return tasty;
	}
	public void setTasty(boolean tasty) {
		this.tasty = tasty;
	}
	public int getNumOfPips() {
		return numOfPips;
	}
	public void setNumOfPips(int numOfPips) {
		this.numOfPips = numOfPips;
	}
	public int getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(int manufacturer) {
		this.manufacturer = manufacturer;
	}
	public Plant getPlant() {
		return plant;
	}
	public void setPlant(Plant plant) {
		this.plant = plant;
	}
	public int getShippingNumber() {
		return shippingNumber;
	}
	public void setShippingNumber(int shippingNumber) {
		this.shippingNumber = shippingNumber;
	}
	
	
	
}
