package com.excilys.formation.battleships;
import com.excilys.formation.battleships.ship.*;

import java.io.Serializable;
import java.util.List;

public class AIPlayer extends Player {
    private BattleShipsAI ai;
    public AIPlayer(Board ownBoard, Board opponentBoard, List<AbstractShip> ships) {
        super(ownBoard, opponentBoard, ships);
        ai = new BattleShipsAI(ownBoard, opponentBoard);
    }
    @Override
    public void putShips(){
        ai.putShips(ships);
    }

    @Override
    public IBoard.Hit sendHit(int[] coords) {
        return ai.sendHit(coords);
    }
    // TODO AIPlayer must not inherit "keyboard behavior" from player. Call ai instead.
}