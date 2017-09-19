
package lab2oomu.View;


import lab2oomu.Model.GameGrid;


/**
 * This interfgace will be implemented by the object that observe
 * changes made to the logical part of the game.
 * @author Jonathan
 */
public interface Observer {
    public void update(GameGrid table);
}
