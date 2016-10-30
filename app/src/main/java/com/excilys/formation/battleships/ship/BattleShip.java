package com.excilys.formation.battleships.ship;

public class BattleShip extends AbstractShip {
	public BattleShip(){
		super(4,"Battleship",'B',null);
	}
	public BattleShip(Orientation orientation){
		super(4,"Battleship",'B',orientation);
	}
}
