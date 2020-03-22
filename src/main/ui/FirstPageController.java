package ui;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class FirstPageController {
    @FXML
    Button newFileButton = new Button();
    @FXML
    Button loadFileButton = new Button();

    @FXML void newFileFunction() throws IOException {
        newFileButton.setOnAction(event -> {
            try {
                GoalsController.addGoals();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
