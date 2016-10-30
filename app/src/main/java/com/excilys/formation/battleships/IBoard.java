package com.excilys.formation.battleships;
import com.excilys.formation.battleships.ship.AbstractShip;
import java.util.NoSuchElementException;
public interface IBoard {
/**
*
* @return The size of the Board
*/
int getSize();
/**
* Put the given ship at the given coordinates
* @param ship The ship to place on the board
* @param x
* @param y
*/
void putShip(AbstractShip ship, int x, int y) throws FunctionHelpers.ShipException;
/**
* @param x
* @param y
* @return True if a ship is located at the given coords
*/
boolean hasShip(int x, int y);
/**
* Set the state of the hit at a given position
* @param hit true if the hit must be set to successful
* @param x
* @param y
*/
void setHit(boolean hit, int x, int y);
/**
* Get the state of a hit at the given position
* @param x
* @param y
* @return true if hit is a 'strike'
*/
Boolean getHit(int x, int y);

Boolean canPutShip(AbstractShip ship, int x, int y);
enum Hit {
    MISS(-1, "manqué"),
    STRIKE(-2, "touché"),
    DESTROYER(2, "Frégate"),
    SUBMARINE(3, "Sous-marin"),
    BATTLESHIP(4, "Croiseur"),
    CARRIER(5, "Porte-avion")
    ;
    private int value;
    private String label;
    Hit(int value, String label) {
        this.value = value;
        this.label = label;
    }

    public static Hit fromInt(int value) {
        for (Hit hit : Hit.values()) {
            if (hit.value == value) {
                return hit;
            }
        }
        throw new NoSuchElementException("no enum for value " + value);
    }

    public String toString() {
        return this.label;
    }
};
/**
* Sends a hit at the given position
* @param x
* @param y
* @return status for the hit (eg : strike or miss)
*/
Hit sendHit(int x, int y) throws FunctionHelpers.ShipAlreadyStruck ;
}