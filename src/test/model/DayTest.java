package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class DayTest {
    private Day day;
    private Day dayTwo;

    @BeforeEach
    void runBefore() {
        day = new Day(5);
    }

    @Test
    void testDay() {
        assertEquals(5,day.getDayNumber());
        assertEquals(0, day.getCalories());
        assertEquals(0.0, day.getActivity());
    }

    @Test
    void testDayTwo() {
        dayTwo = new Day(1, 1000, 10.0);
        assertEquals(1, dayTwo.getDayNumber());
        assertEquals(1000, dayTwo.getCalories());
        assertEquals(10.0, dayTwo.getActivity());
    }

    @Test
    void testAddCalories() {
        assertFalse(day.addCalories(50));
        assertEquals(50, day.getCalories());
        assertTrue(day.addCalories(100));
        assertEquals(150, day.getCalories());
    }

    @Test
    void testAddActivity()  {
        assertFalse(day.addActivity(5.0));
        assertEquals(5.0, day.getActivity());
        assertTrue(day.addActivity(10.0));
        assertEquals(15.0, day.getActivity());
    }

    @Test
    void testToJson() {
        Day dayTwo = new Day(1, 1000, 10.0);
        JSONObject jsonObject = dayTwo.toJson();
        int dayNumber = jsonObject.getInt("dayNumber");
        int calories = jsonObject.getInt("calories");
        Double activity = jsonObject.getDouble("activity");
        assertEquals(1, dayNumber);
        assertEquals(1000, calories);
        assertEquals(10.0, activity);
    }
}

