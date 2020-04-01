
/*
Took calories burnt value from https://www.verywellfit.com/walking-calories-burned-by-miles-3887154 to be 65 calories for walking per mile
which when converted to kilometres, gives around 40 calories
This is the main calorie counter class where UI runs and a lot of calorie getter and setter methods exist
 */

package model;

import java.util.ArrayList;

public class CalorieCounter {
    ArrayList<Food> foods;
    ArrayList<Fluids> fluids;
    ArrayList<Activities> activities;

    public CalorieCounter() {
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
            totalCalories += f.getCalories();
        }
        if (!(activities.isEmpty())) {
            for (Activities a : activities) {
                totalCalories = a.gymming(totalCalories);
                totalCalories = a.walking(totalCalories);
            }
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

    public void setActivities(ArrayList<Activities> activities) {
        this.activities = activities;
    }

    public ArrayList<Food> getFoods() {
        return foods;
    }

    public void setFoods(ArrayList<Food> foods) {
        this.foods = foods;
    }

    public void setFluids(ArrayList<Fluids> fluids) {
        this.fluids = fluids;
    }

    public ArrayList<Fluids> getFluids() {
        return fluids;
    }
}
