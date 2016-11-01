package com.excilys.formation.battleships.ship;

public class Destroyer extends AbstractShip {
	public Destroyer(){
		super(2,"Destroyer",'D',Orientation.NORTH);
	}
	public Destroyer(Orientation orientation){
		super(2,"Destroyer",'D',orientation);
	}
}
