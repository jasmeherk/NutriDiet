package ui;

import exceptions.InvalidInputException;
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
        goals = new Goals("0","0","0");
        attr = new Attributes("1.0","1.0");
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

    void setInitialWeightText(String text) throws InvalidInputException {
        initialWeight.setText(text);
        attr.setWeight(text);
    }

    public Label initialHeight;

    void setInitialHeightText(String text) throws InvalidInputException {
        initialHeight.setText(text);
        attr.setHeight(text);
    }

    public Label goalGym;

    void setGymText(String text) throws InvalidInputException {
        goalGym.setText(text);
        goals.setDesiredGymRigour(text);
    }

    public Label goalWeight;

    void setWeightText(String text) throws InvalidInputException {
        goalWeight.setText(text);
        goals.setDesiredWeight(text);
    }

    public Label goalSleep;

    void setSleepText(String text) throws InvalidInputException {
        goalSleep.setText(text);
        goals.setDesiredSleep(text);
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
        double bmi = Double.parseDouble(initialWeight.getText())
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
                foodButtonClicked(foodName.getText(),foodCal.getText());
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

    void foodButtonClicked(String foodName, String foodCal) {
        try {
            Food food = new Food(foodName, foodCal);
            calorieCounter.addFood(food);
        } catch (InvalidInputException exception) {
            PopupController.popupMessage("Please enter an appropriate name and valid number of calories",
                    "Calories can only be of integer type");
        }
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
                fluidButtonClicked(fluidName.getText(), fluidQuantity.getText());
                window.close();
            });
            VBox layout = new VBox(10);
            layout.getChildren().addAll(fluidNameLabel,fluidName,
                    fluidQuantityLabel,fluidQuantity,waterImage,doneButton);
            layout.setAlignment(Pos.CENTER);
            Scene scene = new Scene(layout);
            window.setScene(scene);
            window.showAndWait();
        });
    }

    void fluidButtonClicked(String name, String quant) {
        try {
            Fluids fluid = new Fluids(name, quant);
            calorieCounter.addFluid(fluid);
        } catch (InvalidInputException exception) {
            PopupController.popupMessage("Please enter appropriate name and valid quantity",
                    "Quantity can only be of integer type");
        }
    }

    @FXML void activityButton() {
        addActivityButton.setOnAction(event -> {
            activityButtonClicked();
        });
    }

    void activityButtonClicked() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(350);
        Label gymLabel = new Label();
        gymLabel.setText("Gymming Rigour (0-10)");
        TextField gymVal = new TextField();
        Label walkLabel = new Label();
        walkLabel.setText("Walking (km)");
        TextField walkVal = new TextField();
        Label sleepLabel = new Label();
        sleepLabel.setText("Sleep (hours)");
        TextField sleepVal = new TextField();
        Button doneButton = new Button("Add");
        doneButton.setOnAction(e -> {
            activityAction(walkVal.getText(),sleepVal.getText(),gymVal.getText());
            window.close();
        });
        VBox layout = new VBox(10);
        layout.getChildren().addAll(gymLabel,gymVal,walkLabel,walkVal,sleepLabel,sleepVal,sleepImage,doneButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

    void activityAction(String walkVal, String sleepVal, String gymVal) {
        try {
            Activities a = new Activities(walkVal, sleepVal, gymVal);
            calorieCounter.addActivity(a);
        } catch (InvalidInputException exception) {
            PopupController.popupMessage("Please enter appropriate values for each field",
                    "All values should be numbers, and gym rigour must be between 0 and 10");
        }
    }

    @FXML
    void setReccomendButton() {
        reccomendButton.setOnAction(event -> {
            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setMinWidth(350);
            TextArea msg = new TextArea();
            msg.setText(getText(""));
            VBox layout = new VBox(10);
            layout.getChildren().addAll(msg);
            layout.setAlignment(Pos.CENTER);
            Scene scene = new Scene(layout);
            window.setScene(scene);
            window.showAndWait();
        });
    }

    String getText(String text) {
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
            text = check(text, d, weightVal, hydValue, sleepVal, gymValue);
        }
        return text;
    }

    String check(String text, Double d, double weightVal, double hydValue, double sleepVal, double gymValue) {
        if (d == weightVal) {
            text = text + recommend.weightMessage(weightVal) + "\n";
        } else if (d == hydValue) {
            text = text + recommend.hydrationMessage(hydValue) + "\n";
        } else if (d == sleepVal) {
            text = text + recommend.sleepMessage(sleepVal) + "\n";
        } else {
            text = text + recommend.gymRigourMessage(gymValue) + "\n";
        }
        return text;
    }

    @FXML void setSaveAndQuitButton() throws IOException {
        StoredData str = new StoredData(calorieCounter, goals, attr);
        Writer.write(str);
    }
}

