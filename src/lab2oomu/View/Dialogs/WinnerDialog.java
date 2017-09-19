
package lab2oomu.View.Dialogs;

import javafx.application.Platform;
import javafx.scene.control.Alert;


/**
 * This class represents the dialog that shows which player has won the match.
 * @author Jonathan
 */
public class WinnerDialog {    
       Alert winner = new Alert(Alert.AlertType.INFORMATION);
       
       
    public WinnerDialog(){
        winner.setTitle("WINNER");
    }
    
    public void setContent(String playerName, String color){
        Platform.runLater(() -> {
        winner.setContentText(""+playerName+"("+color+") has won the game!!");
        });
    }
    
    /**
     * displays the dialog.
     */
    public void showDialog() {
        Platform.runLater(() -> {
        winner.showAndWait();
        });
    }
}
