package com.excilys.formation.battleships.android.ui;

import com.excilys.formation.battleships.FunctionHelpers;
import com.excilys.formation.battleships.IBoard;
import com.excilys.formation.battleships.android.ui.ships.DrawableShip;
import com.excilys.formation.battleships.ship.AbstractShip;

import battleships.formation.excilys.com.battleships.R;

public class BoardController implements IBoard {

    /* ***
     * Public constants
     */
    public static final int SHIPS_FRAGMENT = 0;
    public static final int HITS_FRAGMENT = 1;

    /* ***
     * Attributes
     */
    private final IBoard mBoard;
    private final BoardGridFragment[] mFragments;
    private final BoardGridFragment mHitsFragment;
    private final BoardGridFragment mShipsFragment;



    public BoardController(IBoard board) {
        mBoard = board;
        mShipsFragment = BoardGridFragment.newInstance(SHIPS_FRAGMENT, mBoard.getSize(), R.drawable.ships_bg, R.string.board_ships_title);
        mHitsFragment = BoardGridFragment.newInstance(HITS_FRAGMENT, mBoard.getSize(), R.drawable.hits_bg, R.string.board_hits_title);

        mFragments = new BoardGridFragment[] {
            mShipsFragment, mHitsFragment
        };
    }

    public BoardGridFragment[] getFragments() {
        return mFragments;
    }

    public void displayHitInShipBoard(boolean hit, int x, int y) {
        mShipsFragment.putDrawable(hit ? R.drawable.hit : R.drawable.miss, x, y);
    }


    @Override
    public Hit sendHit(int x, int y) throws FunctionHelpers.ShipAlreadyStruck {
        Hit h = mBoard.sendHit(x,y);
        return h;
    }

    @Override
    public int getSize() {
        return mBoard.getSize();
    }

    @Override
    public void putShip(AbstractShip ship, int x, int y) {
        if (!(ship instanceof DrawableShip)) {
            throw new IllegalArgumentException("Cannot put a Ship that does not implement DrawableShip.");
        }


        AbstractShip.Orientation orientation = ship.getShipOrientation();
        mBoard.putShip(ship,y,x); //TODO change value of x,y to column line
        switch (orientation) {
            case NORTH:
                y = y - ship.getSize() + 1;
                break;
            case WEST:
                x = x - ship.getSize() + 1;
                break;

        }
        mShipsFragment.putDrawable(((DrawableShip) ship).getDrawable(),x,y);

    }

    @Override
    public Boolean canPutShip(AbstractShip ship, int x, int y)
    {
        return mBoard.canPutShip(ship,x,y);
    }
    @Override
    public boolean hasShip(int x, int y) {
        return mBoard.hasShip(x,y);
    }

    @Override
    public void setHit(boolean hit, int x, int y) {
        mBoard.setHit(hit,x,y);
        if(hit) {
            mHitsFragment.putDrawable(R.drawable.hit, x, y);
        }
        else
        {
            mHitsFragment.putDrawable(R.drawable.miss, x, y);
        }


    }

    @Override
    public Boolean getHit(int x, int y) {
        return mBoard.getHit(x,y);
    }
}
