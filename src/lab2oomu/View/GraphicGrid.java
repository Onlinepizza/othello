
package lab2oomu.View;

import java.util.ArrayList;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;


import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import lab2oomu.Model.Coordinate;
import lab2oomu.Model.GameGrid;
import lab2oomu.Model.State;

/**
 * This Class contains the graphical representation of the grid on which the
 * game pieces will be placed.
 * @author Oscar
 */
public class GraphicGrid implements Observer {

    private GridPane pane;
    private Coordinate CurrentMove;
    private GraphicSubject subject;
    private ArrayList<Circle> coins;
    
    /**
     * Creates a new instance of the object graphicSubject, which will update
     * the graphical grid as the logical grid changes.
     * It also calls setGrid which initiates the graphics and creates the array
     * coins which will keep track of the shapes stored in the grid.
     */
    public GraphicGrid(){
        subject = new GraphicSubject();
        this.pane = this.setGrid();
        coins = new ArrayList<>();
    }
    
    public void setSubject(GraphicSubject subject){
        this.subject = subject;
    }
  
    public GridPane getPane() {
        return this.pane;
    }
    
    public GraphicSubject getSubject(){
        return subject;
    }
    
    public Coordinate getCurrentMove() {
        return this.CurrentMove;
    }
    
    /**
     * Initiates a grid which cointais Mouseeventhandlers
     * that throws the coordinate of the square that was clicked.
     * @return 
     */
    public GridPane setGrid() {
        int i, j;
        pane = new GridPane();
        pane.setAlignment(Pos.CENTER);

        for (i = 0; i < 8; i++) {
            for (j = 0; j < 8; j++) {
                SquareTile newSquare = new SquareTile(i, j);
                newSquare.getShape().setStroke(Color.BLACK);              

                newSquare.getShape().setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        subject.SaveMove(new Coordinate(newSquare.getX(), newSquare.getY()));
                        subject.notifyObservers();
                    }
                }
                );

                newSquare.getShape().setOnMouseEntered(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        newSquare.highlight();
                        newSquare.getShape().setOnMouseExited(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                newSquare.deselect();
                            }
                        }
                        );
                    }
                }
                );
                pane.add(newSquare.getShape(), i, j);
            }
        }
        return pane;
    }
    
    /**
     * This method updates the graphical grid from the object 
     * GameGrid which contains the logical representation of
     * the grid.
     * @param table 
     */
    @Override
    public void update(GameGrid table) {
        Platform.runLater(()->{          
            Coordinate newCoord = null;
            State newState = null;
            for(int i = 0; i < 8; i++){
                for(int j = 0; j < 8; j++){
                    newCoord = new Coordinate(i, j);
                    newState = table.getState(newCoord);
                    Coin newCoin = new Coin(newState.type);
                    if (newState.type != State.states.EMPTY) {                        
                        pane.add(newCoin.getShape(),newCoord.getX(),newCoord.getY());
                    }
                    coins.add(newCoin.getShape());
                }
            }        
        });                  
    }
    
    /**
     * This method deletes all the game pieces currently on the graphical grid.
     */
    public void delete(){
        for(int i = 0; i < coins.size(); i++){
            pane.getChildren().remove(coins.get(i));
        }
        coins = new ArrayList<>();
    }
                    
}
