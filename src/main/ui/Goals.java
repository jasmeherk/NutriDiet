package ui;


public class Goals {
    static final double maxWeightLossPerDay = 0.25;
    double desiredWeight;
    int duration; // How much time in days to reach desired goal

    public Goals( double desiredWeight, int duration) {
        this.desiredWeight = desiredWeight;
        this.duration = duration;
    }
    public void goalsTooAmbitious() {
        if ()
            System.out.println("Please rethink, goals seem too ambitious");
    }
}

