module sierra.sudokutest {
    requires javafx.controls;
    requires javafx.fxml;

    opens sierra.sudokutest to javafx.fxml;
    exports sierra.sudokutest;
}
