package sierra.sudokutest;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class DifficultyController {

    static String difficulty= "NA";
    static int blanks = 0;
    @FXML
    Button EasyButton;

    @FXML
    Button HardButton;

    @FXML
    Button MediumButton;

    @FXML
    void EasyButtonSelected(ActionEvent event) throws IOException {
        difficulty="Easy";
        blanks = 1;

        App.setRoot("primary");

    }

    @FXML
    void HardButtonSelected(ActionEvent event) throws IOException {
        difficulty="Hard";
        blanks = 40;
        App.setRoot("primary");

    }

    @FXML
    void MediumButtonSelected(ActionEvent event) throws IOException {
       difficulty="Medium";
       blanks = 30;


        App.setRoot("primary");

    }

    public String getDifficulty() {
        return difficulty;
    }
}