package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Activities;
import model.CalorieCounter;
import model.Fluids;
import model.Food;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    CalorieCounter calorieCounter;

    //Labels
    public Label initialWeight;

    void setInitialWeightText(String text) {
        initialWeight.setText(text);
    }

    public Label initialHeight;

    void setInitialHeightText(String text) {
        initialHeight.setText(text);
    }

    public Label goalGym;

    void setGymText(String text) {
        goalGym.setText(text);
    }

    public Label goalWeight;

    void setWeightText(String text) {
        goalWeight.setText(text);
    }

    public Label goalSleep;

    void setSleepText(String text) {
        goalSleep.setText(text);
    }


    //Text Areas
    @FXML public TextArea foodList;
    @FXML public TextArea activityList;
    @FXML public TextArea fluidList;


    //Buttons
    @FXML Button addFoodButton = new Button();
    @FXML Button addFluidButton = new Button();
    @FXML Button addActivityButton = new Button();
    @FXML Button saveAndQuitButton = new Button();
    @FXML public Button reccomendButton = new Button();
    @FXML public Button viewFoodButton = new Button();
    @FXML public Button viewFluidButton = new Button();
    @FXML public Button viewActivityButton = new Button();
    @FXML public Button bmiButton = new Button();


    @FXML void displayFoods() {
        ArrayList<Food> foods = calorieCounter.getFoods();
        String text = "";
        for (Food f: foods) {
            text = text + "Name : " + f.getFoodName() + " Calories: " + f.getCalories() + "\n";
        }
        foodList.setText(text);
    }

    @FXML void displayFluids() {
        ArrayList<Fluids> fluids = calorieCounter.getFluids();
        String text = "";
        for (Fluids f: fluids) {
            text = text + "Name : " + f.getFluidName() + " Quantity(ml): " + f.getQuantityinML() + "\n";
        }
        fluidList.setText(text);
    }

    @FXML void displayActivity() {
        ArrayList<Activities> activities = calorieCounter.getActivities();
        String text = "";
        for (Activities a: activities) {
            text = text + "Gym Rigour: " + a.getGymmingRigour()
                    + " Sleep: " + a.getSleep() + " Walking Distance(km): " + a.getWalk() + "\n";
        }
        activityList.setText(text);
    }



    @FXML void foodButton() {
        addFoodButton.setOnAction(event -> {
            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setMinWidth(350);
            Label foodNameLabel = new Label();
            foodNameLabel.setText("Food Name");
            TextField foodName = new TextField();
            Label foodCalLabel = new Label();
            foodCalLabel.setText("Calories");
            TextField foodCal = new TextField();
            Button doneButton = new Button("Add");
            doneButton.setOnAction(e -> {
                Food food = new Food(foodName.getText(), Integer.parseInt(foodCal.getText()));
                calorieCounter.addFood(food);
                window.close();
            });
            VBox layout = new VBox(10);
            layout.getChildren().addAll(foodNameLabel,foodName,foodCalLabel,foodCal,doneButton);
            layout.setAlignment(Pos.CENTER);
            Scene scene = new Scene(layout);
            window.setScene(scene);
            window.showAndWait();
        });
    }

    @FXML void fluidButton() {
        addFluidButton.setOnAction(event -> {
            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setMinWidth(350);
            Label fluidNameLabel = new Label();
            fluidNameLabel.setText("Fluid Name");
            TextField fluidName = new TextField();
            Label fluidQuantityLabel = new Label();
            fluidQuantityLabel.setText("Quantity (ml)");
            TextField fluidQuantity = new TextField();
            Button doneButton = new Button("Add");
            doneButton.setOnAction(e -> {
                Fluids fluid = new Fluids(fluidName.getText(), Integer.parseInt(fluidQuantity.getText()));
                calorieCounter.addFluid(fluid);
                window.close();
            });
            VBox layout = new VBox(10);
            layout.getChildren().addAll(fluidNameLabel,fluidName,fluidQuantityLabel,fluidQuantity,doneButton);
            layout.setAlignment(Pos.CENTER);
            Scene scene = new Scene(layout);
            window.setScene(scene);
            window.showAndWait();
        });
    }

    @FXML void activityButton() {
        addFoodButton.setOnAction(event -> {
            addActivity();
        });
    }

    void addActivity() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(350);
        Label gymLabel = new Label();
        gymLabel.setText("Gymming Rigour (1-10)");
        TextField gymVal = new TextField();
        Label walkLabel = new Label();
        walkLabel.setText("Walking (km)");
        TextField walkVal = new TextField();
        Label sleepLabel = new Label();
        sleepLabel.setText("Sleep (hours)");
        TextField sleepVal = new TextField();
        Button doneButton = new Button("Add");
        doneButton.setOnAction(e -> {
            addingActivity(walkVal.getText(),sleepVal.getText(),gymVal.getText());
            window.close();
        });
        VBox layout = new VBox(10);
        layout.getChildren().addAll(gymLabel,gymVal,walkLabel,walkVal,sleepLabel,sleepVal,doneButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

    }

    void addingActivity(String walkVal, String sleepVal, String gymVal) {
        Activities a = new Activities(Double.parseDouble(walkVal),
                Integer.parseInt(sleepVal),
                Integer.parseInt(gymVal));
        calorieCounter.addActivity(a);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        calorieCounter = new CalorieCounter();
    }
}

