package model;
// Stores user attributes and manipulates them to give BMI

public class Attributes {
    double height; // in metres
    double weight; // in kgs
    // MODIFIES : this

    public Attributes(double height, double weight) {
        this.height = height;
        this.weight = weight;
    }
// EFFECTS : calculates BMI of USer

    public double calculateBMI() {
        return weight / (height * height);
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }
}
