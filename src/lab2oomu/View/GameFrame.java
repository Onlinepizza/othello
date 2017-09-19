
package lab2oomu.View;


import javafx.scene.Scene;

import javafx.stage.Stage;
import lab2oomu.Model.Coordinate;
import lab2oomu.Model.GameGrid;
import lab2oomu.controller.Player.LogicObserver;

/**
 * This class initiates the graphical representation of the game and 
 * acts as an interface for the thro which the controller can assign and 
 * get observers and subjects.
 * 
 * @author Jonathan
 */
public class GameFrame{
    GameBoard board;   

    public GameFrame(Stage primaryStage, String playerName, String playerName2, GameGrid gameTable) {
        this.board = new GameBoard(playerName, playerName2, gameTable);
        Scene scene = new Scene(board.getPane(), 720, 600);

        primaryStage.setTitle("Othello 4 alle");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    /**
     * This method returns an Object of type Observer which updates the graphical
     * representaion of the grid which contains the gamepieces
     * @return 
     */
    public Observer getGridObserver(){
        return board.getGridObserver();
    }
    
    /**
     * This method returns an Object of type Observer which updates the current
     * stats of the game.
     * @return 
     */
    public Observer getBoardObserver(){
        return board;
    }
    
    /**
     * This method accepts an object of type LogicObserver which
     * stores moves thrown by the events on the graphical gameboard.
     * @param o 
     */
    public void addObserver(LogicObserver o){
        board.assignGraphicSubject(o);
    }
    
    /**
     * this method accepts an object of type Logicobserver which
     * listens for events thrown by the button "new game"
     * @param o 
     */
    public void addNewGameObserver(LogicObserver o){
        board.Register(o);
    }
}
