package com.excilys.formation.battleships.android.ui.ships;

import com.excilys.formation.battleships.ship.AbstractShip;
import com.excilys.formation.battleships.ship.Submarine;

import java.util.HashMap;
import java.util.Map;

import battleships.formation.excilys.com.battleships.R;

/**
 * Created by Thibault on 01/11/2016.
 */

public class DrawableSubmarine extends Submarine implements DrawableShip  {
    private static final Map<AbstractShip.Orientation, Integer> DRAWABLES = new HashMap<>();
    static
    {
        DRAWABLES.put(AbstractShip.Orientation.NORTH, R.drawable.submarine_n);
        DRAWABLES.put(AbstractShip.Orientation.EAST,R.drawable.submarine_e);
        DRAWABLES.put(AbstractShip.Orientation.SOUTH,R.drawable.submarine_s);
        DRAWABLES.put(AbstractShip.Orientation.WEST, R.drawable.submarine_w);
    }

    @Override
    public int getDrawable() {
        return DRAWABLES.get(getShipOrientation());
    }
}
