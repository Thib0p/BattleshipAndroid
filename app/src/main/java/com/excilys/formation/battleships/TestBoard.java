package com.excilys.formation.battleships;
import com.excilys.formation.battleships.ship.AbstractShip.Orientation;
import com.excilys.formation.battleships.ship.*;

public class TestBoard {
	public static void main(String[] args) {
		Board myBoard = new Board("hello");
		Orientation CS = Orientation.SOUTH;
		Orientation CW = Orientation.WEST;
		Carrier myCarrier = new Carrier(CS);
		Destroyer myDestroyer = new Destroyer(CW);

		AbstractShip[] ships = { new Destroyer(), new Submarine(),
				new Submarine(), new BattleShip(), new Carrier() };
		InputHelper.ShipInput a;
		int i = 0;
		myBoard.print();
		while (i < 1) {
			a = InputHelper.readShipInput();
			ships[i].setShipOrientation(a.orientation);
			try {
				myBoard.putShip(ships[i], a.y, a.x);
				i++;
				myBoard.print();
			} catch (FunctionHelpers.ShipException e) {
				// nothing to do here

			}

		}
		InputHelper.CoordInput b;
		IBoard.Hit h;
		while (true) {
			System.out.println("Entrez les coordonnées du bombardement :");
			b = InputHelper.readCoordInput();
			try {
				myBoard.setHit(myBoard.hasShip(b.y, b.x), b.y, b.x);
				h = myBoard.sendHit(b.y, b.x);
				myBoard.print();
				if (h.equals(h.DESTROYER) || h.equals(h.SUBMARINE)
						|| h.equals(h.BATTLESHIP) || h.equals(h.CARRIER)) {
					System.out.print("coulé : ");

				}
				if (h.equals(h.STRIKE)) {

				}
				System.out.println(h.toString());
			} catch (FunctionHelpers.ShipAlreadyStruck e) {
				
			}
		}
	}
}
