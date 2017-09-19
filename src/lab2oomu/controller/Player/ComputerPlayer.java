
package lab2oomu.controller.Player;


import java.util.Random;

import lab2oomu.Model.Coordinate;

import lab2oomu.Model.State;


/**
 * This class represents the computerplayer.
 * @author S143975
 */
public class ComputerPlayer extends Player {

    private State Color;

    @Override
    public State getColor() {
        return Color;
    }
    
    public ComputerPlayer(State color, String playerName) {
        this.name = playerName;
        this.Color = color;
        
    }
    
    /**
     * This method overrides getmove in Player,
     * it randomly generates a coordinate.
     * @return 
     */
    @Override
    public Coordinate getMove() {
        Random rand = new Random();
        int x = rand.nextInt(8);

        int y = rand.nextInt(8);

        return new Coordinate(x, y);
    }
    
}
