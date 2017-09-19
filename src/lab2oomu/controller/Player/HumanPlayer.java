
package lab2oomu.controller.Player;

import lab2oomu.Model.Coordinate;
import lab2oomu.Model.State;



/**
 * Represents the human player.
 * @author S143975
 */
public class HumanPlayer extends Player {

    private State Color;

    @Override
    public State getColor() {
        return Color;
    }
    private GraphicObserver observer;

    public HumanPlayer(State color, String playerName, GraphicObserver observer) {
        this.name = playerName;
        this.Color = color;
        this.observer = observer;
        
    }
    
    /**
     * This method overrides getmove in Player,
     * it gets a new coordinate from the GraphicObserverobject observer stored as an
     * attribute.
     * @return 
     */
    @Override
    public Coordinate getMove(){
        Coordinate co = observer.getCurrentMove();
        observer.resetMove();
        return co;
    }
}
