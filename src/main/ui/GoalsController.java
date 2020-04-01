package ui;

import exceptions.InvalidInputException;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class GoalsController {


    public static void addGoals() throws IOException {
        Stage window = new Stage();
        Label labelGymRigour = new Label();
        TextField gymRigourEntry = new TextField();
        Label labelWeight = new Label();
        TextField desiredWeightEntry = new TextField();
        Label labelSleep = new Label();
        TextField sleepEntry = new TextField();
        Label initialWeight = new Label();
        TextField initialWeightEntry = new TextField();
        Label height = new Label();
        TextField heightEntry = new TextField();
        Button doneButton = new Button("Done");
        initializeLabelAndStageSettings(window,labelGymRigour,labelWeight,labelSleep,initialWeight,height);
        doneButton.setOnAction(event -> {
            goalButtonClicked(gymRigourEntry.getText(), desiredWeightEntry.getText(),
                    sleepEntry.getText(),initialWeightEntry.getText(),heightEntry.getText());
            window.close();
        });
        initializeLayout(gymRigourEntry,desiredWeightEntry,sleepEntry,initialWeightEntry,heightEntry,labelGymRigour,
                labelWeight,labelSleep,initialWeight,height,window,doneButton);
    }

    static void goalButtonClicked(String gymRigour, String desiredWeight, String sleep, String weight, String height) {
        FXMLLoader loader = new FXMLLoader(GoalsController.class.getResource("newload.fxml"));
        Parent root = null;
        try {
            root = (Parent) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            MainController mainController = loader.getController();
            mainController.setGymText(gymRigour);
            mainController.setWeightText(desiredWeight);
            mainController.setSleepText(sleep);
            mainController.setInitialWeightText(weight);
            mainController.setInitialHeightText(height);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (InvalidInputException exception) {
            PopupController.popupMessage("Please enter appropriate values for the relevant fields",
                    "All values entered should be a type of number,gym rigour must be between 0-10,"
                            + "height and weight must be greater than 0");
        }
    }

    static void initializeLabelAndStageSettings(Stage window, Label gymRigour, Label weight,
                                                Label desiredSleep, Label initialWeight, Label height) {
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Desired Goals");
        window.setMinWidth(350);
        gymRigour.setText("Desired Gym Rigour (0-10)");
        weight.setText("Desired Weight");
        desiredSleep.setText("Desired Sleep");
        initialWeight.setText("Current Weight");
        height.setText("Height");
    }

    static void initializeLayout(TextField gymRigourEntry, TextField desiredWeightEntry, TextField sleepEntry,
                                 TextField initialWeightEntry, TextField heightEntry, Label labelGymRigour,
                                 Label labelWeight, Label labelSleep, Label initialWeight, Label height,
                                 Stage window, Button doneButton) {
        VBox layout = new VBox(10);
        layout.getChildren().addAll(labelGymRigour,gymRigourEntry,labelWeight,
                desiredWeightEntry,labelSleep,sleepEntry,initialWeight,initialWeightEntry,
                height,heightEntry,doneButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
