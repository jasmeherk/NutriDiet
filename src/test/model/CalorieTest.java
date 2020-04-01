package model;

import exceptions.InvalidInputException;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
// This class tests all the user stories

class CalorieTest {
    Food foodItem;
    Fluids fluidItem1;
    Fluids fluidItem2;
    Activities act;
    CalorieCounter c;
    Recommender rec;
    Attributes a;
    Goals goal;
    StoredData s;

   @BeforeEach void runBefore() {
        foodItem = new Food("Apple", "100");;
        fluidItem1 = new Fluids("Water", "500");
        fluidItem2 = new Fluids("juice", "500");
        act = new Activities("4.0", "6", "6");
        c = new CalorieCounter();
        rec = new Recommender();
        a = new Attributes("1.8","65.0");
        goal = new Goals("63.0", "7", "5");
        s = new StoredData(c,goal,a);
    }

    CalorieTest() throws InvalidInputException {
    }

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
    @Test void testSetGymmingRigourGreaterThan10() {
        try {
            act.setGymmingRigour("11");
            fail("Shouldn't have reached here");
        } catch(InvalidInputException e) {
            System.out.println("Great");
        }
    }
    @Test void testSetGymmingRigourLessThan0() {
        try {
            act.setGymmingRigour("-1");
            fail("Shouldn't have reached here");
        } catch(InvalidInputException e) {
            System.out.println("Great");
        }
    }
    @Test void testSetGymmingRigour() {
        try {
            act.setGymmingRigour("10");
            assertEquals(act.getGymmingRigour(),10);
        } catch(InvalidInputException e) {
            fail("Shouldn't have reached here");
        }
    }
    @Test void testSetGymmingRigourNumberFormat() {
        try {
            act.setGymmingRigour("hello");
            fail("Shouldn't have reached here");
        } catch(InvalidInputException e) {
            System.out.println("Great");
        }
    }
    @Test void testSetSleepThrowsException() {
        try {
            act.setSleep("hi");
            fail("Shouldn't have reached here");
        } catch (InvalidInputException e) {
            System.out.println("Great");
        }
    }
    @Test void testSetSleepThrowsNoException() {
        try {
            act.setSleep("8");
            assertEquals(act.getSleep(),8);
        } catch (InvalidInputException e) {
            fail("Shouldn't have reached here");
        }
    }
    @Test void testSetWalkThrowsException() {
        try {
            act.setWalk("hi");
            fail("Shouldn't have reached here");
        } catch (InvalidInputException e) {
            System.out.println("Great");
        }
    }
    @Test void testSetWalkThrowsNoException() {
        try {
            act.setWalk("6.0");
            assertEquals(act.getWalk(),6);
        } catch (InvalidInputException e) {
            fail("Shouldn't have reached here");
        }
    }

