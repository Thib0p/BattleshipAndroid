package com.excilys.formation.battleships;
import java.io.IOException;

import com.excilys.formation.battleships.ship.*;

public class Board implements IBoard {
	private static final int DEFAULT_SIZE = 10;
	private String name;
	private int size;
	private ShipState ships[][];
	private Boolean strikes[][];
	public String getName()
	{
		return name;
	}
	public Board(String name, int size) {
		this.name = name;
		this.size = size;
		this.ships = new ShipState[size][size];
		this.strikes = new Boolean[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				this.strikes[i][j] = null;
			}
		}
	}

	public Board(String name) {
		this(name, DEFAULT_SIZE);
	}



	public void print() {
		FunctionHelpers.clearConsole();
		System.out.print("  ");
		for (int i = 0; i < size / 10; i++) {
			System.out.print(" ");
		}
		for (int i = 0; i < size; i++) {
			System.out.print(String.valueOf((char) (i + 65)) + " ");
		}
		System.out.print("       ");
		for (int i = 0; i < size / 10; i++) {
			System.out.print(" ");
		}
		for (int i = 0; i < size; i++) {
			System.out.print(String.valueOf((char) (i + 65)) + " ");
		}
		System.out.print("\n");
		for (int i = 0; i < size; i++) {
			System.out.print(i + 1);
			for (int j = 0; j < size / 10 - Math.floor((i + 1) / 10) + 1; j++) {
				System.out.print(" ");
			}
			for (int j = 0; j < size; j++) {
				if(ships[i][j]!=null)
					System.out.print(ships[i][j].toString() + " ");
				else 
					System.out.print("." + " ");
			}
			System.out.print("     ");
			System.out.print(i + 1);
			for (int j = 0; j < size / 10 - Math.floor((i + 1) / 10) + 1; j++) {
				System.out.print(" ");
			}
			for (int j = 0; j < size; j++) {
				if (strikes[i][j]==null) {
					System.out.print(". ");
				} 
				else if(strikes[i][j]) {
					System.out.print(ColorUtil.colorize("X",ColorUtil.Color.RED)+" ");
				}
				else
				{
					System.out.print(ColorUtil.colorize("X",ColorUtil.Color.WHITE)+" ");
				}

			}
			System.out.print("\n");
		}

	}

	public Boolean canPutShip(AbstractShip ship, int x, int y) {
		switch (ship.getShipOrientation()) {
		case NORTH:
			if (x - ship.getSize() < 0)
				return false;
			for (int i = 0; i < ship.getSize(); i++) {
				if (ships[x - i][y] != null)
					return false;
			}
			break;
		case SOUTH:
			if (x + ship.getSize() > size)
				return false;
			for (int i = 0; i < ship.getSize(); i++) {
				if (ships[x + i][y] != null)
					return false;
			}
			break;
		case EAST:
			if (y + ship.getSize() > size)
				return false;
			for (int i = 0; i < ship.getSize(); i++) {
				if (ships[x][y + i] != null)
					return false;
			}
			break;
		case WEST:
			if (y - ship.getSize() < 0)
				return false;
			for (int i = 0; i < ship.getSize(); i++) {
				if (ships[x][y - i] != null)
					return false;
			}
			break;
		}
		return true;
	}

	public void putShip(AbstractShip ship, int x, int y) throws FunctionHelpers.ShipException{
		if(canPutShip(ship, x, y))
		{
			switch(ship.getShipOrientation()){
			case NORTH:
				for (int i = 0; i < ship.getSize(); i++) {
					ships[x-i][y] = new ShipState(ship);
				}
				break;
			case SOUTH:
				for (int i = 0; i < ship.getSize(); i++) {
					ships[x+i][y] = new ShipState(ship);
				}
				break;
			case EAST:
				for (int i = 0; i < ship.getSize(); i++) {
					ships[x][y+i] = new ShipState(ship);
				}
				break;
			case WEST:
				for (int i = 0; i < ship.getSize(); i++) {
					ships[x][y-i] = new ShipState(ship);
				}
				break;
			}
		}
		else
		{
			throw new FunctionHelpers.ShipException();
		}
	}

	public boolean hasShip(int x, int y) {
		if (ships[x][y]!=null)
			return true;
		else
			return false;
	}

	public void setHit(boolean hit, int x, int y) {
		strikes[x][y] = hit;
	}

	public Boolean getHit(int x, int y) {
		return strikes[x][y];
	}

	public int getSize() {
		return size;
	}
	public Hit sendHit(int x, int y) throws FunctionHelpers.ShipAlreadyStruck {
		if(!hasShip(x, y)) {
			return(Hit.MISS);
		}
		if(ships[x][y].isStruck())
		{
			throw new FunctionHelpers.ShipAlreadyStruck();
		}
		ships[x][y].addStrike();
		if(ships[x][y].isSunk())
		{
			return Hit.fromInt(ships[x][y].getShip().getSize());
		}
		else
		{
			return Hit.STRIKE;
		}
		
		
	}
}
