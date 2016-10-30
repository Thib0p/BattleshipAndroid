package com.excilys.formation.battleships;
import com.excilys.formation.battleships.ship.*;

import java.io.Serializable;
import java.util.*;

public class BattleShipsAI implements Serializable {

	/**
	 * grid size.
	 */
	private final int size;

	/**
	 * My board. My ships have to be put on this one.
	 */
	private final IBoard board;

	/**
	 * My opponent's board. My hits go on this one to strike his ships.
	 */
	private final IBoard opponent;

	/**
	 * Coords of last known strike. Would be a good idea to target next hits
	 * around this point.
	 */
	private int lastStrike[];

	/**
	 * If last known strike lead me to think the underlying ship has vertical
	 * placement.
	 */
	private Boolean lastVertical;

	/**
	 * 
	 * @param myBoard
	 *            board where ships will be put.
	 * @param opponentBoard
	 *            Opponent's board, where hits will be sent.
	 */
	public BattleShipsAI(IBoard myBoard, IBoard opponentBoard) {
		this.board = myBoard;
		this.opponent = opponentBoard;
		size = board.getSize();
	}

	/**
	 * Put the ships on owned board.
	 * 
	 * @param ships
	 *            the ships to put
	 */
	public void putShips(AbstractShip ships[]) {
		int x, y;
		AbstractShip.Orientation o;
		Random rnd = new Random();
		AbstractShip.Orientation[] orientations = AbstractShip.Orientation
				.values();
		for (AbstractShip s : ships) {
			do {
				o = orientations[rnd.nextInt(orientations.length)];
				x = rnd.nextInt(size);
				y = rnd.nextInt(size);
				s.setShipOrientation(o);
			} while (!board.canPutShip(s, x, y));
			// L'exception n'est de toute façon jamais levée puisque CanPutShip
			// est toujours verifiée précédemment.
			board.putShip(s, x, y);
		}
	}
	/**
	 * 
	 * @param coords
	 *            array must be of size 2. Will hold the coord of the send hit.
	 * @return the status of the hit.
	 */
	public IBoard.Hit sendHit(int[] coords) {
		int res[] = null;
		if (coords == null || coords.length < 2) {
			throw new IllegalArgumentException(
					"must provide an initialized array of size 2");
		}
		boolean a = true;
		IBoard.Hit hit = null;
		while (a) {
			try {
				// already found strike & orientation?
				if (lastVertical != null) {
					if (lastVertical) {
						res = pickVCoord();
					} else {
						res = pickHCoord();
					}

					if (res == null) {
						// no suitable coord found... forget last strike.
						lastStrike = null;
						lastVertical = null;
					}
				} else if (lastStrike != null) {
					// if already found a strike, without orientation
					// try to guess orientation
					res = pickVCoord();
					if (res == null) {
						res = pickHCoord();
					}
					if (res == null) {
						// no suitable coord found... forget last strike.
						lastStrike = null;
					}
				}
				if (lastStrike == null) {
					res = pickRandomCoord();
				}
				hit = opponent.sendHit(res[0], res[1]);
				a = false;
				board.setHit(hit != IBoard.Hit.MISS, res[0], res[1]);
			} catch (FunctionHelpers.ShipAlreadyStruck e) {

			}

		}
		if (hit != IBoard.Hit.MISS) {
			if (lastStrike != null) {
				lastVertical = guessOrientation(lastStrike, res);
			}
			lastStrike = res;
		}

		coords[0] = res[0];
		coords[1] = res[1];
		return hit;
	}

	/* ***
	 * Private
	 */
// CETTE FONCTION NE FONCTIONNANT PAS NOUS AVONS UTILISE BOARD.CANPUTSHIP 
	private boolean canPutShip(AbstractShip ship, int x, int y) {
		AbstractShip.Orientation o = ship.getShipOrientation();
		int dx = 0, dy = 0;
		if (o == AbstractShip.Orientation.EAST) {
			if (x + ship.getSize() >= this.size) {
				return false;
			}
			dx = 1;
		} else if (o == AbstractShip.Orientation.SOUTH) {
			if (y + ship.getSize() >= this.size) {
				return false;
			}
			dy = 1;
		} else if (o == AbstractShip.Orientation.NORTH) {
			if (y + 1 - ship.getSize() < 0) {
				return false;
			}
			dy = -1;
		} else if (o == AbstractShip.Orientation.WEST) {
			if (x + 1 - ship.getSize() < 0) {
				return false;
			}
			dx = -1;
		}

		int ix = x;
		int iy = y;

		for (int i = 0; i < ship.getSize(); ++i) {
			if (board.hasShip(ix, iy)) {
				return false;
			}
			ix += dx;
			iy += dy;
		}

		return true;
	}

	private boolean guessOrientation(int[] c1, int[] c2) {
		return c1[0] == c2[0];
	}

	private boolean isUndiscovered(int x, int y) {
		return x >= 0 && x < size && y >= 0 && y < size
				&& board.getHit(x, y) == null;
	}

	private int[] pickRandomCoord() {
		Random rnd = new Random();
		int x;
		int y;
		do {
			x = rnd.nextInt(size);
			y = rnd.nextInt(size);
		} while (!isUndiscovered(x, y));
		return new int[] { x, y };
	}

	/**
	 * pick a coord verically around last known strike
	 * 
	 * @return suitable coord, or null if none is suitable
	 */
	private int[] pickVCoord() {
		int x = lastStrike[0];
		int y = lastStrike[1];

		for (int iy : new int[] { y - 1, y + 1 }) {
			if (isUndiscovered(x, iy)) {
				return new int[] { x, iy };
			}
		}
		return null;
	}

	/**
	 * pick a coord horizontally around last known strike
	 * 
	 * @return suitable coord, or null if none is suitable
	 */
	private int[] pickHCoord() {
		int x = lastStrike[0];
		int y = lastStrike[1];

		for (int ix : new int[] { x - 1, x + 1 }) {
			if (isUndiscovered(ix, y)) {
				return new int[] { ix, y };
			}
		}
		return null;
	}
}