    @Test void testSetDesiredGymmingRigourGreaterThan10() {
        try {
            goal.setDesiredGymRigour("11");
            fail("Shouldn't have reached here");
        } catch(InvalidInputException e) {
            System.out.println("Great");
        }
    }
    @Test void testSetDesiredGymmingRigourLessThan0() {
        try {
            goal.setDesiredGymRigour("-1");
            fail("Shouldn't have reached here");
        } catch(InvalidInputException e) {
            System.out.println("Great");
        }
    }
    @Test void testSetDesiredGymmingRigour() {
        try {
            goal.setDesiredGymRigour("10");
            assertEquals(goal.getDesiredGymRigour(),10);
        } catch(InvalidInputException e) {
            fail("Shouldn't have reached here");
        }
    }
    @Test void testSetDesiredGymmingRigourNumberFormat() {
        try {
            goal.setDesiredGymRigour("hello");
            fail("Shouldn't have reached here");
        } catch(InvalidInputException e) {
            System.out.println("Great");
        }
    }
    @Test void testSetDesiredSleepThrowsException() {
        try {
            goal.setDesiredSleep("hi");
            fail("Shouldn't have reached here");
        } catch (InvalidInputException e) {
            System.out.println("Great");
        }
    }
    @Test void testSetDesiredSleepThrowsNoException() {
        try {
            goal.setDesiredSleep("8");
            assertEquals(goal.getDesiredSleep(),8);
        } catch (InvalidInputException e) {
            fail("Shouldn't have reached here");
        }
    }
    @Test void testSetDesiredWeightThrowsException() {
        try {
            goal.setDesiredWeight("hello");
            fail("Shouldn't have reached here");
        } catch (InvalidInputException e) {
            System.out.println("Great");
        }
    }
    @Test void testSetDesiredWeightThrowsNoException() {
        try {
            goal.setDesiredWeight("6.0");
            assertEquals(goal.getDesiredWeight(),6);
        } catch (InvalidInputException e) {
            fail("Shouldn't have reached here");
        }
    }
    @Test void testSetActivities() {
       boolean check = true;
       int i = 0;
        ArrayList<Activities> a = new ArrayList<>();
        a.add(act);
        c.setActivities(a);
        ArrayList<Activities> as = c.getActivities();
        for (Activities activity : as) {
            if(activity.getSleep() != a.get(i).getSleep() ||
                    activity.getWalk() != a.get(i).getWalk() ||
                    activity.getGymmingRigour() != a.get(i).getGymmingRigour()) {
                check = false;
            }
            i ++;
        }
        assertTrue(check);
    }
    @Test void testSetFoods() {
        boolean check = true;
        int i = 0;
        ArrayList<Food> a = new ArrayList<>();
        a.add(foodItem);
        c.setFoods(a);
        ArrayList<Food> as = c.getFoods();
        for (Food f : as) {
            if(!(f.foodName.equals(a.get(i).foodName))) {
                check = false;
            }
            i++;
        }
        assertTrue(check);
    }
    @Test void testSetFluids() {
        boolean check = true;
        int i = 0;
        ArrayList<Fluids> a = new ArrayList<>();
        a.add(fluidItem1);
        a.add(fluidItem2);
        c.setFluids(a);
        ArrayList<Fluids> as = c.getFluids();
        for (Fluids f : as) {
            if(!(f.fluidName.equals(a.get(i).fluidName))) {
                check = false;
            }
            i++;
        }
        assertTrue(check);
    }
    @Test void testSetFoodName() {
            foodItem.setFoodName("app");
            assertEquals(foodItem.getFoodName(),"app");
    }
    @Test void testSetFoodCalException() {
        try {
            foodItem.setCalories("hello");
            fail("Shouldn't have reached here");
        } catch (InvalidInputException e) {
            System.out.println("Great");
        }
    }
    @Test void testSetFoodCalNoException() {
        try {
            foodItem.setCalories("150");
            assertEquals(foodItem.getCalories(), 150);
        } catch (InvalidInputException e) {
            fail("Shouldn't have reached here");
        }
    }
    @Test void testSetFluidName() {
        fluidItem1.setFluidName("juice");
        assertEquals(fluidItem1.getFluidName(),"juice");
    }
    @Test void testSetFluidQuantityException() {
        try {
            fluidItem1.setQuantityinML("hello");
            fail("Shouldn't have reached here");
        } catch (InvalidInputException e) {
            System.out.println("Great");
        }
    }
    @Test void testSetFluidQuantityNoException() {
        try {
            fluidItem1.setQuantityinML("150");
            assertEquals(fluidItem1.getQuantityinML(),150);
        } catch (InvalidInputException e) {
            fail("Shouldn't have reached here");
        }
    }
    @Test void testSetWeightThrowsException() {
        try {
            a.setWeight("hello");
            fail("Shouldn't have reached here");
        } catch (InvalidInputException e) {
            System.out.println("Great");
        }
    }
    @Test void testSetWeightThrowsNoException() {
        try {
            a.setWeight("6.0");
            assertEquals(a.getWeight(),6);
        } catch (InvalidInputException e) {
            fail("Shouldn't have reached here");
        }
    }
    @Test void testSetHeightThrowsException() {
        try {
            a.setHeight("hello");
            fail("Shouldn't have reached here");
        } catch (InvalidInputException e) {
            System.out.println("Great");
        }
    }
    @Test void testSetHeightThrowsNoException() {
        try {
            a.setHeight("6.0");
            assertEquals(a.getHeight(),6);
        } catch (InvalidInputException e) {
            fail("Shouldn't have reached here");
        }
    }
    // Sigmoid Messages
    //weightSigmoid
    @Test void testWeightMessage() {
       assertEquals(rec.weightMessage(0.5), "You have achieved your goal! Hurray");
       assertEquals(rec.weightMessage(0.65), "You are still pretty far from your goal." +
                " Cut down on junk food and eat a well balanced diet. You can do it!");
       assertEquals(rec.weightMessage(0.45), "You have almost attained your desired weight." +
               " Don't worry, you are on the right track. Keep it up!");
        assertEquals(rec.weightMessage(0.1), "You are far away from your goal. But don't worry," +
                " you still got this. Eat as much as you can alongside working out.");
    }
    @Test void testHydrationMessage() {
        assertEquals(rec.hydrationMessage(0.5), "Yay you are at the perfect" +
                " hydration level for the day");
        assertEquals(rec.hydrationMessage(0.65), "You are not drinking enough water" +
                " or are drinking other fluids. Please take care!");
        assertEquals(rec.hydrationMessage(0.45), "You are drinking enough water. Keep it up!");
        assertEquals(rec.hydrationMessage(0.1), "You're doing great. " +
                "Just make sure the majority of what you drink is water ? ");
    }
    @Test void testSleepMessage() {
       assertEquals(rec.sleepMessage(0.5), "Yay you slept the perfect amount");
       assertEquals(rec.sleepMessage(0.65), "Sleep more ! Your body must be well rested " +
               "for a productive day");
       assertEquals(rec.sleepMessage(0.45), "You are sleeping around the right amount of time." +
               " Keep it up!");
       assertEquals(rec.sleepMessage(0.1), "Wake up earlier! Sleeping longer " +
               "than planned can make a person lazy");
    }
    @Test void testGymMessage() {
        assertEquals(rec.gymRigourMessage(0.5), "Wow you were at the perfect gym rigour");
        assertEquals(rec.gymRigourMessage(0.65), "You are slacking! Work hard if" +
                " you want to see results");
        assertEquals(rec.gymRigourMessage(0.45), "You are almost at the" +
                " perfect gym rigour. Keep it up!");
        assertEquals(rec.gymRigourMessage(0.1), "Don't exert yourself." +
                " Tone it down a bit and you're all set!");
    }
}