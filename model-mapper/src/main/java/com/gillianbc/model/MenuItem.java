package com.gillianbc.model;

public class MenuItem {
	private String skin;
	private boolean flavourful;
	private int seedCnt;
	private int whoMadeIt;
	private Plant growsOn;
	private int trackingNum;
	public String getSkin() {
		return skin;
	}
	public void setSkin(String skin) {
		this.skin = skin;
	}
	public boolean isFlavourful() {
		return flavourful;
	}
	public void setFlavourful(boolean flavourful) {
		this.flavourful = flavourful;
	}
	public int getSeedCnt() {
		return seedCnt;
	}
	public void setSeedCnt(int seedCnt) {
		this.seedCnt = seedCnt;
	}
	public int getWhoMadeIt() {
		return whoMadeIt;
	}
	public void setWhoMadeIt(int whoMadeIt) {
		this.whoMadeIt = whoMadeIt;
	}
	public Plant getGrowsOn() {
		return growsOn;
	}
	public void setGrowsOn(Plant growsOn) {
		this.growsOn = growsOn;
	}
	public int getTrackingNum() {
		return trackingNum;
	}
	public void setTrackingNum(int trackingNum) {
		this.trackingNum = trackingNum;
	}
	
	
	
}
