package model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
// This class tests all the user stories

class CalorieTest {
    Food foodItem = new Food("Apple", 100);;
    Fluids fluidItem1 = new Fluids("Water", 500);
    Fluids fluidItem2 = new Fluids("juice", 500);
    Activities act = new Activities(4.0, 6, 6);
    CalorieCounter c = new CalorieCounter();
    Recommender rec = new Recommender();
    Attributes a = new Attributes(1.8,65.0);
    Goals goal = new Goals(63.0, 7, 5);
    StoredData s = new StoredData(c,goal,a);

    @Test
    void testBMI() {
        double bmiVal = 65.0/(1.8 * 1.8);
       assertEquals(bmiVal, a.calculateBMI());
    }

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
        double x = rec.sleepSigmoid(2,2);
        assertEquals(0.5, x);
    }
    @Test
    void testGymRigourSigmoid() {
        double x = rec.gymRigourSigmoid(3,2);
        assertEquals(0.7310585786300049, x);
    }

    @Test
    void testGymming() {
        assertEquals(3580, act.gymming(4000));
    }
    @Test
    void testWalking() {
        assertEquals(1840, act.walking(2000));
    }

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
    void testGetMessage() {
        rec.getMessage(0.5, 0.49, 0.55, 0.11);
        rec.getMessage(0.49, 0.5, 0.96, 0.98);
        rec.getMessage(0.8, 0.7, 0.5, 0.41);
        rec.getMessage(0.35, 0.15, 0.11, 0.5);
    }
    @Test
    void testCalculateCalories() {
        c.addFood(foodItem);
        c.addFood(foodItem);
        int cal = c.calculateCalories();
        assertEquals(200, cal);
        c.addActivity(act);
        int cal2 = c.calculateCalories();
        assertEquals(-380, cal2);
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
    @Test
    void testGetActivity() {
        c.getActivities();
    }
    @Test
    void testGetFood() {
        c.getFoods();
    }
    @Test
    void testGetFluids() {
        c.getFluids();
    }
    @Test
    void testGetDesiredWeight() {
        assertEquals(63.0,goal.getDesiredWeight());
    }
    @Test
    void testGetDesiredSleep() {
        assertEquals(7, goal.getDesiredSleep());
    }
    @Test
    void testGetDesiredGymRigour() {
        assertEquals(5, goal.getDesiredGymRigour());
    }
    @Test
    void testGetDesiredHydrationGoal() {
        assertEquals(2300, goal.getHydrationGoal());
    }
    @Test
    void testGetFoodName() {
        assertEquals("Apple", foodItem.getFoodName());
    }

    @Test
    void testGetFluidName() {
        assertEquals("Water", fluidItem1.getFluidName());
    }

    @Test
    void testGetQuantityInMl() {
        assertEquals(500, fluidItem1.getQuantityinML());
    }
    @Test
    void testGetCalories() {
        assertEquals(100, foodItem.getCalories());
    }
    @Test
    void testGetAttrData() {
        assertEquals(a,s.getAttrData());
    }
    @Test
    void testGetGoalData() {
        assertEquals(goal,s.getGoalData());
    }
    @Test
    void testGetCalData() {
        assertEquals(c, s.getCalData());
    }
    @Test
    void testGetSleep() {
        assertEquals(6, act.getSleep());
    }
    @Test
    void testGetGymRigour() {
        assertEquals(6,act.getGymmingRigour());
    }
    @Test
    void testGetWalk() {
        assertEquals(4.0, act.getWalk());
    }
    @Test
    void testGetHeight() {
        assertEquals(1.8,a.getHeight());
    }
    @Test
    void testGetWeight() {
        assertEquals(65.0, a.getWeight());
    }
}