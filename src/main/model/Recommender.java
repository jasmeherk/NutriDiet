package model;

import java.util.*;
//  This class gives the user recommendations on what to do and has all the math to that procedure

public class Recommender {
    static final double caloriesToKg = 0.00012959782; // Taken from https://www.convertunits.com/from/calories/to/kg
    //REQUIRES : x
    // EFFECTS : returns a value between 0 and 1

    private static double sigmoid(double x) {
        return  (1 / (1 + Math.pow(Math.E,(x * -1))));
    }
    // REQUIRES : desired property and property (for all functions of the form variableSigmoid)
    // EFFECTS : gives sigmoid value of that variable (for all functions of the form variableSigmoid)

    public double weightSigmoid(double desiredWeight, int calories) {
        double difference =  desiredWeight - ((double) calories * caloriesToKg);
        return sigmoid(difference);
    }

    public double hydrationSigmoid(int desiredHydration, int water) {
        int difference = desiredHydration - water;
        return sigmoid(difference);
    }

    public double sleepSigmoid(int desiredSleep, int sleep) {
        int difference = desiredSleep - sleep;
        return sigmoid(difference);
    }

    public double gymRigourSigmoid(int desiredGymRigour, int gymRigour) {
        int difference = desiredGymRigour - gymRigour;
        return sigmoid(difference);
    }
    //REQUIRES for all functions of form variableMessage : sigmoidValue of Variable
    // EFFECTS for all functions of form variableMessage : message for appropriate variable

    public String weightMessage(double sigmoidValue) {
        if (sigmoidValue == 0.5) {
            return "You have achieved your goal! Hurray";
        }
        if (sigmoidValue >= 0.4 && sigmoidValue <= 0.6) {
            return "You have almost attained your desired weight. Don't worry, you are on the right track. Keep it up!";
        }
        if (sigmoidValue > 0.6) {
            return "You are still pretty far from your goal."
                    + " Cut down on junk food and eat a well balanced diet. You can do it!";
        }
        return "You are far away from your goal."
                    + " But don't worry, you still got this. Eat as much as you can alongside working out.";
    }

    public String hydrationMessage(double sigmoidValue) {
        if (sigmoidValue == 0.5) {
            return "Yay you are at the perfect hydration level for the day";
        }
        if (sigmoidValue >= 0.4 && sigmoidValue <= 0.6) {
            return "You are drinking enough water. Keep it up!";
        }
        if (sigmoidValue > 0.6) {
            return "You are not drinking enough water or are drinking other fluids. Please take care!";
        }
        return "You're doing great. Just make sure the majority of what you drink is water ? ";
    }

    public String sleepMessage(double sigmoidValue) {
        if (sigmoidValue == 0.5) {
            return "Yay you slept the perfect amount";
        }
        if (sigmoidValue >= 0.4 && sigmoidValue <= 0.6) {
            return "You are sleeping around the right amount of time. Keep it up!";
        }
        if (sigmoidValue > 0.6) {
            return "Sleep more ! Your body must be well rested for a productive day";
        }
        return "Wake up earlier! Sleeping longer than planned can make a person lazy";
    }

    public String gymRigourMessage(double sigmoidValue) {
        if (sigmoidValue == 0.5) {
            return "Wow you were at the perfect gym rigour";
        }
        if (sigmoidValue >= 0.4 && sigmoidValue <= 0.6) {
            return "You are almost at the perfect gym rigour. Keep it up!";
        }
        if (sigmoidValue > 0.6) {
            return "You are slacking! Work hard if you want to see results";
        }
        return "Don't exert yourself. Tone it down a bit and you're all set!";
    }

    //REQUIRES : Manipulated Sigmoid values of all variables
    //EFFECTS : Gives feedback on habits
    public ArrayList<Double>
            getMessage(double weightValue, double hydrationValue, double sleepValue, double gymRigourValue) {
        ArrayList<Double> sigmoidValues = new ArrayList<>();
        sigmoidValues.add(weightValue);
        sigmoidValues.add(hydrationValue);
        sigmoidValues.add(sleepValue);
        sigmoidValues.add(gymRigourValue);
        sigmoidValues.sort(Collections.reverseOrder());
        return sigmoidValues;
    }

}

