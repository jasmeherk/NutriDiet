package ui;

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

import java.io.IOException;

public class GoalsController {


    public static void addGoals() throws IOException {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Desired Goals");
        window.setMinWidth(350);
        Label labelGymRigour = new Label();
        labelGymRigour.setText("Desired Gym Rigour");
        TextField gymRigourEntry = new TextField();
        Label labelWeight = new Label();
        labelWeight.setText("Desired Weight");
        TextField weightEntry = new TextField();
        Label labelSleep = new Label();
        labelSleep.setText("Desired Sleep");
        TextField sleepEntry = new TextField();
        Label initialWeight = new Label();
        initialWeight.setText("Current Weight");
        TextField initialWeightEntry = new TextField();
        Label height = new Label();
        height.setText("Height");
        TextField heightEntry = new TextField();
        Button doneButton = new Button("Done");
        doneButton.setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader(GoalsController.class.getResource("newload.fxml"));
            Parent root = null;
            try {
                root = (Parent) loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            MainController mainController = loader.getController();
            mainController.setGymText(gymRigourEntry.getText());
            mainController.setWeightText(weightEntry.getText());
            mainController.setSleepText(sleepEntry.getText());
            mainController.setInitialWeightText(initialWeightEntry.getText());
            mainController.setInitialHeightText(heightEntry.getText());
            window.close();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });
        VBox layout = new VBox(10);
        layout.getChildren().addAll(labelGymRigour,gymRigourEntry,labelWeight,
                weightEntry,labelSleep,sleepEntry,initialWeight,initialWeightEntry, height,heightEntry,doneButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
