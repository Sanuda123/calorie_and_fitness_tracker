package persistence;

import model.Day;

import static org.junit.jupiter.api.Assertions.assertEquals;
    // The following tests were made by referring to the cited source:
    // checkDay()
public class JsonTest {
    protected void checkDay(Day day, int dayNumber, int calories, Double activity) {
        assertEquals(dayNumber, day.getDayNumber());
        assertEquals(calories, day.getCalories());
        assertEquals(activity, day.getActivity());
    }
}
