package model;
// Stores user attributes and manipulates them to give BMI

import exceptions.InvalidInputException;

public class Attributes {
    double height; // in metres
    double weight; // in kgs
    // MODIFIES : this

    public Attributes(String height, String weight) {
        setHeight(height);
        setWeight(weight);
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

    public void setHeight(String height) {
        try {
            double heightAttribute = Double.parseDouble(height);
            if (heightAttribute <= 0.0) {
                throw new InvalidInputException();
            } else {
                this.height = heightAttribute;
            }
        } catch (NumberFormatException e) {
            throw new InvalidInputException();
        }
    }

    public void setWeight(String weight) {
        try {
            double weightAttribute = Double.parseDouble(weight);
            if (weightAttribute <= 0.0) {
                throw new InvalidInputException();
            } else {
                this.weight = weightAttribute;
            }
        } catch (NumberFormatException e) {
            throw new InvalidInputException();
        }
    }
}
