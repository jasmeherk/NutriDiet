
/*
Took calories burnt value from https://www.verywellfit.com/walking-calories-burned-by-miles-3887154 to be 65 calories for walking per mile
which when converted to kilometres, gives around 40 calories
This is the main calorie counter class where UI runs and a lot of calorie getter and setter methods exist
 */

package model;

import ui.UserInterface;

import java.util.ArrayList;

public class CalorieCounter {
    ArrayList<Food> foods;
    ArrayList<Fluids> fluids;
    ArrayList<Activities> activities;
    UserInterface ui;

    public CalorieCounter() {
        runUI();
    }

    public void runUI() {
        foods = new ArrayList<>();
        fluids = new ArrayList<>();
        activities = new ArrayList<>();
    }

    public void addFood(Food f) {
        foods.add(f);
    }

    public void addFluid(Fluids f) {
        fluids.add(f);
    }
// EFFECTS : returns total calories consumed from list and including activities

    public int calculateCalories() {
        int totalCalories = 0;
        for (Food f: foods) {
            totalCalories += f.calories;
        }
        if (!(activities.isEmpty())) {
            totalCalories = activities.get(0).gymming(totalCalories);
            totalCalories = activities.get(0).walking(totalCalories);
        }
        return totalCalories;
    }
    // EFFECTS : returns totalQuantity of Water the user Drank

    public int calculateHydration() {
        int totalQuantity = 0;
        for (Fluids f : fluids) {
            if (f.fluidName.toLowerCase().equals("water")) {
                totalQuantity += f.quantityinML;
            } else {
                totalQuantity += (int) ((double) f.quantityinML * 0.6); // 0.6 is an arbitrary value
            }
        }
        return totalQuantity;
    }

    // MODIFIES : activities
    // EFFECTS : Adds Activity to list of activities
    public void addActivity(Activities a) {
        activities.add(a);
    }


    public ArrayList<Activities> getActivities() {
        return activities;
    }

    public ArrayList<Food> getFoods() {
        return foods;
    }

    public ArrayList<Fluids> getFluids() {
        return fluids;
    }
}
