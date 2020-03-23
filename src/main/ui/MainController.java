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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.*;
import persistence.Reader;
import persistence.Writer;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController  {
    Stage window = new Stage();
    CalorieCounter calorieCounter;
    Recommender recommend;
    Goals goals;
    Attributes attr;
    Image imgFood;
    ImageView foodImage;
    Image imgWater;
    ImageView waterImage;
    Image imgSleep;
    ImageView sleepImage;

    public MainController() {
        calorieCounter = new CalorieCounter();
        recommend = new Recommender();
        goals = new Goals(0.0,0,0);
        attr = new Attributes(0.0,0.0);
        imgFood = new Image("/ui/food.jpg");
        foodImage = new ImageView(imgFood);
        foodImage.setFitHeight(75);
        foodImage.setFitWidth(75);
        imgWater = new Image("/ui/water_droplets_ripple_hd_picture_3.jpg");
        waterImage = new ImageView(imgWater);
        waterImage.setFitHeight(75);
        waterImage.setFitWidth(75);
        imgSleep = new Image("/ui/sleep.jpeg");
        sleepImage = new ImageView(imgSleep);
        sleepImage.setFitHeight(75);
        sleepImage.setFitWidth(75);
    }

    //Labels
    public Label initialWeight;

    void setInitialWeightText(String text) {
        initialWeight.setText(text);
        attr.setWeight(Double.parseDouble(text));
    }

    public Label initialHeight;

    void setInitialHeightText(String text) {
        initialHeight.setText(text);
        attr.setHeight(Double.parseDouble(text));
    }

    public Label goalGym;

    void setGymText(String text) {
        goalGym.setText(text);
        goals.setDesiredGymRigour(Integer.parseInt(text));
    }

    public Label goalWeight;

    void setWeightText(String text) {
        goalWeight.setText(text);
        goals.setDesiredWeight(Double.parseDouble(text));
    }

    public Label goalSleep;

    void setSleepText(String text) {
        goalSleep.setText(text);
        goals.setDesiredSleep(Integer.parseInt(text));
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

    @FXML void displayBMI() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(350);
        Label bmiLabel = new Label();
        bmiLabel.setText("Your BMI is : ");
        Label bmiVal = new Label();
        double bmi = (double) Integer.parseInt(initialWeight.getText())
                / (Double.parseDouble(initialHeight.getText()) * Double.parseDouble(initialHeight.getText()));
        bmiVal.setText(Double.toString(bmi));
        VBox layout = new VBox(10);
        layout.getChildren().addAll(bmiLabel,bmiVal);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

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
            layout.getChildren().addAll(foodNameLabel,foodName,foodCalLabel,foodCal,foodImage,doneButton);
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
            layout.getChildren().addAll(fluidNameLabel,fluidName,fluidQuantityLabel,fluidQuantity,waterImage,doneButton);
            layout.setAlignment(Pos.CENTER);
            Scene scene = new Scene(layout);
            window.setScene(scene);
            window.showAndWait();
        });
    }

    @FXML void activityButton() {
        addActivityButton.setOnAction(event -> {
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
                Activities a = new Activities(Double.parseDouble(walkVal.getText()),
                        Integer.parseInt(sleepVal.getText()),
                        Integer.parseInt(gymVal.getText()));
                calorieCounter.addActivity(a);
                window.close();
            });
            VBox layout = new VBox(10);
            layout.getChildren().addAll(gymLabel,gymVal,walkLabel,walkVal,sleepLabel,sleepVal,sleepImage,doneButton);
            layout.setAlignment(Pos.CENTER);
            Scene scene = new Scene(layout);
            window.setScene(scene);
            window.showAndWait();
        });
    }

    @FXML
    void setReccomendButton() {
        reccomendButton.setOnAction(event -> {
            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setMinWidth(350);
            String text = "";
            TextArea msg = new TextArea();
            double hydValue = recommend.hydrationSigmoid(goals.getHydrationGoal(), calorieCounter.calculateHydration());
            double weightVal = recommend.weightSigmoid(goals.getDesiredWeight(), calorieCounter.calculateCalories());
            int sleeping = 0;
            int gymRig = 0;
            for (Activities a: calorieCounter.getActivities()) {
                sleeping += a.getSleep();
                gymRig += a.getGymmingRigour();
            }
            sleeping = sleeping / calorieCounter.getActivities().size();
            gymRig = gymRig / calorieCounter.getActivities().size();
            double sleepVal = recommend.sleepSigmoid(goals.getDesiredSleep(), sleeping);
            double gymValue = recommend.gymRigourSigmoid(goals.getDesiredGymRigour(),gymRig);
            ArrayList<Double> sigmoidValues = recommend.getMessage(weightVal, hydValue, sleepVal, gymValue);
            for (Double d : sigmoidValues) {
                if (d == weightVal) {
                    text = text + recommend.weightMessage(weightVal) + "\n";
                } else if (d == hydValue) {
                    text = text + recommend.hydrationMessage(hydValue) + "\n";
                } else if (d == sleepVal) {
                    System.out.println(recommend.sleepMessage(sleepVal));
                    text = text + recommend.sleepMessage(sleepVal) + "\n";
                } else {
                    text = text + recommend.gymRigourMessage(gymValue) + "\n";
                }
            }
            msg.setText(text);
            VBox layout = new VBox(10);
            layout.getChildren().addAll(msg);
            layout.setAlignment(Pos.CENTER);
            Scene scene = new Scene(layout);
            window.setScene(scene);
            window.showAndWait();
        });
    }

    @FXML void setSaveAndQuitButton() throws IOException {
        StoredData str = new StoredData(calorieCounter, goals, attr);
        Writer.write(str);
    }
}

