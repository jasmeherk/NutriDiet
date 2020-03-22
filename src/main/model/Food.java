package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import sun.java2d.pipe.SpanShapeRenderer;

public class Food extends TableColumn<Food, Object> {
    SimpleStringProperty foodName;
    SimpleIntegerProperty calories;
// EFFECTS : instantiate with inputs for fields

    public Food(String foodName, int calories) {
        this.foodName = new SimpleStringProperty(foodName);
        this.calories = new SimpleIntegerProperty(calories);
    }

    public int getCalories() {
        return calories.get();
    }

    public String getFoodName() {
        return foodName.get();
    }

    public void setFoodName(String foodName) {
        this.foodName.set(foodName);
    }

    public void setCalories(int calories) {
        this.calories.set(calories);
    }
}


