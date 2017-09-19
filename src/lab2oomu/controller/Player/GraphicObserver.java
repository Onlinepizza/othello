
package lab2oomu.controller.Player;


import lab2oomu.Model.Coordinate;

/**
 * This Class observes changes in the graphical part of the game and
 * stores new coordinates whenever update is called by the subject.
 * @author S143975
 */
public class GraphicObserver implements LogicObserver{
    
    private Coordinate CurrentMove;


    public GraphicObserver() {        
        CurrentMove = null;
    }
    
    /**
     * stores a new coordinate.
     * @param coord 
     */
    @Override
    public void update(Coordinate coord) {
       CurrentMove = coord;
    }
    
    public Coordinate getCurrentMove(){
        return CurrentMove;
    }
    
    public void resetMove(){
        CurrentMove = null;
    }
}
