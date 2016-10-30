package com.excilys.formation.battleships;

import java.util.ArrayList;

import com.excilys.formation.battleships.ship.AbstractShip;
import com.excilys.formation.battleships.ship.BattleShip;
import com.excilys.formation.battleships.ship.Carrier;
import com.excilys.formation.battleships.ship.Destroyer;
import com.excilys.formation.battleships.ship.Submarine;

public class TestGame {
	private static void sleep(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		int sunkShips = 0;
		Board myBoard = new Board("Pirate Board");
		ArrayList<AbstractShip> ships = new ArrayList<AbstractShip>();
		ships.add(new Destroyer());
		ships.add(new Submarine());
		ships.add(new Submarine());
		ships.add(new BattleShip());
		ships.add(new Carrier());
		BattleShipsAI ai = new BattleShipsAI(myBoard, myBoard);
		AbstractShip[] shipsArray = new AbstractShip[ships.size()];
		ships.toArray(shipsArray);

		ai.putShips(shipsArray);
		int[] coords = new int[2];
		IBoard.Hit h;
		while (sunkShips < ships.size()) {

				h = ai.sendHit(coords);
				myBoard.print();
				if (h.equals(h.DESTROYER) || h.equals(h.SUBMARINE)
						|| h.equals(h.BATTLESHIP) || h.equals(h.CARRIER)) {
					System.out.print("coulé : ");
					++sunkShips;
				}
				if (h.equals(IBoard.Hit.STRIKE)) {
					System.out.println(h.toString());
				}
				System.out.println(h.toString());

				System.out.println(sunkShips) ;
				sleep(100);


		}
		sleep(100);
	}
}
