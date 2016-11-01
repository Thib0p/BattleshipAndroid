package com.excilys.formation.battleships.android.ui.ships;

import com.excilys.formation.battleships.android.ui.ships.DrawableShip;
import com.excilys.formation.battleships.ship.AbstractShip;
import com.excilys.formation.battleships.ship.BattleShip;

import java.util.HashMap;
import java.util.Map;


import battleships.formation.excilys.com.battleships.R;

/**
 * Created by Thibault on 01/11/2016.
 */

public class DrawableBattleship extends BattleShip implements DrawableShip {
    private static final Map<AbstractShip.Orientation, Integer> DRAWABLES = new HashMap<>();
    static
    {
        DRAWABLES.put(AbstractShip.Orientation.NORTH, R.drawable.battleship_n);
        DRAWABLES.put(AbstractShip.Orientation.EAST,R.drawable.battleship_e);
        DRAWABLES.put(AbstractShip.Orientation.SOUTH,R.drawable.battleship_s);
        DRAWABLES.put(AbstractShip.Orientation.WEST, R.drawable.battleship_w);
    }

    @Override
    public int getDrawable() {
        return DRAWABLES.get(getShipOrientation());
    }
}