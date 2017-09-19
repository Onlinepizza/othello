
package lab2oomu.controller.Player;


import lab2oomu.Model.Coordinate;

/**
 * This will be implemented by classes which observe changes in the
 * graphical part of the game.
 * @author S143975
 */
public interface LogicObserver {
    public void update(Coordinate coord);
}
