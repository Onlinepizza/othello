
package lab2oomu.controller.Player;

import lab2oomu.Model.Coordinate;
import lab2oomu.Model.Coordinate.Direction;
import lab2oomu.Model.GameGrid;
import lab2oomu.Model.State;

/**
 * This abstract class contains methods to make valid moves.
 * @author S143975
 */
public abstract class Player {

    public String name;
    
    /**
     * This method accepts a GameGrid, a Coordinate, a State and uses these to validate 
     * and make moves.
     * @param table
     * @param coord
     * @param tile
     * @throws IllegalArgumentException 
     */
    public void makeMove(GameGrid table, Coordinate coord, State tile) throws IllegalArgumentException {
       
        
        if (!table.hasOpponentNeighbours(coord, tile)) {
            throw new IllegalArgumentException("Invalid move: No opponent neighbours");
        }

        if (!table.isEmpty(coord)) {
            throw new IllegalArgumentException("Invalid move: Occupied tile");
        }

        State Opponent = tile.getOpponentState();

        for (Direction dir : Direction.values()) {

            Coordinate currentCell = coord;
            boolean isOpponentTile = false;
            boolean opponentTileFound = false;

            do {
                currentCell = table.getNeighbourCoordinate(currentCell, dir);
                if (isOpponentTile = table.contains(currentCell, Opponent)) {
                    opponentTileFound = true;
                }
            } while (isOpponentTile);

            if (opponentTileFound && table.contains(currentCell, tile)) {
                try {
                    table.changeStatesBetween(coord, currentCell, dir, tile);
                    table.alterTable(coord, tile);
                } catch (IllegalArgumentException ex) {
                    throw ex;
                }

            }

        }
        throw new IllegalArgumentException("Invalid Move: No friendly coin on opposite side");
    }

    public String getName() {
        return this.name;
    }
    
    public abstract State getColor();
    
    public abstract Coordinate getMove();

}
