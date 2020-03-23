package ui;


import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.StoredData;
import org.omg.PortableInterceptor.INACTIVE;
import persistence.Reader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FirstPageController {


    //Buttons
    @FXML Button newFileButton;
    @FXML Button loadFileButton;

    @FXML void newFileFunction() throws IOException {
        newFileButton.setOnAction(event -> {
            try {
                GoalsController.addGoals();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML void loadFileFunction() {
        loadFileButton.setOnAction(event -> {
            StoredData models = null;
            try {
                models = Reader.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
            FXMLLoader loader = new FXMLLoader(GoalsController.class.getResource("newload.fxml"));
            Parent root = null;
            try {
                root = (Parent) loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            MainController mainController = loader.getController();
            mainController.setGymText(Integer.toString(models.getGoalData().getDesiredGymRigour()));
            mainController.setWeightText(Double.toString(models.getGoalData().getDesiredWeight()));
            mainController.setSleepText(Integer.toString(models.getGoalData().getDesiredSleep()));
            mainController.setInitialWeightText(Double.toString(models.getAttrData().getWeight()));
            mainController.setInitialHeightText(Double.toString(models.getAttrData().getWeight()));
            mainController.calorieCounter.setActivities(models.getCalData().getActivities());
            mainController.calorieCounter.setFoods(models.getCalData().getFoods());
            mainController.calorieCounter.setFluids(models.getCalData().getFluids());
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });
    }
}
