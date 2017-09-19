package lab2oomu.Model;

/**
 * This class represents a coordinate
 * @author S143975
 */

public class Coordinate {
    
    private final int x;
    private final int y;
    
    /**
     * @param ix
     * @param iy 
     * This class constructor accepts an x aswells as an y value 
     * that will represent a coordinate
     */
    public Coordinate(int ix, int iy) {
        this.x = ix;
        this.y = iy;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public enum Direction {
        North, NorthEast, East, SouthEast, South, SouthWest, West, NorthWest
    };
}
