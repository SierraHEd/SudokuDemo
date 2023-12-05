package com.example.sudokulogin;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    TextField SignUpUsernameText, SignUpPasswordText, SignUpEmailText, LogInUsernameText, LogInPasswordText;
    Button SignUpButton, LogInButton;

    @FXML
    public void SignUpEvent(){

        // Check info is not already in database
        // If not duplicate, add to database

    }
    @FXML
    public void LogInEvent(){

        //Check that username is in database, if not, user does not exist
        //If yes, check password validity. If valid, log in
        //if not valid, show incorrect password.

    }



}