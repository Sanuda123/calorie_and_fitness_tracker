package model;

import org.json.JSONObject;
import persistence.Writable;

    // Represents the information stored within each day
    // The following methods were made by referring to the cited source:
    // toJson()
public class Day implements Writable {
    private final int dayNumber;
    private int calories;
    private Double activity;

    //MODIFIES: this
    //EFFECTS: initializes day with a day number and zero for other fields
    public Day(int dayNumber) {
        this.dayNumber = dayNumber;
        calories = 0;
        activity = 0.0;
    }

    //MODIFIES: this
    //EFFECTS: constructs pre-existing day with all fields
    public Day(int dayNumber, int calories, Double activity) {
        this.dayNumber = dayNumber;
        this.calories = calories;
        this.activity = activity;
    }

    //EFFECTS: returns the day number
    public int getDayNumber() {
        return dayNumber;
    }

    //EFFECTS: returns the calories consumed for the day so far
    public int getCalories() {
        return calories;
    }


    //EFFECTS: returns the activity done for the day
    public Double getActivity() {
        return activity;
    }

    //MODIFIES: this
    //EFFECTS: inputs calories for the current day, return true if value already exists
    public boolean addCalories(int calories) {
        if (this.calories == 0) {
            this.calories = calories;
            return false;
        } else {
            this.calories = this.calories + calories;
            return true;
        }
    }

    //MODIFIES: this
    //EFFECTS: inputs activity for the current day, adds if value already exists
    public boolean addActivity(Double activity) {
        if (this.activity == 0.0) {
            this.activity = activity;
            return false;
        } else {
            this.activity = this.activity + activity;
            return true;
        }
    }


    //EFFECTS: Associate each field with a keyword, making the object ready to be stored
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("dayNumber", dayNumber);
        json.put("calories", calories);
        json.put("activity", activity);
        return json;
    }
}
