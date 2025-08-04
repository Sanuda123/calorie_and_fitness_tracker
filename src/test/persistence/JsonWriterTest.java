package persistence;
import model.Day;
import model.User;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

    // The following tests were made by referring to the cited source:
    //testWriterInvalidFile(), testWriterEmptyWorkroom(), testWriteGeneralWorkroom()
class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            User user = new User();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            User user = new User();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyUser.json");
            writer.open();
            writer.write(user);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyUser.json");
            user = reader.read();
            assertEquals("", user.getName());
            assertEquals(1, user.getListOfDays().size());
            checkDay(user.getListOfDays().get(0), 1, 0, 0.0);
            assertEquals(0, user.getHeight());
            assertEquals(0.0, user.getWeight());
            assertEquals(false, user.isMale());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            User user = new User("San", 160, 160.0, true);
            user.addDay(new Day(1, 1000, 10.0));
            user.addDay(new Day(2, 2000, 15.0));
            JsonWriter writer = new JsonWriter("./data/testWriterUser.json");
            writer.open();
            writer.write(user);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterUser.json");
            user = reader.read();
            assertEquals("San", user.getName());
            List<Day> days= user.getListOfDays();
            assertEquals(2, days.size());
            checkDay(days.get(0), 1, 1000, 10.0);
            checkDay(days.get(1), 2, 2000, 15.0);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}