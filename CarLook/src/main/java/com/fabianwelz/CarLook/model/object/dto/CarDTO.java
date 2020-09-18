package com.fabianwelz.CarLook.model.object.dto;

public class CarDTO {
private String brand;
private String year;
private String info;
private String email;
private int id;

public CarDTO() {
	brand="";
	year="";
	info="";
	email="";
	id=0;
}
public String getBrand() {
	return brand;
}
public void setBrand(String brand) {
	this.brand = brand;
}
public String getYear() {
	return year;
}
public void setYear(String year) {
	this.year = year;
}
public String getInfo() {
	return info;
}
public void setInfo(String info) {
	this.info = info;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
@Override
public String toString() {
    return "CarDTO{" + "brand=" + brand + ", year=" + year + ", info=" + info + '}';
}


}
