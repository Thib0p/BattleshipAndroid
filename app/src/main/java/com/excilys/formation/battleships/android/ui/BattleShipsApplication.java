package com.excilys.formation.battleships.android.ui;

import android.app.Application;

import com.excilys.formation.battleships.AIPlayer;
import com.excilys.formation.battleships.Board;
import com.excilys.formation.battleships.Player;
import com.excilys.formation.battleships.ship.AbstractShip;

import java.util.Arrays;
import java.util.List;

public class BattleShipsApplication extends Application {

    /* ***
     * Attributes
     */
    private static BoardController mBoard;
    private static Board mOpponentBoard;
    private static Player[] mPlayers;
    private static Game mGame;

    // ...


    /* ***
     * Lifecycle
     */
    // ...
    public void onCreate()
    {
        mGame = new Game();
    }
    /* ***
     * Methods
     */
    // ...
    public static Game getGame() {
        return mGame;
    }

    public static BoardController getBoard() {
        return mBoard;
    }

    public static Board getOpponentBoard() {
        return mOpponentBoard;
    }

    public static Player[] getPlayers() {
        return mPlayers;
    }

    public class Game {
        private Player mPlayer1;
        private Player mPlayer2;
        /* ***
         * Methods
         */
        public Game() {
        }

        public Game init(String playerName) {

            Board b = new Board(playerName);
            mBoard = new BoardController(b);
            mOpponentBoard = new Board("IA");

            mPlayer1 = new Player(b, mOpponentBoard, createDefaultShips());
            mPlayer2 = new AIPlayer(mOpponentBoard, b, createDefaultShips());

            // place player ships
            mPlayer1.putShips();
            mPlayer2.putShips();
            mPlayers = new Player[] {mPlayer1, mPlayer2};

            return this;
        }

        private List<AbstractShip> createDefaultShips() {
            AbstractShip[] ships = new AbstractShip[0];

            // TODO uncomment me
            // ships = new AbstractShip[]{new DrawableDestroyer(), new DrawableSubmarine(), new DrawableSubmarine(), new DrawableBattleship(), new DrawableCarrier()};
            return Arrays.asList(ships);
        }
    }
}
