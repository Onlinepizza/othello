/**
 * This is the GameManager
 */
package lab2oomu.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.stage.Stage;
import lab2oomu.View.Dialogs.DrawnDialog;
import lab2oomu.View.GameFrame;
import lab2oomu.View.Dialogs.SetUpGameDialog;
import lab2oomu.View.Dialogs.WinnerDialog;
import lab2oomu.Model.Coordinate;
import lab2oomu.Model.GameGrid;
import lab2oomu.controller.Player.GraphicObserver;
import lab2oomu.Model.State;
import lab2oomu.controller.Player.ComputerPlayer;
import lab2oomu.controller.Player.HumanPlayer;
import lab2oomu.Model.State.states;
import lab2oomu.controller.Player.LogicObserver;

import lab2oomu.controller.Player.Player;

/**
 * This Class handles communication between model and view.
 * It determines which player is next up, and declares a winner.
 * @author S143975
 */
public class GameManager implements Runnable, LogicObserver{

    private GameFrame gFrame;
    private WinnerDialog win;
    private DrawnDialog draw;
    private GameGrid gameTable;
    private Thread managerThread;
    private GraphicObserver observ;
    private Player pOne;
    private Player pTwo;
    private Player activePlayer;
    private Coordinate move;   
    private Stage primaryStage;
    private boolean newGame;

    int x = 0;
    
    /**
     * This constuctor initiates the graphics,
     * assigns observers and prompts the user for player type and name.
     * @param primaryStage 
     */
    public GameManager(Stage primaryStage){

        this.gameTable = new GameGrid();
        this.observ = new GraphicObserver();
        this.primaryStage = primaryStage;
        this.win = new WinnerDialog();
        this.draw = new DrawnDialog();
        this.move = null;
        this.newGame = false;
        
        SetUpGameDialog getNameDialog = new SetUpGameDialog();
        
        String playerType, playerType2;

        playerType = getNameDialog.makeChoice();
        if (playerType.equals("Human")) {
            this.pOne = new HumanPlayer(new State(states.WHITE), getNameDialog.setDialog(), observ);
        } else {
            this.pOne = new ComputerPlayer(new State(states.WHITE), "HAL 9000");
        }

        playerType2 = getNameDialog.makeChoice();
        if (playerType2.equals("Human")) {
            this.pTwo = new HumanPlayer(new State(states.BLACK), getNameDialog.setDialog(), observ);
        } else {
            this.pTwo = new ComputerPlayer(new State(states.BLACK), "Skynet");
        }
        
        this.gFrame = new GameFrame(primaryStage, pOne.getName(), pTwo.getName(), gameTable);
        gFrame.addObserver(observ);
        gFrame.addNewGameObserver(this);
        gameTable.Register(gFrame.getGridObserver());
        gameTable.Register(gFrame.getBoardObserver());
        gameTable.notifyObservers();
        activePlayer = null;
        activePlayer = pOne;
    }
    
    public void addThread(Thread thread){
        this.managerThread = thread;
    }
    
    /**
     * This is the main part of the application,
     * the active player is established, and subsequently promped for
     * moves.
     * If the game is completed, the winnername is displayed.
     * If the game is a draw this is also displayed.
     */
    @Override
    public void run() {
        Boolean running = true;
        Boolean showScores = true;
        int curMoves;
        
       while (running){
           
            try { 
                managerThread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(GameManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            //RUN GAME
            while (gameTable.getGameState() == GameGrid.gameState.RUNNING) {
                
                showScores = true;
                if(newGame){                
                    reset();
                }

                if (!gameTable.existsValidMove(activePlayer.getColor())) {
                    gameTable.setGameStateDone();
                    break;
                }

                curMoves = gameTable.getNCoins();
                
                
                while (curMoves == gameTable.getNCoins()) {
                    tryMove(activePlayer, activePlayer.getColor());
                    if(newGame) break;
                }

                if(!newGame)changeActivePlayer();   
            }
            
            //END OF GAME
            if (showScores){
            if (gameTable.getNBCoins() == gameTable.getNWCoins()) {
                draw.showDialog();
            }
            else {
                if (gameTable.getNWCoins() > gameTable.getNBCoins()) {
                    win.setContent(pOne.getName(), "White");
                } 
                else {
                    win.setContent(pTwo.getName(), "Black");
                }
                win.showDialog();
            }            
            showScores = false;
            }
            
            //RESET?
            if(newGame){
                reset();
                gameTable.setGameStateRunning();                 
                }
       }
    }
    
    /**
     * This method prompts the active player for a move until they make a valid
     * one.
     * @param play
     * @param tile 
     */
    public void tryMove(Player play, State tile) {
        this.move = null;
        while (true) {
            try {
                while (move == null) {
                    if(newGame) return;
                    this.move = play.getMove();
                    managerThread.sleep(10);    
                }                
                play.makeMove(gameTable, move, tile);                
                return;
            } catch (IllegalArgumentException ex) {
                // System.out.println(ex.getMessage());
            } catch (InterruptedException ex) {
                Logger.getLogger(GameManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            return;
        }
    }
    
    /**
     * This method is called when the "new game" button is pressed.
     * @param coord 
     * @param resetBool 
     */
    @Override
    public void update(Coordinate coord) {        
        newGame = true;        
    }
    
    /**
     * This method restarts the game.
     */
    public void reset(){        
        gameTable.setGameStateRunning();
        gameTable.resetTable();
        activePlayer = pOne;
        newGame = false;
    }
    
    /**
     * this method changes the active player.
     */
    private void changeActivePlayer(){       
        if(activePlayer == pOne){
           activePlayer = pTwo;
        }else if(activePlayer == pTwo){
           activePlayer = pOne;
        }
    }    
}
