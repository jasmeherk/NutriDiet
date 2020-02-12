package model;
// Stores user attributes and manipulates them to give BMI

public class Attributes {
    double height; // in metres
    double weight; // in kgs
    char gender;

    public Attributes(double height, double weight, char gender) {
        this.height = height;
        this.weight = weight;
        this.gender = gender;
    }
// EFFECTS : calculates BMI of USer

    public double calculateBMI() {
        return weight / (height * height);
    }
}
