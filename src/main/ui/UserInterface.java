package ui;

import model.*;
import persistence.Writer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import persistence.Reader;

public class UserInterface {
    double height;
    double weight;
    Attributes attr;
    CalorieCounter cc;
    Recommender recommend;
    Goals goals;
    Scanner input = new Scanner(System.in);

    public UserInterface() throws IOException {
        System.out.println("First write your basic details");
        System.out.println("------------------------------");
        System.out.println("Enter height (metres)");
        height = input.nextDouble();
        System.out.println("Enter weight (kgs)");
        weight = input.nextDouble();
        System.out.println("Now enter your goals."
                +  " Think before entering as you won't be able to change them once entered."
                +  " We shall make sure you are committed");
        System.out.println("----------------------------------"
                +   "----------------------"
                +  "-------------------------------"
                +  "-------------------------------------------");
        attr = new Attributes(height,weight);
        recommend = new Recommender();
        makeGoals(getWeight());
        cc = new CalorieCounter();
        persistMenuDisplay();
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }
    // EFFECTS : Displays menu until quit

    public void persistMenuDisplay() throws IOException {
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
    // MODIFIES: this
    // EFFECTS: processes user command according to property entered

    private void processCommand(String command) throws IOException {
        if (command.equals("c")) {
            processCalories();
        } else if (command.equals("f")) {
            processFluids();
        } else if (command.equals("a")) {
            processActivity();
        } else if (command.equals("g")) {
            checkValidityReccomendation();
        } else if (command.equals("b")) {
            System.out.println(attr.calculateBMI());
        } else if (command.equals("d")) {
            StoredData s = new StoredData(cc, goals, attr);
            Writer.write(s);
        } else if (command.equals("s")) {
            display();
        } else if (command.equals("r")) {
            Reader.read();
        } else {
            System.out.println("Selection invalid...");
        }
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
        System.out.println("\td -> Download Data to File");
        System.out.println("\tr -> Read file");
        System.out.println("\tq -> quit");
    }
    //EFFECTS: adds a fluid to list

    void processFluids() {
        input = new Scanner(System.in);
        System.out.println("Enter Fluid");
        String fluid = input.nextLine();
        System.out.println("Enter quantity for " + fluid);
        int quantity = input.nextInt();
        Fluids item = new Fluids(fluid,quantity);
        cc.addFluid(item);
    }
    //EFFECTS: Adds a food to list

    void processCalories() {
        input = new Scanner(System.in);
        System.out.println("Enter Food item");
        String food = input.nextLine();
        System.out.println("Enter calories for " + food);
        int calories = input.nextInt();
        Food item = new Food(food,calories);
        cc.addFood(item);
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
        cc.addActivity(activities1);
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
    //REQUIRES : averageSigmoid >= 0
    //EFFECTS : checks if goal is attainable

    public boolean checkGoalsTooAmbitious(double averageSigmoid) {
        return averageSigmoid >= 0.4;
    }
    // EFFECTS : Displays Food

    void displayFood() {
        ArrayList<Food> arr = cc.getFoods();
        for (Food food: arr) {
            System.out.println("Name : " + food.getFoodName() + " Calories = " + food.getCalories());
        }
    }
    // EFFECTS : Displays Fluid

    void displayFluid() {
        ArrayList<Fluids> arr = cc.getFluids();
        for (Fluids fluid: arr) {
            System.out.println("Name : " + fluid.getFluidName() + " Quantity = " + fluid.getQuantityinML());
        }
    }
    // EFFECTS : Checks if user can ask for recommendation

    void checkValidityReccomendation() throws IOException {
        if (cc.getActivities().isEmpty()) {
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
    // EFFECTS : gets recommendation

    void getSigmoidMessage() {
        double hydValue = recommend.hydrationSigmoid(goals.getHydrationGoal(), cc.calculateHydration());
        double weightVal = recommend.weightSigmoid(goals.getDesiredWeight(), cc.calculateCalories());
        double sleepVal = recommend.sleepSigmoid(goals.getDesiredSleep(), cc.getActivities().get(0).sleep);
        int gymRigour = cc.getActivities().get(0).gymmingRigour;
        double gymValue = recommend.gymRigourSigmoid(goals.getDesiredGymRigour(),gymRigour);
        recommend.getMessage(weightVal, hydValue, sleepVal, gymValue);
    }
}
