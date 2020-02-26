package model;

import org.junit.jupiter.api.Test;
import persistence.*;

import java.io.IOException;

public class PersistenceTest {
    CalorieCounter c = new CalorieCounter();
    Goals goal = new Goals(63.0, 7, 5);
    Attributes a = new Attributes(1.8,65.0);
    StoredData s = new StoredData(c,goal,a);

    @Test
    void testReadWrite() throws IOException {
        Writer.write(s);
        Reader.read();
    }
}
