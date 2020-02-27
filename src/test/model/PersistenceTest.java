package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import persistence.*;

import java.io.IOException;

public class PersistenceTest {
    CalorieCounter c = new CalorieCounter();
    Goals goal = new Goals(63.0, 7, 5);
    Attributes a = new Attributes(1.8,65.0);
    StoredData s = new StoredData(c,goal,a);

    @Test
    void testRead() throws IOException {
       StoredData s = Reader.read();
       Goals g = s.getGoalData();
       assertEquals(5 ,g.getDesiredGymRigour());
       assertEquals(7, g.getDesiredSleep());
       assertEquals(63.0, g.getDesiredWeight());
    }
    @Test
    void testWrite() {
        boolean success = true;
        try {
            Writer.write(s);
        } catch (IOException e) {
            success = false;
        }
        assertTrue(success);
    }
}
