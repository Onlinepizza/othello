
package lab2oomu.View;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import lab2oomu.Model.State.states;

/**
 * This class contains the coordinates and the graphical representaion
 * of a single game piece.
 * @author Jonathan
 */
public class Coin {

    private Circle graphicCoin;
    

    public Coin(states state) {
        if (state != states.EMPTY)
        graphicCoin = this.setCoin(state);
    }

    public Circle setCoin(states state) {
        Circle newCoin = new Circle(20);
        if(state.equals(states.BLACK)){
        newCoin.setFill(Color.BLACK);
        newCoin.setStrokeWidth(2);
        newCoin.setStroke(Color.WHITE);
            }
        else if (state.equals(states.WHITE)){
            newCoin.setFill(Color.WHITE);
            newCoin.setStrokeWidth(2);
            newCoin.setStroke(Color.BLACK);
            }
        return newCoin;
    }

    public Circle getShape() {
        return this.graphicCoin;
    }
    
    public void setColor(states state){
        if(state.equals(states.BLACK)){
            graphicCoin.setFill(Color.BLACK);  
        }else graphicCoin.setFill(Color.BLACK);
    }
    
    /**
     * Changes the color of the game piece
     */
    public void changeColor() {
        if (this.graphicCoin.getFill() == Color.BLACK) {
            this.graphicCoin.setFill(Color.WHITE);
            this.graphicCoin.setStroke(Color.BLACK);
            this.graphicCoin.setStrokeWidth(2);
        } else {
            this.graphicCoin.setFill(Color.BLACK);
            this.graphicCoin.setStroke(Color.WHITE);
            this.graphicCoin.setStrokeWidth(2);
        }
    }
}
