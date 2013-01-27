package com.uTests.general;

public class Car {
	
	private long id;
	private String carManufacture;
	private int serialNumber;
	private String color;
	
	public Car(){
		id = 0;
		carManufacture = "NotDefined";
		serialNumber = 0;
		color = "NotDefined";
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCarManufacture() {
		return carManufacture;
	}

	public void setCarManufacture(String carManufacture) {
		this.carManufacture = carManufacture;
	}

	public int getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}
