
package lab2oomu.View.Dialogs;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ChoiceDialog;

/**
 * 
 * @author Jonathan
 * This Class represents the dialog which prompts the user for playertype and name.
 */
public class SetUpGameDialog {
    
    TextInputDialog dialog;
    ChoiceDialog choice;
    
    public SetUpGameDialog() {
        dialog = new TextInputDialog("Son of a Noob");
        choice = new ChoiceDialog();
    }
    
    public String setDialog() {

        this.dialog.setTitle("Not Welcome"); //COMEDY!!
        this.dialog.setHeaderText("Do you wanna play a game?");
        this.dialog.setContentText("Please enter your name:");

        Optional<String> result = dialog.showAndWait();

        return result.get();
    }
    
    /**
     * 
     * @return 
     * The user is prompted for a choice between human and computerplayer,
     * if the user chooses human, an additional prompt for name appears.
     */
    public String makeChoice() {
        
        List<String> choices = new ArrayList<>();
        choices.add("Human");
        choices.add("Computer");
        ChoiceDialog<String> choice = new ChoiceDialog<>("Human", choices);
        Optional<String> result = choice.showAndWait();
        return result.get();
    }
}
