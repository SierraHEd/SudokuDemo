package sierra.sudokutest;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class HelloController {
    @FXML
    TextField SignUpUsernameText, SignUpPasswordText, SignUpEmailText, LogInUsernameText, LogInPasswordText;
    Button SignUpButton, LogInButton;

    boolean flag = true;
    boolean flag2 = true;
    boolean flag3 = true;
    private boolean key;
    private ObservableList<Person> listOfUsers = FXCollections.observableArrayList();
    private Person person;

    @FXML
    public void initialize() {

        // Check info is not already in database
        // If not duplicate, add to
        SignUpPasswordText.setStyle("-fx-border-color: black ; -fx-border-width: 1px ;");
        flag = true;
        SignUpPasswordText.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (SignUpPasswordText.getText().matches("[A-Za-z0-9]{2,25}")) {
                flag = false;

                SignUpPasswordText.setStyle("-fx-border-color: black ; -fx-border-width: 1px ;");
            } else {

                SignUpPasswordText.setVisible(true);
                SignUpPasswordText.requestFocus();
                flag = true;
            }
        });

        SignUpUsernameText.setStyle("-fx-border-color: black ; -fx-border-width: 1px ;");
        flag2 = true;
        SignUpUsernameText.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (SignUpUsernameText.getText().matches("[A-Za-z0-9]{2,25}")) {
                flag2 = false;
;
                SignUpPasswordText.setStyle("-fx-border-color: black ; -fx-border-width: 1px ;");
            } else {


                SignUpUsernameText.setVisible(true);
                SignUpUsernameText.requestFocus();
                flag2 = true;
            }
        });

        SignUpEmailText.setStyle("-fx-border-color: black ; -fx-border-width: 1px ;");
        flag3 = true;
        SignUpEmailText.focusedProperty().addListener((observable, oldValue, newValue) -> {

            if (SignUpEmailText.getText().matches("[A-Za-z0-9]{3,15}@[A-Za-z0-9]{3,15}.[A-Za-z0-9]{3,6}")) {
                flag3 = false;

                SignUpPasswordText.setStyle("-fx-border-color: black ; -fx-border-width: 1px ;");
            } else {


                SignUpEmailText.setVisible(true);
                SignUpEmailText.requestFocus();
                flag3 = true;
            }

        });

    }

    @FXML
        public void SignUpEvent(){
        if (!flag && !flag2 && !flag3) {

            UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                    .setEmail(SignUpEmailText.getText())
                    .setEmailVerified(false)
                    .setPassword(SignUpPasswordText.getText())
                    .setDisplayName(SignUpUsernameText.getText())
                    .setDisabled(false);

            // Add User to Database!!!!



            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Congratulations!");
            alert.setHeaderText("You've been signed up!");
            Optional<ButtonType> result = alert.showAndWait();
        }
    }



    @FXML
    public void LogInEvent() throws IOException {

            key = false;

            //asynchronously retrieve all documents
            ApiFuture<QuerySnapshot> future =  App.fstore.collection("Persons").get();
            // future.get() blocks on response
            List<QueryDocumentSnapshot> documents;
            try
            {
                documents = future.get().getDocuments();
                if(documents.size()>0)
                {
                    System.out.println("Outing data from firabase database....");
                    listOfUsers.clear();
                    for (QueryDocumentSnapshot document : documents)
                    {
                        LogInUsernameText.setText(LogInUsernameText.getText()+ document.getData().get("Name")+ " , Age: "+
                                document.getData().get("Age")+ " \n ");
                        System.out.println(document.getId() + " => " + document.getData().get("Name"));
                        person  = new Person(String.valueOf(document.getData().get("Name")));
                        listOfUsers.add(person);
                    }
                }
                else
                {
                    System.out.println("No data");
                }
                key=true;

            }
            catch(InterruptedException | ExecutionException ex)
            {
                ex.printStackTrace();
            }

        App.setRoot("Difficulty");

        //Check that username is in database, if not, user does not exist
        //If yes, check password validity. If valid, log in
        //if not valid, show incorrect password.

    }



}