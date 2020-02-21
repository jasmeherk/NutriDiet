package model;

public class Food {
    String foodName;
    int calories;

    public Food(String foodName, int calories) {
        this.foodName = foodName;
        this.calories = calories;
    }

    public int getCalories() {
        return calories;
    }

    public String getFoodName() {
        return foodName;
    }
}


