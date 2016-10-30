package com.excilys.formation.battleships; /**
 * Created by Thibault on 28/09/2016.
 */
import com.excilys.formation.battleships.ship.*;

        import java.io.*;
import java.util.*;

public class Game {

    /* ***
     * Constants
     */
    public static final File SAVE_FILE = new File("savegame.dat");

    /* ***
     * Attributes
     */
    private Player player1;
    private AIPlayer player2;
    private Scanner sin;
    private final static int MAX_MAX_SHIPS =5;

    /* ***
     * Methods
     */
    public Game() {

    }

    public Game init() {

        if (!loadSave()) {
            // init attributes
            System.out.println("entre ton nom:");

            sin = new Scanner(System.in);
            String username = sin.nextLine();
            Board b1, b2;
            b1 = new Board(username);
            b2 = new Board("AI");
            this.player1 = new Player(b1,b2,createDefaultShips());
            this.player2 = new AIPlayer(b2,b1,createDefaultShips());

            b1.print();
            // place player ships
            player1.putShips();
            player2.putShips();
        }
        return this;
    }

    public void run() {
        int[] coords = new int[2];
        Board b1 = player1.board;
        IBoard.Hit hit;

        // main loop
        b1.print();
        boolean done;
        do {
            hit = player1.sendHit(coords);
            boolean strike = hit != IBoard.Hit.MISS;
            player1.setHit(strike, coords[0], coords[1]);
            done = updateScore();
            b1.print();
            System.out.println(makeHitMessage(false /* outgoing hit */, coords, hit));

            save();

            if (!done && !strike) {
                do {
                    hit = player2.sendHit(coords);

                    strike = hit != IBoard.Hit.MISS;
                    player2.setHit(strike, coords[0], coords[1]);
                    if (strike) {
                        b1.print();
                    }
                    System.out.println(makeHitMessage(true /* incoming hit */, coords, hit));
                    done = updateScore();

                    if (!done) {
                        save();
                    }
                } while(strike && !done);
            }

        } while (!done);

        SAVE_FILE.delete();
        System.out.println(String.format("joueur %d gagne", player1.lose ? 2 : 1));
        sin.close();
    }


    private void save() {
 /*       try {
            // TODO bonus 10 : uncomment
//            if (!SAVE_FILE.exists()) {
//                SAVE_FILE.getAbsoluteFile().getParentFile().mkdirs();
//            }

            // TODO bonus 10 : serialize players

        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }

    private boolean loadSave() {
        /*if (SAVE_FILE.exists()) {
            try {
                // TODO bonus 10 : deserialize players

                return true;
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }*/
        return false;
    }

    private boolean updateScore() {
        for (Player player : new Player[]{player1, player2}) {
            int destroyed = 0;
            for (AbstractShip ship : player.getShips())
                if (ship.isSunk()) {
                    destroyed++;
                }

            player.destroyedCount = destroyed;
            player.lose = destroyed == player.getShips().length;
            if (player.lose) {
                return true;
            }
        }
        return false;
    }

    private String makeHitMessage(boolean incoming, int[] coords, IBoard.Hit hit) {
        String msg;
        ColorUtil.Color color = ColorUtil.Color.RESET;
        switch (hit) {
            case MISS:
                msg = hit.toString();
                break;
            case STRIKE:
                msg = hit.toString();
                color = ColorUtil.Color.RED;
                break;
            default:
                msg = hit.toString() + " coulé";
                color = ColorUtil.Color.RED;
        }
        msg = String.format("%s Frappe en %c%d : %s", incoming ? "<=" : "=>",
                ((char) ('A' + coords[1])),
                (coords[0] + 1), msg);
        return ColorUtil.colorize(msg, color);
    }

    private static List<AbstractShip> createDefaultShips() {
        return Arrays.asList(new AbstractShip[]{new Destroyer(), new Submarine(), new Submarine(), new BattleShip(), new Carrier()});
    }
/*    private static List<AbstractShip> createRandomShips() {



        return Arrays.asList(new AbstractShip[]{new Destroyer(), new Submarine(), new Submarine(), new BattleShip(), new Carrier()});
    }*/
    public static void main(String args[]) {
        new Game().init().run();
    }

}
