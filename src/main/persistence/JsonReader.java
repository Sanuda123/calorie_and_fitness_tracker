package persistence;

import model.Day;
import model.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;


import java.util.stream.Stream;

    // Represents a reader that reads the user from JSON data stored in file
    //The following methods were made by referring to the cited source:
    // JsonReader(), read(), readFile(), parseUser(), addDays(), addDay()
public class JsonReader {
    private String source;

    //MODIFIES: this
    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads the user from file and returns it;
    // throws IOException if an error occurs reading data from file
    public User read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseUser(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses user from JSON object and returns it
    private User parseUser(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int height = jsonObject.getInt("height");
        Double weight = jsonObject.getDouble("weight");
        boolean isMale = jsonObject.getBoolean("isMale");

        User user = new User(name, height, weight, isMale);
        addDays(user, jsonObject);
        return user;
    }

    // MODIFIES: user
    // EFFECTS: parses days from JSON object and adds them to the user
    private void addDays(User user, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("days");
        for (Object json : jsonArray) {
            JSONObject nextDay = (JSONObject) json;
            addDay(user, nextDay);
        }
    }

    // MODIFIES: user
    // EFFECTS: parses day from JSON object and adds it to the user
    private void addDay(User user, JSONObject jsonObject) {
        int dayNumber = jsonObject.getInt("dayNumber");
        int calories = jsonObject.getInt("calories");
        Double activity = jsonObject.getDouble("activity");
        Day d = new Day(dayNumber, calories, activity);
        user.addDay(d);
    }
}
