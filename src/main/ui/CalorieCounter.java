
/*
Took calories burnt value from https://www.verywellfit.com/walking-calories-burned-by-miles-3887154 to be 65 calories for walking per mile
which when converted to kilometres, gives around 40 calories
This is the main calorie counter class where UI runs and a lot of calorie getter and setter methods exist
 */

package ui;

import model.Attributes;
import model.Recommender;

import java.util.ArrayList;
import java.util.Scanner;

public class CalorieCounter {
    ArrayList<Food> foods;
    ArrayList<Fluids> fluids;
    ArrayList<Activities> activities;
    Scanner input = new Scanner(System.in);
    Attributes attributes;
    Recommender recommend;
    Goals goals;

    public CalorieCounter() {
        runUI();
    }

    public void runUI() {
        foods = new ArrayList<>();
        fluids = new ArrayList<>();
        activities = new ArrayList<>();
        recommend = new Recommender();
        System.out.println("First write your basic details");
        System.out.println("------------------------------");
        System.out.println("Enter height (metres)");
        double height = input.nextDouble();
        System.out.println("Enter weight (kgs)");
        double weight = input.nextDouble();
        System.out.println("Enter Gender (M/F)");
        char gender = input.next().charAt(0);
        attributes = new Attributes(height, weight, gender);
        System.out.println("Now enter your goals."
                +  " Think before entering as you won't be able to change them once entered."
                +  " We shall make sure you are committed");
        System.out.println("----------------------------------"
                +   "----------------------"
                +  "-------------------------------"
                +  "-------------------------------------------");
        makeGoals(weight);
        persistMenuDisplay();
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
    // EFFECTS: displays menu of options to user

    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tc -> Enter Calories");
        System.out.println("\tf -> Enter Fluids");
        System.out.println("\ta -> Enter Activities");
        System.out.println("\tb -> Calculate BMI");
        System.out.println("\ts -> Show Food and Fluids");
        System.out.println("\tg -> Get Recommendation");
        System.out.println("\tq -> quit");
    }
    // MODIFIES: this
    // EFFECTS: processes user command according to property entered

    private void processCommand(String command) {
        switch (command) {
            case "c":
                processCalories();
                break;
            case "f":
                processFluids();
                break;
            case "a":
                processActivity();
                break;
            case "g":
                checkValidityReccomendation();
                break;
            case "b":
                System.out.println(attributes.calculateBMI());
                break;
            case "s":
                display();
                break;
            default:
                System.out.println("Selection invalid...");
                break;
        }
    }

    // MODIFIES : activities
    // EFFECTS : Adds Activity to list of activities
    public void addActivity(Activities a) {
        activities.add(a);
    }
//EFFECTS: Adds a food to list

    void processCalories() {
        input = new Scanner(System.in);
        System.out.println("Enter Food item");
        String food = input.nextLine();
        System.out.println("Enter calories for " + food);
        int calories = input.nextInt();
        Food item = new Food(food,calories);
        addFood(item);
    }
//EFFECTS: adds a fluid to list

    void processFluids() {
        input = new Scanner(System.in);
        System.out.println("Enter Fluid");
        String fluid = input.nextLine();
        System.out.println("Enter quantity for " + fluid);
        int quantity = input.nextInt();
        Fluids item = new Fluids(fluid,quantity);
        addFluid(item);
    }
// EFFECTS : adds Activity to list

    void processActivity() {
        input = new Scanner(System.in);
        System.out.println("Enter hours slept");
        int sleep = input.nextInt();
        System.out.println("Enter how strenuous your gym workout was on a scale from 1-10 (Enter 0 if you didn't go)");
        int gymRigour = input.nextInt();
        System.out.println("Enter distance walked (km)");
        double distance = input.nextDouble();
        Activities activities1 = new Activities(distance,sleep,gymRigour);
        addActivity(activities1);
    }
    //REQUIRES : weight >= 0
    // EFFECTS : makes goals for each attribubte

    public void makeGoals(double weight) {
        for (; ; ) {
            System.out.println("Enter the desired weight (kgs)");
            double desiredWeight = input.nextDouble();
            System.out.println("Enter desired sleep (hours)");
            int desiredSleep = input.nextInt();
            System.out.println("How much sleep do you currently get on average (hours)");
            int sleep = input.nextInt();
            System.out.println("Enter desired gym rigor (1-10 and 0 if no gym, however we recommend it)");
            int desiredGymRigour = input.nextInt();
            System.out.println("Enter current gym rigor (1-10 and 0 if no gym)");
            int gymRigour = input.nextInt();
            double sleepSigVal = Math.abs(recommend.sleepSigmoid(desiredSleep, sleep) - 0.5);
            double gymRigourSigVal = Math.abs(recommend.gymRigourSigmoid(desiredGymRigour, gymRigour) - 0.5);
            double weightSigVal = Math.abs(recommend.weightSigmoid(desiredWeight, (int) weight) - 0.5);
            double averageSigmoid = (sleepSigVal + gymRigourSigVal + weightSigVal) / 3;
            if (checkGoalsTooAmbitious(averageSigmoid)) {
                System.out.println("Please rethink, goals seem too ambitious");
                continue;
            }
            goals = new Goals(desiredWeight, desiredSleep, desiredGymRigour);
            break;
        }
    }
// EFFECTS : Displays menu until quit

    void persistMenuDisplay() {
        boolean keepGoing = true;
        String command;

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                System.out.println("Are you sure you want to quit? If you quit, all data would be lost (y/n)");
                if (input.next().equals("y")) {
                    keepGoing = false;
                }
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nSorry to see you go! Hope to see you again");
    }
    //REQUIRES : averageSigmoid >= 0
    //EFFECTS : checks if goal is attainable

    public boolean checkGoalsTooAmbitious(double averageSigmoid) {
        return averageSigmoid >= 0.4;
    }
    // EFFECTS : Displays Food

    void displayFood() {
        for (Food food: foods) {
            System.out.println("Name : " + food.foodName + " Calories = " + food.calories);
        }
    }
    // EFFECTS : Displays Fluid

    void displayFluid() {
        for (Fluids fluid: fluids) {
            System.out.println("Name : " + fluid.fluidName + " Quantity = " + fluid.quantityinML);
        }
    }
    // EFFECTS : gets recommendation

    void getSigmoidMessage() {
        double hydValue = recommend.hydrationSigmoid(Goals.hydrationGoal, calculateHydration());
        double weightVal = recommend.weightSigmoid(goals.desiredWeight, calculateCalories());
        double sleepVal = recommend.sleepSigmoid(goals.desiredSleep, activities.get(0).sleep);
        int gymRigour = activities.get(0).gymmingRigour;
        double gymValue = recommend.gymRigourSigmoid(goals.desiredGymRigour,gymRigour);
        recommend.getMessage(weightVal, hydValue, sleepVal, gymValue);
    }
    // EFFECTS : Checks if user can ask for recommendation

    void checkValidityReccomendation() {
        if (activities.isEmpty()) {
            System.out.println("Log in Activities first");
            persistMenuDisplay();
        } else {
            getSigmoidMessage();
        }
    }
    //EFFECTS : Displays list of items

    void display() {
        displayFood();
        displayFluid();
    }

}
