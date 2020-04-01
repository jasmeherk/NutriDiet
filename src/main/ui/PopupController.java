package ui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PopupController {
    static void popupMessage(String error, String warning) {
        Stage newWindow = new Stage();
        newWindow.initModality(Modality.APPLICATION_MODAL);
        newWindow.setMinWidth(250);
        Label inputErrorLabel = new Label();
        inputErrorLabel.setText(error);
        Label warningLabel = new Label();
        warningLabel.setText(warning);
        VBox layout = new VBox(10);
        layout.getChildren().addAll(inputErrorLabel,warningLabel);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        newWindow.setScene(scene);
        newWindow.showAndWait();
    }
}
