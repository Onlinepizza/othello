
package lab2oomu.Model;

import java.util.ArrayList;
import lab2oomu.View.Observer;
import lab2oomu.Model.Coordinate.Direction;
import lab2oomu.Model.State.states;

/**
 * This class represents the logical gameboard that stores the game pieces
 * @author S143975
 */
public class GameGrid implements LogicSubject {

    private gameState Game;
    private int nCoins;
    private int nBCoins;
    private int nWCoins;
    private State[][] table;
    private Observer listener;
    private ArrayList<Observer> observers;
    
    /**
     * This initializes the grid by placing 4 game pieces in the
     * middle of the grid.
     */
    public GameGrid() {
        observers = new ArrayList<Observer>();
        this.nCoins = 4;
        this.nBCoins = 2;
        this.nWCoins = 2;
        this.table = new State[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.table[i][j] = new State();

            }
        }
        this.table[3][3] = new State(states.WHITE);
        this.table[3][4] = new State(states.BLACK);
        this.table[4][3] = new State(states.BLACK);
        this.table[4][4] = new State(states.WHITE);
        this.Game = gameState.RUNNING;
    }
    
    @Override
    public void notifyObservers() {
        for(Observer listen : observers){
            listen.update(this);
        }
    }

    @Override
    public void Register(Observer o) {
        observers.add(o);
        listener = o;
    }
    
    /**
     * These states represents the states that a game can get into.
     */
    public enum gameState {
        RUNNING, DONE
    }

    public gameState getGameState() {
        return Game;
    }
    
    public void setGameStateDone(){
        this.Game = gameState.DONE;
    }
    
    public void setGameStateRunning(){
        this.Game = gameState.RUNNING;
    }

    public State getState(Coordinate coord) {
        return this.table[coord.getX()][coord.getY()];
    }
    
    /**
     * This method resets the logical game table.
     */
    public void resetTable(){
        int i, j;
        this.setGameStateRunning();
            this.nCoins = 4;
            this.nBCoins = 2;
            this.nWCoins = 2;
            for (i = 0; i < 8; i++) {
                for (j = 0; j < 8; j++) {
                    this.table[i][j].setState(states.EMPTY);
                }
            }
            this.table[3][3] = new State(states.WHITE);
            this.table[3][4] = new State(states.BLACK);
            this.table[4][3] = new State(states.BLACK);
            this.table[4][4] = new State(states.WHITE);
            notifyObservers();
    }
    
    /**
     * This method accepts a coordinate and a state which represents the activeplayer,
     * it uses these to place a game piece on the grid.
     * @param xy
     * @param player
     * @throws IllegalArgumentException 
     */
    public void alterTable(Coordinate xy, State player) throws IllegalArgumentException {
        int i, j;
        int nb = 0, nw = 0, tc = 0;
        
            //PLAY GAME
            try {               
                this.table[xy.getX()][xy.getY()].ChangeState(player);

                for (i = 0; i < 8; i++) {
                    for (j = 0; j < 8; j++) {

                        if (this.table[i][j].getState() == State.states.BLACK) {
                            nb++;
                            tc++;
                        }
                        if (this.table[i][j].getState() == State.states.WHITE) {
                            nw++;
                            tc++;
                        }
                    }
                }

                this.nCoins = tc;
                this.nBCoins = nb;
                this.nWCoins = nw;
                notifyObservers();

                if (nCoins == 64) {
                    Game = gameState.DONE;
                }

            } catch (IllegalArgumentException ex) {
                throw ex;
            }
        
    }
    
    /**
     * This method return wether or not there is a 
     * gamepiece with the same color on the specified coordinate.
     * @param coord
     * @param player
     * @return 
     */
    public boolean contains(Coordinate coord, State player) {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (coord.getX() == j && coord.getY() == i && this.table[j][i].type == player.type) {
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * Checks if the table is empty on a specific coordinate
     * @param coord
     * @return 
     */
    public boolean isEmpty(Coordinate coord) {
        return this.table[coord.getX()][coord.getY()].getState() == states.EMPTY;
    }
    
    /**
     * This method takes a coordinate and a state representing the activeplayer,
     * and proceeds to explore every coordinate in one directon as long as the 
     * coordninate contains a opponent gamepiece, if a friendly game piece is
     * detected, then all pieces between the original and current coordinate is
     * converted to friendly tiles. It then returns to the original coordinate 
     * and executes the same procedure in every direction.
     * @param coord
     * @param currentCell
     * @param dir
     * @param player
     * @throws IllegalArgumentException 
     */
    public void changeStatesBetween(Coordinate coord, Coordinate currentCell, Direction dir, State player) throws IllegalArgumentException {
        Coordinate looper = getNeighbourCoordinate(coord, dir);
        if (this.table[looper.getX()][looper.getY()].type == player.type) {
            throw new IllegalArgumentException("Invalid move");
        }

        while (this.table[looper.getX()][looper.getY()].type != player.type) {
            this.alterTable(looper, player);
            looper = getNeighbourCoordinate(looper, dir);
        }
    }
    
    /**
     * This method checks wether or not a gamepiece has opponents next to it.
     * @param coord
     * @param player
     * @return 
     */
    public boolean hasOpponentNeighbours(Coordinate coord, State player) {
        for (Direction dir : Direction.values()) {
            if (contains(getNeighbourCoordinate(coord, dir), player.getOpponentState())) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * This method returns the closest coordinate in a given direction.
     * @param coord
     * @param dir
     * @return 
     */
    public Coordinate getNeighbourCoordinate(Coordinate coord, Direction dir) {
        Coordinate retur = null;
        switch (dir) {
            case North:
                retur = new Coordinate(coord.getX(), coord.getY() - 1);
                break;
            case NorthEast:
                retur = new Coordinate(coord.getX() + 1, coord.getY() - 1);
                break;
            case East:
                retur = new Coordinate(coord.getX() + 1, coord.getY());
                break;
            case SouthEast:
                retur = new Coordinate(coord.getX() + 1, coord.getY() + 1);
                break;
            case South:
                retur = new Coordinate(coord.getX(), coord.getY() + 1);
                break;
            case SouthWest:
                retur = new Coordinate(coord.getX() - 1, coord.getY() + 1);
                break;
            case West:
                retur = new Coordinate(coord.getX() - 1, coord.getY());
                break;
            case NorthWest:
                retur = new Coordinate(coord.getX() - 1, coord.getY() - 1);
                break;
        }

        return retur;
    }
    
    /**
     * Returns the number of white coins
     * @return 
     */
    public int getNWCoins() {
        return this.nWCoins;
    }
    
    /**
     * Returns the number of black coins
     * @return 
     */
    public int getNBCoins() {
        return this.nBCoins;
    }
    
    /**
     * Returns the number of coins
     * @return 
     */
    public int getNCoins() {
        return this.nCoins;
    }
    
    /**
     * Prints the table.
     * Only for debugging.
     * @return 
     */
    public void printTable() {
        for (int i = 0; i < 8; i++) {
            System.out.println(" ");
            for (int j = 0; j < 8; j++) {
                System.out.print("|" + i + j + this.table[i][j].type + "|");
            }
        }
    }
    
    /**
     * This method check wether or not there exists any valid moves gives a
     * state representing the player.
     * @param tile
     * @return 
     */
    public Boolean existsValidMove(State tile) {
        int x, y;
        for (x = 0; x < 8; x++) {
            for (y = 0; y < 8; y++) {
                Coordinate coord = new Coordinate(x, y);
                if (checkMove(coord, tile)) {
                    
                    return true;
                }
            }
        }      
        return false;
    }
    
    /**
     * This method checks if there are any valid moves for a 
     * specific coordinate.
     * @param coord
     * @param tile
     * @return 
     */
    public Boolean checkMove(Coordinate coord, State tile) {

        if (!this.isEmpty(coord)) {

            return false;
        }
        if (!this.hasOpponentNeighbours(coord, tile)) {

            return false;
        }

        State Opponent = tile.getOpponentState();

        for (Coordinate.Direction dir : Coordinate.Direction.values()) {

            Coordinate currentCell = coord;
            boolean isOpponentTile = false;
            boolean opponentTileFound = false;

            do {
                currentCell = this.getNeighbourCoordinate(currentCell, dir);
                if (isOpponentTile = this.contains(currentCell, Opponent)) {
                    opponentTileFound = true;
                }
            } while (isOpponentTile);

            if (opponentTileFound && this.contains(currentCell, tile)) {
                return true;
            }

        }
        return false;
    }
}
