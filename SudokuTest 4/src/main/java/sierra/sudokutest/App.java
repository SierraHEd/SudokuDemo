package sierra.sudokutest;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    
    public static Firestore fstore;
    public static FirebaseAuth fauth;
    private final FirestoreContext contxtFirebase = new FirestoreContext();


    @Override
    public void start(Stage stage) throws IOException {
         fstore = contxtFirebase.firebase();
        fauth = FirebaseAuth.getInstance();
        scene = new Scene(loadFXML("SudokuLogInFXML"), 1440, 780);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }



    public static void main(String[] args) {
//        int N = 9, K = 20;
//        generateNumbers sudoku = new generateNumbers(N, K);
//        sudoku.fillValues();
//        sudoku.printSudoku();
        launch();
    }

}