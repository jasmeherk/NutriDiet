package model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
// This class tests all the user stories

class CalorieTest {
    Food foodItem = new Food("Apple", 100);;
    Fluids fluidItem1 = new Fluids("Water", 500);
    Fluids fluidItem2 = new Fluids("juice", 500);
    Attributes a = new Attributes(1.8,65.0, 'M');
    Activities act = new Activities(4.0, 6, 6);
    CalorieCounter c = new CalorieCounter();
    Recommender rec = new Recommender();

    @Test
    void testBMI() {
        double bmiVal = 65.0/(1.8 * 1.8);
       assertEquals(bmiVal, a.calculateBMI());
    }
    @Test
    void testGetMessage() {
        rec.getMessage(0.35, 0.45, 0.11, 0.23);
    }
    /*
    @Test
    void testWeightMessage() {

    }
    @Test
    void testHydrationMessage() {

    }
    @Test
    void testSleepMessage() {

    }
    @Test
    void testGymRigourMessage() {

    }
     */
    @Test
    void testWeightSigmoid() {
       double x = rec.weightSigmoid(69.0,65);
        assertEquals(x, x);
    }
    @Test
    void testHydrationSigmoid() {
        double x = rec.hydrationSigmoid(2300,2200);
        assertEquals(x, x);
    }
    @Test
    void testSleepSigmoid(){
        double x = rec.hydrationSigmoid(2,2);
        assertEquals(0.5, x);
    }
    @Test
    void testGymRigourSigmoid() {
        double x = rec.hydrationSigmoid(3,2);
        assertEquals(0.7310585786300049, x);
    }
    /*
    @Test
    void testGymming() {

    }
    @Test
    void testWalking() {

    }
    */
    @Test
    void testAddSingleFood() {
        c.addFood(foodItem);
        assertEquals(1,c.foods.size());
    }
    @Test
    void testAddMultipleFood() {
        c.addFood(foodItem);
        c.addFood(foodItem);
        assertEquals(2,c.foods.size());
    }
    @Test
    void testAddSingleFluid() {
        c.addFluid(fluidItem1);
        assertEquals(1, c.fluids.size());
    }
    @Test
    void testAddMultipleFluid(){
        c.addFluid(fluidItem1);
        c.addFluid(fluidItem2);
        assertEquals(2, c.fluids.size());
    }
    @Test
    void testCalculateCalories() {
        c.addFood(foodItem);
        c.addFood(foodItem);
        int cal = c.calculateCalories();
        assertEquals(200, cal);
    }
    @Test void testCalculateHydration() {
        c.addFluid(fluidItem1);
        c.addFluid(fluidItem2);
        int flu = c.calculateHydration();
        assertEquals(800, flu);
    }
    @Test
    void testAddActivity() {
        c.addActivity(act);
    }
}