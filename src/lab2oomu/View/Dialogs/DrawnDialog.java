
package lab2oomu.View.Dialogs;

import javafx.application.Platform;
import javafx.scene.control.Alert;

/**
 * 
 * @author Jonathan
 * This Class represents a dialog which appears if the game is a draw.
 */
public class DrawnDialog {

    Alert draw = new Alert(Alert.AlertType.INFORMATION);

    public DrawnDialog(){
        draw.setTitle("TIE");  
        draw.setContentText("No one ever wins");
    }
    
    /**
     * Displays the dialog.
     */
    public void showDialog() {
        Platform.runLater(() -> {
         draw.showAndWait();
        });
    }
    
    
    
    
}
