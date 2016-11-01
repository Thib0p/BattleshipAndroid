package com.excilys.formation.battleships.ship;

public class Cruiser extends AbstractShip {
	public Cruiser(){
		super(3,"Cruiser",'H',Orientation.NORTH);
	}
	public Cruiser(Orientation orientation){
		super(3,"Cruiser",'H',orientation);
	}
}
