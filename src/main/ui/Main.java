package ui;
// Main class which runs everything


import com.sun.javafx.fxml.builder.JavaFXImageBuilder;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.*;
import javafx.scene.control.*;
import javafx.application.Application;


import model.Food;


import java.io.IOException;

public class Main extends Application {


    public static void main(String[] args) throws IOException {
        launch(args);
        //new UserInterface();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("firstpage.fxml"));
        primaryStage.setTitle("Welcome to NutriDiet");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

}
