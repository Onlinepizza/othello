
package lab2oomu.View;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * This class represents a square in the graphical representation of the gameboard.
 * @author Oscar
 */
public class SquareTile extends Shape {

    private Rectangle rect;
    private int x, y;
    boolean selected;
    
    /**
     * This constructor creates a new squaretile on the given coordinate.
     * @param x
     * @param y 
     */
    public SquareTile(int x, int y) {
        this.rect = new Rectangle(50, 50, Color.TRANSPARENT);
        this.x = x;
        this.y = y;
        this.selected = false;
    }

    public Rectangle getShape() {
        return this.rect;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
    
    /**
     * This method highlights the squaretile by changing its color.
     */
    public void highlight() {
        this.selected = true;
        this.getShape().setStroke(Color.WHITE);
    }

    public void deselect() {
        this.selected = false;
        this.getShape().setStroke(Color.BLACK);
    }

    @Override
    public com.sun.javafx.geom.Shape impl_configShape() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
