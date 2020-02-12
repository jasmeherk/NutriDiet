package ui;

public class Activities {
    double walk; // Distance walked in Kilometres
    double sleep; // Duration of sleep in hours
    int gymmingRigour; // stores hours the user has been gymming
    static final int caloriesLostGymming = 70; // Arbritary Number
    static final int caloriesBurntPerKmOfWalk = 40;

    public Activities(double walk, double sleep, int gymmingRigour) {
        this.walk = walk;
        this.sleep = sleep;
        this.gymmingRigour = gymmingRigour;
    }

    public boolean isEnoughSleep() {
        if (gymmingRigour > 4 && sleep < 8) {
            return false;
        } else if (sleep < 7.0) {
            return false;
        }
        return true;
    }

    public int gymming(int calories) {
        return calories - (caloriesLostGymming * gymmingRigour);
    }

    public int walking(int calories) {
        return calories - (int) (walk * (double) caloriesBurntPerKmOfWalk);
    }
}
