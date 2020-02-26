package persistence;

import com.google.gson.Gson;
import model.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.ArrayList;


// A reader that can read account data from a file
public class Reader {

    public static void read() throws IOException {
        Gson gson = new Gson();
        JFileChooser choose = new JFileChooser();
        FileNameExtensionFilter extFilter = new FileNameExtensionFilter("JSON File", "json");
        choose.setFileFilter(extFilter);
        int returnVal = choose.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("You have chosen to open this file: "
                    + choose.getSelectedFile().getName());
        }
        File jsonFile = choose.getSelectedFile();
        BufferedReader br = new BufferedReader(new FileReader(jsonFile));
        StoredData models = gson.fromJson(br, StoredData.class);
        printAttributeData(models);
        printGoalsData(models);
        ArrayList<Food> foods = models.getCalData().getFoods();
        ArrayList<Fluids> fluids = models.getCalData().getFluids();
        ArrayList<Activities> activities = models.getCalData().getActivities();
        printFoods(foods);
        printFluids(fluids);
        printActivities(activities);
    }

    static void printFoods(ArrayList<Food> foods) {
        for (Food f : foods) {
            System.out.print("Calories : " + f.getCalories() + "     ");
            System.out.println("Food : " + f.getFoodName());
        }
    }

    static void printFluids(ArrayList<Fluids> fluids) {
        for (Fluids f : fluids) {
            System.out.print("Quantity (ml) : " + f.getQuantityinML() + "     ");
            System.out.println("Fluid : " + f.getFluidName());
        }
    }

    static void printActivities(ArrayList<Activities> activities) {
        for (Activities a : activities) {
            System.out.print("Walking : " + a.getWalk() + "     ");
            System.out.print("Gymming : " + a.getGymmingRigour() + "     ");
            System.out.println("Sleeping : " + a.getSleep());
        }
    }

    static void printAttributeData(StoredData models) {
        System.out.println("Height: " + models.getAttrData().getHeight());
        System.out.println("Weight : " + models.getAttrData().getWeight());
    }

    static void printGoalsData(StoredData models) {
        System.out.println("Gym Rigour : " + models.getGoalData().getDesiredGymRigour());
        System.out.println("Hydration Goal : " + models.getGoalData().getHydrationGoal());
        System.out.println("Sleep Goal : " + models.getGoalData().getDesiredSleep());
        System.out.println("Weight goal : " + models.getGoalData().getDesiredWeight());
    }
}
