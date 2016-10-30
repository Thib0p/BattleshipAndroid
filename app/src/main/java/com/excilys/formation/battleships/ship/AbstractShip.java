package com.excilys.formation.battleships.ship;

public class AbstractShip {
private int strikeCount; 
private int size;
private String name;

public void addStrike(){
	this.strikeCount+=1;
}
public boolean isSunk(){
	if(strikeCount>=size)
		return true;
	else
		return false;
}
public int getSize() {
	return size;
}
public void setSize(int size) {
	this.size = size;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public char getLabel() {
	return label;
}
public void setLabel(char label) {
	this.label = label;
}

public Orientation getShipOrientation() {
	return shipOrientation;
}
public void setShipOrientation(Orientation shipOrientation) {
	this.shipOrientation = shipOrientation;
}

private char label;
public enum Orientation {NORTH,SOUTH, EAST, WEST};
private Orientation shipOrientation;
public AbstractShip(int size, String name, char label,
		Orientation shipOrientation) {
	this.size = size;
	this.name = name;
	this.label = label;
	this.shipOrientation = shipOrientation;
	this.strikeCount=0;
}



}
