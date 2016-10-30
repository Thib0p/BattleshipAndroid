package com.excilys.formation.battleships;
import com.excilys.formation.battleships.ship.*;

public class ShipState {
	private AbstractShip ship;
	private boolean struck;

	public ShipState(AbstractShip ship) {
		this.ship = ship;
	}

	public void addStrike() {
		struck = true;
		ship.addStrike();
	}

	public boolean isStruck() {
		return struck;
	}

	public AbstractShip getShip() {
		return ship;
	}

	public boolean isSunk() {
		return ship.isSunk();
	}

	public String toString() {
		if (struck)
			return ColorUtil.colorize(ship.getLabel(), ColorUtil.Color.RED);
		else
			return ColorUtil.colorize(ship.getLabel(), ColorUtil.Color.WHITE);
	}
}
