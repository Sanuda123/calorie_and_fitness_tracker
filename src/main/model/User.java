package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.text.DecimalFormat;
import java.util.*;

    // Class containing all the User information including dayNumber and lists
    // The following methods were made by referring to the cited source:
    // toJson(), daysToJson()
public class User implements Writable {
    private int height;
    private double weight;
    private boolean isMale;
    private String name;
    private ArrayList<Day> days;
    private EventLog eventLog;



    //MODIFIES: this
    //EFFECTS: creates a user
    public User() {
        days = new ArrayList<>();
        name = "";
        newDay();
        this.eventLog = EventLog.getInstance();
    }

    //MODIFIES: this
    //EFFECTS: creates a user with pre-existing stats
    public User(String name, int height, Double weight, boolean isMale) {
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.isMale = isMale;
        this.days = new ArrayList<>();
        this.eventLog = EventLog.getInstance();
    }

    //EFFECTS: returns height
    public int getHeight() {
        return height;
    }

    //EFFECTS: returns weight
    public double getWeight() {
        return weight;
    }

    //EFFECTS: returns name
    public String getName() {
        return name;
    }

    //EFFECTS: returns true is user is male
    public boolean isMale() {
        return isMale;
    }

    //EFFECTS: returns the list of days
    public ArrayList<Day> getListOfDays() {
        return days;
    }

    //MODIFIES: this
    //EFFECTS: sets the height of user
    public void setHeight(int height) {
        this.height = height;
        eventLog.logEvent(new Event("Height set to " + height + " cm"));
    }

    //MODIFIES: this
    //EFFECTS: sets the gender of user, true if male, false if female
    public void setIsMale(boolean male) {
        isMale = male;
        String gender;
        if (male) {
            gender = "Male";
        } else {
            gender = "Female";
        }
        eventLog.logEvent(new Event("Gender set to " + gender));
    }

    //MODIFIES: this
    //EFFECTS: sets the name of user
    public void setName(String name) {
        this.name = name;
        eventLog.logEvent(new Event("Name set to " + name));
    }

    //EFFECTS: sets the weight of the user
    public void setWeight(double weight) {
        this.weight = weight;
        eventLog.logEvent(new Event("Weight set to " + weight + " pounds"));
    }

    //MODIFIES: this
    //EFFECTS: creates a new day with dayNumber++ the last element
    public void newDay() {
        days.add(new Day(days.size() + 1));
    }

    //MODIFIES: this
    //EFFECTS: adds calories, true if, calories existed before, false if calories was 0
    public boolean addCalories(int calories) {
        eventLog.logEvent(new Event(calories + " Calories added on day "
                + days.get(days.size() - 1).getDayNumber()));
        return days.get(days.size() - 1).addCalories(calories);
    }

    //MODIFIES: this
    //EFFECTS: adds activity, true if, activity existed before, false if activity was 0

    public boolean addActivity(Double activity) {
        eventLog.logEvent(new Event(activity + " Km added on day "
                + days.get(days.size() - 1).getDayNumber()));
        return days.get(days.size() - 1).addActivity(activity);
    }

    //EFFECTS: returns the day last added to Days
    public Day currentDay() {
        return days.get(days.size() - 1);
    }

    //EFFECTS: adds a day with all values to the listOfDays(used in json)
    public void addDay(Day day) {
        days.add(day);
    }

    //EFFECTS: returns the day of given dayNumber
    public Day getDay(int dayNumber) {
        for (Day d : days) {
            if (d.getDayNumber() == dayNumber) {
                return d;
            }
        }
        return null;
    }

    //REQUIRES: start < end
    //MODIFIES: this
    //EFFECTS: creates a new day with the average calories and distance ran for the given range
    public Day getAverage(int start, int end) {
        int averageCalories = 0;
        Double averageActivity = 0.0;

        for (int i = start; i <= end; i++) {
            Day d = getDay(i);
            averageCalories += d.getCalories();
            averageActivity += d.getActivity();
        }
        averageCalories = averageCalories / (end - start + 1);
        averageActivity = averageActivity / (end - start + 1);
        DecimalFormat format = new DecimalFormat("#.00");
        averageActivity = Double.parseDouble(format.format(averageActivity).toString());
        eventLog.logEvent(new Event("Average calories and activity calculated from day " + start + " to day "
                + end));
        return new Day(0, averageCalories, averageActivity);
    }

    //MODIFIES: this
    //EFFECTS: removes day of given DayNumber
    public void removeDay(int dayNumber) {
        days.remove(getDay(dayNumber));
    }


    //EFFECTS: Associates each field with a keyword, making the object ready to be stored
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("height", height);
        json.put("weight", weight);
        json.put("isMale", isMale);
        json.put("days", daysToJson());
        return json;
    }

    // EFFECTS: returns days in the user as a JSON array
    public JSONArray daysToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Day d : days) {
            jsonArray.put(d.toJson());
        }
        return jsonArray;
    }

    //EFFECTS: returns a sting of all events and their respective times
    public String printLog() {
        String log = "";
        for (Iterator<Event> it = eventLog.iterator(); it.hasNext(); ) {
            Event e = it.next();
            log += e.toString() + "\n";
        }
        return log;
    }

    public void logViewListEvent(int dayNumber) {
        eventLog.logEvent(new Event("Viewing information from day " + dayNumber));
    }

}

