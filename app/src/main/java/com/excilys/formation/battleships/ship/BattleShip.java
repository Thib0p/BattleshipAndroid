package com.excilys.formation.battleships.ship;

public class BattleShip extends AbstractShip {
	public BattleShip(){
		super(4,"Battleship",'B',Orientation.NORTH);
	}
	public BattleShip(Orientation orientation){
		super(4,"Battleship",'B',orientation);
	}
}
