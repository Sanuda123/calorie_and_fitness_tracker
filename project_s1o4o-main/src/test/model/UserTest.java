package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    private User user;



    @BeforeEach
    void runBefore() {
        user = new User();
    }

    @Test
    void testUser() {
        assertEquals(1, user.getListOfDays().size());
        assertEquals(1, user.currentDay().getDayNumber());
        assertEquals(0, user.currentDay().getCalories());
        assertEquals(0.0, user.currentDay().getActivity());
    }

    @Test
    void testSetHeight() {
        user.setHeight(150);
        assertEquals(150, user.getHeight());
    }

    @Test
    void testSetIsMale() {
        user.setIsMale(true);
        assertTrue(user.isMale());
    }

    @Test
    void testSetName() {
        user.setName("SAN");
        assertEquals("SAN", user.getName());
    }

    @Test
    void testSetWeight() {
        user.setWeight(160);
        assertEquals(160, user.getWeight());
    }


    @Test
    void testCurrentDay() {
        assertEquals(1, user.currentDay().getDayNumber());
        user.newDay();
        assertEquals(2, user.currentDay().getDayNumber());

    }

    @Test
    void testAddCalories() {
        assertFalse(user.addCalories(50));
        assertEquals(50, user.currentDay().getCalories());
        assertTrue(user.addCalories(100));
        assertEquals(150, user.currentDay().getCalories());
        user.newDay();
        assertEquals(0, user.currentDay().getCalories());
    }

    @Test
    void testAddActivity() {
        assertFalse(user.addActivity(5.5));
        assertEquals(5.5, user.currentDay().getActivity());
        assertTrue(user.addActivity(6.0));
        assertEquals(11.5, user.currentDay().getActivity());
        user.newDay();
        assertEquals(0.0, user.currentDay().getCalories());
    }

    @Test
    void testNewDay() {
        assertEquals(1, user.currentDay().getDayNumber());
        user.newDay();
        assertEquals(2, user.currentDay().getDayNumber());
    }

    @Test
    void testAddDay() {
        user.addDay(new Day(2, 1000, 10.0));
        Day day = user.getListOfDays().get(1);
        assertEquals(2, day.getDayNumber());
        assertEquals(1000, day.getCalories());
        assertEquals(10.0, day.getActivity());
    }

    @Test
    void testGetDay() {
        user.currentDay().addCalories(10);
        user.currentDay().addActivity(1.1);
        user.addDay(new Day(2, 20, 2.1));
        user.addDay(new Day(3, 30, 3.1));
        assertEquals(10, user.getDay(1).getCalories());
        assertEquals(1.1, user.getDay(1).getActivity());
        assertEquals(1, user.getDay(1).getDayNumber());


        assertEquals(20, user.getDay(2).getCalories());
        assertEquals(2.1, user.getDay(2).getActivity());
        assertEquals(2, user.getDay(2).getDayNumber());

        assertEquals(30, user.getDay(3).getCalories());
        assertEquals(3.1, user.getDay(3).getActivity());
        assertEquals(3, user.getDay(3).getDayNumber());
        assertEquals(null, user.getDay(10));
    }

    @Test
    void testRemoveDay() {
        Day dayOne = new Day(3, 100, 10.0);
        user.addDay(dayOne);
        assertTrue(user.getListOfDays().contains(dayOne));
        user.removeDay(2);
        assertTrue(user.getListOfDays().contains(dayOne));
        user.removeDay(3);
        assertFalse(user.getListOfDays().contains(dayOne));
    }

    @Test
    void testGetAverage() {
        user.addCalories(1000);
        user.addActivity(10.0);
        user.addDay(new Day(2, 2000, 20.0));
        user.addDay(new Day(3,3000, 13.0));
        Day averageOne = user.getAverage(1,3);
        assertEquals(0, averageOne.getDayNumber());
        assertEquals(2000, averageOne.getCalories());
        assertEquals(14.33, averageOne.getActivity());
        Day averageTwo = user.getAverage(2,3);
        assertEquals(0, averageTwo.getDayNumber());
        assertEquals(2500, averageTwo.getCalories());
        assertEquals(16.50, averageTwo.getActivity());

    }

    @Test
    void testToJson() {
        JSONObject jsonObject = user.toJson();
        String name = jsonObject.getString("name");
        int height = jsonObject.getInt("height");
        Double weight = jsonObject.getDouble("weight");
        boolean isMale = jsonObject.getBoolean("isMale");
        assertEquals("", name);
        assertEquals(0, height);
        assertEquals(0.0, weight);
        assertEquals(false, isMale);
        List<Object> jsonArray = jsonObject.getJSONArray("days").toList();
        assertEquals(1, jsonArray.size());
    }

    @Test
    void testDaysToJson() {
        user.addDay(new Day(2, 2000, 15.0));
        user.addDay(new Day(3, 1500, 5.5));
        JSONArray jsonArray = user.daysToJson();
        assertTrue( jsonArray.toString().contains("{\"activity\":0,\"dayNumber\":1,\"calories\":0}"));
        assertTrue(jsonArray.toString().contains("{\"activity\":15,\"dayNumber\":2,\"calories\":2000}"));
        assertTrue(jsonArray.toString().contains("{\"activity\":5.5,\"dayNumber\":3,\"calories\":1500}"));
    }

}
