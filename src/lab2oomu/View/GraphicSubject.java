
package lab2oomu.View;

import lab2oomu.Model.Coordinate;
import lab2oomu.controller.Player.LogicObserver;

/**
 * This class is the subject that will send the coordinates
 * recorded by the events in the graphical part of the game.
 * @author S143975
 */
public class GraphicSubject implements Subject {
    
    private LogicObserver player;
    private Coordinate CurrentMove;
    
    
    
    public GraphicSubject(){
   
    }
    
    public void SaveMove(Coordinate move){
        this.CurrentMove = move;
    }
    
    @Override
    public void Register(LogicObserver o) {
       this.player = o;
    }
    
    
    @Override
    public void notifyObservers() {
            player.update(CurrentMove);        
    }
    
}
