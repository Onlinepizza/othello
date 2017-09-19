/**
 * Othello game by Oscar Persson (S131469) and Jonathan Knutsson (S143975)
 */
package lab2oomu;

import javafx.application.Application;
import javafx.stage.Stage;
import lab2oomu.controller.GameManager;

/**
 * This class launches the game.
 * @author S143975
 */
public class Othello extends Application {

    @Override
    public void start(Stage primaryStage) {
        GameManager manager = new GameManager(primaryStage);
        Thread managerThread = new Thread(manager);
        manager.addThread(managerThread);
        managerThread.start();
    }
}
