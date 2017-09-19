
package lab2oomu.View;

import javafx.application.Platform;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lab2oomu.Model.Coordinate;
import lab2oomu.Model.GameGrid;
import lab2oomu.controller.Player.LogicObserver;

/**
 * This Class represents the grapical part of the application.
 * It displays the name of the players, the buttons "new game" and "Exit".
 * It also displays the current stats of the game and it has and instance of the
 * class GraphicGrid.
 * @author Jonathan
 */
public class GameBoard implements Observer, Subject{

    private BorderPane pane;
    private GraphicGrid theGrid;
    private Coordinate currentMove;
    private TextField textField1;
    private TextField textField2;
    private GraphicSubject subject;
    private LogicObserver newGameOb;
    
    /**
     * Creates a new instance of a GraphicGrid object and creates a borderpane
     * and the top border is set to display player names.
     * @param playerName
     * @param playerName2
     * @param gameTable 
     */
    public GameBoard(String playerName, String playerName2, GameGrid gameTable) {
        this.theGrid = new GraphicGrid();
        pane = new BorderPane();
        pane.setTop(new CustomPane("Top", playerName, playerName2, gameTable));
        pane.setRight(new CustomPane("Right", null, null, gameTable));
        pane.setBottom(new CustomPane("Bottom", null, null, gameTable));
        pane.setCenter(new CustomPane("Center", null, null, gameTable));
    }

    public BorderPane getPane() {
        return this.pane;
    }
    
    /**
     * This method returns an Object of type Observer which updates the graphical
     * representaion of the grid which contains the gamepieces
     * @return 
     */
    public Observer getGridObserver() {
        return theGrid;
    }
    
    /**
     * This method accepts an object of type GraphicObserver which
     * stores moves thrown by the events on the graphical gameboard.
     * @param o 
     */
    public void assignGraphicSubject(LogicObserver o) {
        theGrid.getSubject().Register(o);
        this.subject = theGrid.getSubject();
    }
    
    /**
     * This method allows the user to enter a move from the keyboard.
     */
    public void getMoveFromKeyboard() {

        int x = Integer.parseInt(textField1.getText());
        x--;
        int y = Integer.parseInt(textField2.getText());
        y--;
        if (x >= 0 && x < 8 && y >= 0 && y < 8) {
            Coordinate coord = new Coordinate(x, y);
            subject.SaveMove(coord);
            subject.notifyObservers();
        }
    }
    
    /**
     * This methiod resets the graphical representation of the game and
     * sends a special coordinate which will signal the logical part
     * to reset itself.
     */
    public void newGame(){
        this.theGrid.delete();
        notifyObservers();
    }
    
    /**
     * registers a new observer which listens for changes made by the
     * "new game" button
     * @param o 
     */
    @Override
    public void Register(LogicObserver o) {
        this.newGameOb = o;
    }
    
    /**
     * Signals the observer to reset the logcal part of the game.
     */
    @Override
    public void notifyObservers() {
        newGameOb.update(currentMove);
    }
    
    /**
     * This inner class represents the pan on which the player names, game stats,  
     * the buttons "new game" and "exit" will actually be placed. It also contains
     * the GraphicGrid.
     */
    class CustomPane extends StackPane {

        public CustomPane(String title, String playerName, String playerName2, GameGrid gameTable) {

            if (title.equals("Top")) {
                HBox paneForButtons = new HBox(5);
                paneForButtons.setAlignment(Pos.CENTER);

                Text text1 = new Text(0, 0, "" + playerName + " (WHITE) VS " + playerName2 + " (BLACK)");
                paneForButtons.getChildren().add(text1);
                getChildren().add(paneForButtons);
            }
            if (title.equals("Left")) {
                VBox paneForButtons = new VBox(5);
                paneForButtons.setAlignment(Pos.TOP_LEFT);
                getChildren().add(paneForButtons);
            }

            if (title.equals("Bottom")) {
                HBox paneForButtons = new HBox(5);
                paneForButtons.setAlignment(Pos.CENTER);
                Text text1 = new Text(0, 0,
                        "White: " + gameTable.getNWCoins() + " || BLACK: " + gameTable.getNBCoins()
                        + " Total coins on the table " + gameTable.getNCoins());

                textField1 = new TextField();
                textField2 = new TextField();

                Button makeMove = new Button("Make Move");

                makeMove.setOnAction(e -> getMoveFromKeyboard());

                paneForButtons.getChildren().add(new Label("X"));
                paneForButtons.getChildren().add(textField1);
                paneForButtons.getChildren().add(new Label("Y"));
                paneForButtons.getChildren().add(textField2);
                paneForButtons.getChildren().add(makeMove);
                paneForButtons.getChildren().add(text1);
                getChildren().add(paneForButtons);
            }

            if (title.equals("Center")) {
                int i;
                HBox upperCords = new HBox(8);
                upperCords.setSpacing(45);
                upperCords.setAlignment(Pos.TOP_CENTER);

                VBox leftCords = new VBox(8);
                leftCords.setSpacing(35);
                leftCords.setAlignment(Pos.CENTER_LEFT);
                
                for (i = 1; i < 9; i++) {
                    Text x = new Text(Integer.toString(i));
                    upperCords.getChildren().add(x);

                }
              for (i = 1; i < 9; i++) {
                    Text x = new Text(Integer.toString(i));
                    leftCords.getChildren().add(x);
                }

                getChildren().add(upperCords);
                getChildren().add(leftCords);
                

                Image image = new Image("image.jpg");
                ImageView view = new ImageView(image);
                view.setFitHeight(408);
                view.setFitWidth(408);
                getChildren().add(view);
                getChildren().add(theGrid.getPane());

  /*              */

            }

            if (title.equals("Right")) {
                VBox pane = new VBox(2);
                pane.setAlignment(Pos.TOP_LEFT);
                Button nGame = new Button("New Game");
                nGame.setOnAction(e -> newGame());
                Button exit = new Button("Exit");
                exit.setOnAction(e -> System.exit(1));
                pane.getChildren().add(nGame);
                pane.getChildren().add(exit);
                getChildren().add(pane);
            }

            setStyle("-fx-border-color: black");
            setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        }

    }
    
    /**
     * This Method overrides the update method in Observer and 
     * uses the parameter gamegrid to update the stats of the game.
     * @param table 
     */
    @Override
    public void update(GameGrid table) {

        Platform.runLater(() -> {
            pane.setBottom(new CustomPane("Bottom", null, null, table));
        });
    }
}