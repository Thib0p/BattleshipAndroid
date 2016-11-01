package com.excilys.formation.battleships.android.ui.ships;

import com.excilys.formation.battleships.ship.AbstractShip;
import com.excilys.formation.battleships.ship.Destroyer;

import java.util.HashMap;
import java.util.Map;

import battleships.formation.excilys.com.battleships.R;

/**
 * Created by Thibault on 01/11/2016.
 */

public class DrawableDestroyer extends Destroyer implements DrawableShip  {
    private static final Map<AbstractShip.Orientation, Integer> DRAWABLES = new HashMap<>();
    static
    {
        DRAWABLES.put(AbstractShip.Orientation.NORTH, R.drawable.destroyer_n);
        DRAWABLES.put(AbstractShip.Orientation.EAST,R.drawable.destroyer_e);
        DRAWABLES.put(AbstractShip.Orientation.SOUTH,R.drawable.destroyer_s);
        DRAWABLES.put(AbstractShip.Orientation.WEST, R.drawable.destroyer_w);
    }

    @Override
    public int getDrawable() {
        return DRAWABLES.get(getShipOrientation());
    }
}
