module com.example.sudokulogin {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.sudokulogin to javafx.fxml;
    exports com.example.sudokulogin;
}