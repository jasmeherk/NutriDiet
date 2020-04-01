package model;
// Stores user activities

import exceptions.InvalidInputException;

public class Activities {
    double walk; // Distance walked in Kilometres
    int sleep; // Duration of sleep in hours
    int gymmingRigour; // stores hours the user has been gymming
    static final int caloriesLostGymming = 70; // Arbitrary Number
    static final int caloriesBurntPerKmOfWalk = 40;
// MODIFIES : this

    public Activities(String walk, String sleep, String gymmingRigour) {
        setWalk(walk);
        setSleep(sleep);
        setGymmingRigour(gymmingRigour);
    }

    // REQUIRES : calories >= 0
    // EFFECTS : calories after gymming

    public int gymming(int calories) {
        return calories - (caloriesLostGymming * gymmingRigour);
    }
    // REQUIRES : calories >= 0
    // EFFECTS : calories after walking

    public int walking(int calories) {
        return calories -  ((int) (walk * (double) caloriesBurntPerKmOfWalk));
    }

    public double getWalk() {
        return walk;
    }

    public int getGymmingRigour() {
        return gymmingRigour;
    }

    public int getSleep() {
        return sleep;
    }

    public void setGymmingRigour(String gymmingRigour) {
        try {
            int gymRigour = Integer.parseInt(gymmingRigour);
            if (gymRigour > 10 || gymRigour < 0) {
                throw new InvalidInputException();
            } else {
                this.gymmingRigour = gymRigour;
            }
        } catch (NumberFormatException e) {
            throw new InvalidInputException();
        }
    }

    public void setSleep(String sleep) {
        try {
            int sleeping = Integer.parseInt(sleep);
            this.sleep = sleeping;
        } catch (NumberFormatException e) {
            throw new InvalidInputException();
        }
    }

    public void setWalk(String walk) {
        try {
            double walking = Double.parseDouble(walk);
            this.walk = walking;
        } catch (NumberFormatException e) {
            throw new InvalidInputException();
        }
    }
}
