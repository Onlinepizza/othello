
package lab2oomu.Model;

import lab2oomu.View.Observer;


/**
 *
 * @author Jonathan
 */

public interface LogicSubject {
    /**
     * 
     * @param o
     * This method adds a new observers to the list of observers stored in the subject
     */
    public void Register(Observer o);
    /**
     * this method notifies the observers when a change has been made to the subject.
     */
    public void notifyObservers();
}
