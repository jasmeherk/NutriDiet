package model;


import exceptions.InvalidInputException;

public class Food {
    String foodName;
    int calories;
// EFFECTS : instantiate with inputs for fields

    public Food(String foodName, String calories) throws InvalidInputException {
        setFoodName(foodName);
        setCalories(calories);
    }

    public int getCalories() {
        return calories;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
            this.foodName = foodName;
    }

    public void setCalories(String calories) {
        try {
            int cal = Integer.parseInt(calories);
            this.calories = cal;
        } catch (NumberFormatException e) {
            throw new InvalidInputException();
        }
    }
}


