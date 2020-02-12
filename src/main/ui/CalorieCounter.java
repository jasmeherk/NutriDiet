
/*
Took calories burnt value from https://www.verywellfit.com/walking-calories-burned-by-miles-3887154 to be 65 calories for walking per mile
which when converted to kilometres, gives around 40 calories
 */

package ui;
        import java.util.ArrayList;
        import java.util.Scanner;

public class CalorieCounter{

    ArrayList<Food> foods;
    ArrayList<Fluids> fluids;
    ArrayList<Activities> activities;
    static final int minimumDrinkingQuantityOfWater = 2000;
    static final int hydration = 2300; // Since it is between 2200-2400 the average is taken (2300)
    static final int minimumCaloricIntake = 1500;
    Scanner input = new Scanner(System.in);
    Activities activity;
    Attributes attributes;
    Recommender recommend;
    Goals goals;

    public CalorieCounter() {
        System.out.println("First write your basic details");
        System.out.println("------------------------------");
        System.out.println("Enter height");
        double height = input.nextDouble();
        System.out.println("Enter weight");
        double weight= input.nextDouble();
        System.out.println("Enter Gender (M/F)");
        char gender = input.next().charAt(0);
        attributes = new Attributes(height, weight, gender);
        System.out.println("Now enter your goals. Think before entering as you won't be able to change them once entered. We shall make sure you are committed");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
        while // goals while loop
        System.out.println("Enter the desired weight you want to achieve");

        foods = new ArrayList<>();
        fluids = new ArrayList<>();
        activities = new ArrayList<>();
        recommend = new Recommender();
        boolean keepGoing = true;
        String command = null;

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                System.out.println("Are you sure you want to quit? If you quit, all data would be lost (y/n)");
                if (input.next() == "y") {
                    keepGoing = false;
                }
                else {
                    continue;
                }
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nSorry to see you go! Hope to see you again");
    }
    public void addFood(Food f) {
        foods.add(f);
    }
    public void removeFood(Food f) {
        foods.remove(f);
    }
    public void addFluid(Fluids f) {
        fluids.add(f);
    }
    public void removeFood(Fluids f) {
        fluids.remove(f);
    }
    public int calculateCalories() {
        int totalCalories =0;
        for(Food f: foods) {
            totalCalories += f.calories;
        }
        totalCalories = activity.gymming(totalCalories);
        totalCalories = activity.walking(totalCalories);
        return totalCalories;
    }
    // EFFECTS : returns true if user is drinking enough water, false otherwise
    public boolean isDrinkiningEnoughWater() {
        int totalQuantity = 0;
        for (Fluids f : fluids) {
            totalQuantity += f.quantityinML;
        }
        if (totalQuantity >= 2200) {
            return true;
        }
        return false;
    }
    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tc -> Enter Calories");
        System.out.println("\tf -> Enter Fluids");
        System.out.println("\ta -> Enter Activities");
        System.out.println("\tr -> Get Recommendation");
        System.out.println("\tq -> quit");
    }
    // MODIFIES: this
    // EFFECTS: processes user command according to property entered
    private void processCommand(String command) {
        if (command.equals("c")) {
            System.out.println("Enter Food item");
            String food = input.nextLine();
            System.out.println("Enter calories for " + food);
            int calories = input.nextInt();
            Food item = new Food(food,calories);
            addFood(item);
        } else if (command.equals("f")) {
            System.out.println("Enter Fluid");
            String fluid = input.nextLine();
            System.out.println("Enter quantity for " + fluid);
            int quantity = input.nextInt();
            Fluids item = new Fluids(fluid,quantity);
            addFluid(item);
        } else if (command.equals("a")) {
            System.out.println("Enter Distance walked");
            double walk = input.nextDouble();
            System.out.println("Enter hours slept");
            double sleep = input.nextDouble();
            System.out.println("Enter how strenuous your gym workout was on a scale from 1-10 (Enter 0 if you didn't go)");
            int gymRigour = input.nextInt();
            System.out.println("Enter distance walked (km)");
            int distance = input.nextInt();
            activity = new Activities(distance,sleep,gymRigour);
            addActivity(activity);
        } else if (command.equals("r")) {
            recommend.getMessage();
        } else {
            System.out.println("Selection not valid...");
        }
    }
    public void addActivity(Activities a) {
        activities.add(a);
    }

}
