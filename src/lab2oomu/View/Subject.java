
package lab2oomu.View;

import lab2oomu.controller.Player.LogicObserver;

/**
 * Classes implementing this interface will dstribute information
 * about themselves to the observers assigned to them.
 * @author S143975
 */
public interface Subject {
    
    /**
     * assigns a new observer.
     * @param o 
     */
    public void Register(LogicObserver o);

    public void notifyObservers();

    
}
