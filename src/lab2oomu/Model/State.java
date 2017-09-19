
package lab2oomu.Model;

import static lab2oomu.Model.State.states.BLACK;
import static lab2oomu.Model.State.states.WHITE;

/**
 * This class represents the state of a square in the logical gameboard.
 * @author S143975
 */
public class State {

    public states type;
    
    public State() {
        this.type = states.EMPTY;
    }

    public State(states Type) {
        this.type = Type;
    }
    
    /**
     * These are the states that a square can get into,
     * WHITE = white game piece on the square.
     * BLACK = black game piece on the square.
     * EMPTY = empty square.
     */
    public enum states {

        WHITE, BLACK, EMPTY
    }

    public states getState() {
        return type;
    }

    public void ChangeState(State tile) throws IllegalArgumentException {
        switch (type) {
            case WHITE:
                if (tile.type == states.BLACK) {
                    this.type = BLACK;
                } else {
                    throw new IllegalArgumentException("Invalid operation");
                }
                break;
            case BLACK:
                if (tile.type == states.WHITE) {
                    this.type = WHITE;
                } else {
                    throw new IllegalArgumentException("Invalid operation");
                }
                break;
            case EMPTY:
                if (tile.type == states.BLACK) {
                    this.type = BLACK;
                } else {
                    this.type = WHITE;
                }
                break;
        }
    }

    public void setState(states Type) {
        this.type = Type;
    }

    public State getOpponentState() {
        if (type == states.BLACK) {
            return new State(states.WHITE);
        } else {
            return new State(states.BLACK);
        }
    }
}
