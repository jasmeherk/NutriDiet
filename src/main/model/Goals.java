package model;
//Stores users goals

import exceptions.InvalidInputException;

public class Goals {
    static final int hydrationGoal = 2300; // Since it is between 2200-2400 the average is taken (2300)
    double desiredWeight;
    int desiredSleep;
    int desiredGymRigour;

    // REQUIRES : a double weight, int sleep, int gym rigour
    // EFFECTS : assigns the parameters to the fields
    public Goals(String desiredWeight, String desiredSleep, String desiredGymRigour) {
        setDesiredGymRigour(desiredGymRigour);
        setDesiredSleep(desiredSleep);
        setDesiredWeight(desiredWeight);
    }

    public double getDesiredWeight() {
        return desiredWeight;
    }

    public int getDesiredGymRigour() {
        return desiredGymRigour;
    }

    public int getDesiredSleep() {
        return desiredSleep;
    }

    public static int getHydrationGoal() {
        return hydrationGoal;
    }

    public void setDesiredGymRigour(String desiredGymRigour) {
        try {
            int gymRigour = Integer.parseInt(desiredGymRigour);
            if (gymRigour > 10 || gymRigour < 0) {
                throw new InvalidInputException();
            } else {
                this.desiredGymRigour = gymRigour;
            }
        } catch (NumberFormatException e) {
            throw new InvalidInputException();
        }
    }

    public void setDesiredWeight(String desiredWeight) {
        try {
            double weight = Double.parseDouble(desiredWeight);
            this.desiredWeight = weight;
        } catch (InvalidInputException e) {
            throw new InvalidInputException();
        }
    }

    public void setDesiredSleep(String desiredSleep) {
        try {
            int sleeping = Integer.parseInt(desiredSleep);
            this.desiredSleep = sleeping;
        } catch (NumberFormatException e) {
            throw new InvalidInputException();
        }
    }
}

