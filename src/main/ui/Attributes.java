package ui;

public class Attributes {
    double height;
    double weight;
    char gender;

    public Attributes(double height, double weight, char gender) {
        this.height = height;
        this.weight = weight;
        this.gender = gender;
    }

    public double calculateBMI() {
        return weight / (height * height);
    }
}
