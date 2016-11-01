package com.excilys.formation.battleships.ship;

public class Carrier extends AbstractShip {
	public Carrier(){
		super(5,"Carrier",'C',Orientation.NORTH);
	}
	public Carrier(Orientation orientation){
		super(5,"Carrier",'C',orientation);
	}
}
