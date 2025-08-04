package persistence;

import model.User;
import model.Day;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

    // The following tests were made by referring to the cited source:
    // testReaderNonExistentFile(), testReaderEmptyUser(), testReaderGeneralWorkroom()
class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/nonExistentFile.json");
        try {
            User user = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyUser() {
        JsonReader reader = new JsonReader("./data/testEmptyUser.json");
        try {
            User user = reader.read();
            assertEquals(1, user.getListOfDays().size());
            assertEquals(0, user.getHeight());
            assertEquals(0.0, user.getWeight());
            assertEquals(false, user.isMale());
            assertEquals("", user.getName());
            checkDay(user.getListOfDays().get(0), 1, 0, 0.0);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testUser.json");
        try {
            User user = reader.read();
            assertEquals("Sanuda", user.getName());
            List<Day> days = user.getListOfDays();
            assertEquals(2, days.size());
            checkDay(days.get(0), 1, 1000,  10.0);
            checkDay(days.get(1), 2, 2000, 15.0);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}

