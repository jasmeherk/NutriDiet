package model;
// Stores user activities

public class Activities {
    public double walk; // Distance walked in Kilometres
    public int sleep; // Duration of sleep in hours
    public int gymmingRigour; // stores hours the user has been gymming
    static final int caloriesLostGymming = 70; // Arbitrary Number
    static final int caloriesBurntPerKmOfWalk = 40;
// MODIFIES : this

    public Activities(double walk, int sleep, int gymmingRigour) {
        this.walk = walk;
        this.sleep = sleep;
        this.gymmingRigour = gymmingRigour;
    }
    // REQUIRES : calories >= 0
    // EFFECTS : calories after gymming

    public int gymming(int calories) {
        return calories - (caloriesLostGymming * gymmingRigour);
    }
    // REQUIRES : calories >= 0
    // EFFECTS : calories after walking

    public int walking(int calories) {
        return calories - (int) (walk * (double) caloriesBurntPerKmOfWalk);
    }
}
