package sierra.sudokutest;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class HelloController {
    @FXML
    TextField SignUpUsernameText, SignUpPasswordText, SignUpEmailText, LogInUsernameText, LogInPasswordText;
    Button SignUpButton, LogInButton;

    @FXML
    public void SignUpEvent(){

    

    }
    @FXML
    public void LogInEvent() throws IOException {
        App.setRoot("Difficulty");

        //Check that username is in database, if not, user does not exist
        //If yes, check password validity. If valid, log in
        //if not valid, show incorrect password.

    }



}