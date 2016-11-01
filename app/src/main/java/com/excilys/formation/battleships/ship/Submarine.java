package com.excilys.formation.battleships.ship;

public class Submarine extends AbstractShip {
	public Submarine(){
		super(3,"Submarine",'S',Orientation.NORTH);
	}
	public Submarine(Orientation orientation){
		super(3,"Submarine",'S',orientation);
	}
}
