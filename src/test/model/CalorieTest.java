package model;

import org.junit.jupiter.api.*;
import ui.*;

import static org.junit.jupiter.api.Assertions.*;
// This class tests all the user stories

class CalorieTest {
    Food foodItem = new Food("Apple", 100);;
    Fluids fluidItem1 = new Fluids("Water", 500);
    Fluids fluidItem2 = new Fluids("juice", 500);
    Attributes a = new Attributes(1.8,65.0, 'M');
    Activities act = new Activities(4.0, 6, 6);
    CalorieCounter c = new CalorieCounter();

    @Test
    void testBMI() {
        double bmiVal = 65.0/(1.8 * 1.8);
       assertEquals(bmiVal, a.calculateBMI());
    }
}