package com.excilys.formation.battleships;
import com.excilys.formation.battleships.ship.*;

import java.io.Serializable;
import java.util.List;

public class Player {
    protected Board board;
    protected Board opponentBoard;
    public int destroyedCount;
    protected AbstractShip[] ships;
    public boolean lose;
    public String getName(){
        return board.getName();
    }
    public Player(Board board, Board opponentBoard, List<AbstractShip> ships) {
        this.board = board;
        this.ships = ships.toArray(new AbstractShip[0]);
        this.opponentBoard = opponentBoard;
    }

    /**
     * Read keyboard input to get ships coordinates. Place ships on given coodrinates.
     */
    public void putShips() {
        boolean done = false;
        int i = 0;

        do {
            AbstractShip s = ships[i];
            String msg = String.format("placer %d : %s(%d)", i + 1, s.getName(), s.getSize());
            System.out.println(msg);
            InputHelper.ShipInput res = InputHelper.readShipInput();
            ships[i].setShipOrientation(res.orientation);
            try {
                board.putShip(ships[i], res.y, res.x);
                i++;
            } catch (FunctionHelpers.ShipException e) {
                // nothing to do here

            }
            done = i == 5;

            board.print();

        } while (!done);
    }

    public IBoard.Hit sendHit(int[] coords) {
        boolean done=false;
        IBoard.Hit hit = null;
        do {

            System.out.println("où frapper?");
            InputHelper.CoordInput hitInput = InputHelper.readCoordInput();
            try {
                hit = this.opponentBoard.sendHit(hitInput.y, hitInput.x);
                this.board.setHit(hit != IBoard.Hit.MISS, hitInput.y, hitInput.x);
                coords[0] = hitInput.y;
                coords[1] = hitInput.x;
                done=true;
            }
            catch(FunctionHelpers.ShipAlreadyStruck e)
            {

            }
        } while (!done);
        return hit;
    }
    public IBoard.Hit sendHit() {
        int[] coords = new int[2];
        return sendHit(coords);
    }
    public void setHit(boolean b, int x, int y) {
        board.setHit(b,x,y);
    }
    public AbstractShip[] getShips() {
        return ships;
    }

    public void setShips(AbstractShip[] ships) {
        this.ships = ships;
    }
}