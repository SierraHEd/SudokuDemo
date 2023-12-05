package sierra.sudokutest;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class PrimaryController implements Initializable {

    @FXML
    private GridPane sudokuGrid;
    
    private TextField selectedCell;
    private final List<TextField> gridCells = new ArrayList<>();
    private final generateNumbers sudokuGenerator = new generateNumbers(9, 20);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sudokuGenerator.fillValues();
        int[][] generatedSudoku = sudokuGenerator.getGeneratedSudoku();

        // Set padding between grid cells
        sudokuGrid.setHgap(-1); // Horizontal padding
        sudokuGrid.setVgap(-1); // Vertical padding

        // Add the grid cells to the list
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TextField cell = createTextField(generatedSudoku[i][j]);
                gridCells.add(cell);
                sudokuGrid.add(cell, j, i);
            }
        }
    }

    private TextField createTextField(int value) {
        TextField cell = new TextField();
        cell.setPrefWidth(50); // Adjust cell width
        cell.setPrefHeight(50); // Adjust cell height
        cell.setPadding(new Insets(5)); // Adjust padding

        // Set background color, font size, and adjust borders
        cell.setStyle(
                "-fx-font-size: 20; -fx-background-color: white; -fx-border-width: 1; -fx-border-color: lightgray;");

        if (value == 0) {
            cell.clear(); // Set empty text for zeros
            cell.setAlignment(Pos.CENTER); // Center the input text
            cell.setOnKeyTyped(this::handleKeyPress); // Add event listener for key press
            cell.setStyle(cell.getStyle() + "; -fx-cursor: none;-fx-text-inner-color: transparent;");
            cell.setOnMousePressed(this::handleMousePressed);
        } else {
            cell.setText(String.valueOf(value));
            cell.setEditable(false); // Make non-editable for filled numbers
            cell.setAlignment(Pos.CENTER); // Center the text
            cell.setStyle(cell.getStyle() + "; -fx-background-color: #f3f3f3;");
        }
        
        // Add mouse event handlers
        cell.setOnMouseEntered(this::handleMouseEntered);
        cell.setOnMouseExited(this::handleMouseExited);

        return cell;
    }

    private void handleKeyPress(KeyEvent event) {
        TextField textField = (TextField) event.getSource();
        String input = event.getCharacter();

        if (isNumeric(input) && input.length() == 1) {
            // Only allow input of a single digit between 1 and 9
            textField.setText(input);

            // Check the user's input against the correct solution
            int row = GridPane.getRowIndex(textField);
            int col = GridPane.getColumnIndex(textField);
            int userInput = Integer.parseInt(input);
            int[][] currentBoard = sudokuGenerator.getGeneratedSudoku();

            if (sudokuGenerator.isSafe(row, col, userInput, currentBoard)) {
                System.out.println("Correct!");
                textField.setStyle(textField.getStyle() + "; -fx-text-fill: black;"); // Set text color to black for correct answers
            } else {
                System.out.println("Incorrect!");
                textField.setStyle(textField.getStyle() + "; -fx-text-fill: red;"); // Set text color to red for incorrect answers
            }

            // Check if the game is won
            if (isGameWon()) {
                showWinDialog();
            }
        } else {
            event.consume(); // Consume the event to prevent non-numeric characters
        }
    }

    private boolean isGameWon() {
        for (TextField cell : gridCells) {
            if (cell.getText().equals("") || !cell.getStyle().contains("-fx-text-fill: black;")) {
                return false;
            }
        }
        return true;
    }

    private void showWinDialog() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Congratulations!");
        alert.setHeaderText("You've won the game!");
        alert.setContentText("Do you want to start a new game?");
        
        ButtonType newGameButton = new ButtonType("New Game");
        ButtonType exitButton = new ButtonType("Exit");
        alert.getButtonTypes().setAll(newGameButton, exitButton);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == newGameButton) {
            // Start a new game
            initialize(null, null);
        } else {
            // Exit the application
            Platform.exit();
        }
    }

    private boolean isNumeric(String str) {
        return str.matches("[1-9]");
    }



    // Mouse enter event handler
    private void handleMouseEntered(MouseEvent event) {
        TextField cell = (TextField) event.getSource();

        // Check if the cell is empty (value 0) before changing the color
        if (cell.getText().equals("")) {
            cell.setStyle(cell.getStyle() + "; -fx-background-color: lightgray;"); // Highlight the cell in blue
        }
    }

    // Mouse exit event handler
    private void handleMouseExited(MouseEvent event) {
        TextField cell = (TextField) event.getSource();

        // Check if the cell is empty (value 0) before changing the color
        if (cell.getText().equals("")) {
            cell.setStyle(cell.getStyle() + "; -fx-background-color: white;"); // Restore light gray border
        }
    }
    private void handleMousePressed(MouseEvent event) {
        TextField cell = (TextField) event.getSource();

        // Clear border from the previously selected cell
        if (selectedCell != null) {
            selectedCell.setStyle(selectedCell.getStyle().replace("-fx-border-color: black;", ""));
        }

        // Set border color for the selected cell
        cell.setStyle(cell.getStyle() + "; -fx-border-color: black;");

        // Update the selected cell
        selectedCell = cell;
    }
}