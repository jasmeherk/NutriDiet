package model;

public class Food {
    String foodName;
    int calories;
// MODIFIES : this

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


