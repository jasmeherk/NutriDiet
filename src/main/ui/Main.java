package ui;
// Main class which runs everything


import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.*;

import javafx.scene.control.*;
import model.Food;


import java.io.IOException;

public class Main extends Application {


    public static void main(String[] args) throws IOException {
        launch(args);
     //   new UserInterface();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("firstpage.fxml"));
        primaryStage.setTitle("Welcome to NutriDiet");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

}
