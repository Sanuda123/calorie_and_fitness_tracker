package ui;

import model.User;
import model.Day;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import persistence.JsonReader;
import persistence.JsonWriter;



//Class that handles all the inputs and outputs
public class CalorieTracker {
    private static final String JSON_STORE = "./data/smth.json";
    boolean quit;
    User user;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    //initializes the calorieTracker
    public CalorieTracker() throws FileNotFoundException {
        quit = false;
        user = new User();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runIt();

    }

    //EFFECTS: runs the whole program
    public void runIt() {
        checkLoadUser();
        while (!quit) {
            System.out.println("It is day " + user.currentDay().getDayNumber() + "!");
            printText();
            Scanner input = new Scanner(System.in);
            String choice = input.nextLine();
            if (choice.equals("1")) {
                inputUser();
            } else if (choice.equals("2")) {
                inputCalories();
            } else if (choice.equals("3")) {
                inputActivity();
            } else if (choice.equals("4")) {
                printListOfDay();
            } else if (choice.equals("5")) {
                newDay();
            } else if (choice.equals("6")) {
                checkSaveUser();
                System.out.println(user.printLog());
                quit = true;
                System.out.println("Have a nice day!");
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: creates and stores the user info
    public void inputUser() {

        System.out.println("What is Your Name?");
        Scanner inputName = new Scanner(System.in);
        user.setName(inputName.nextLine());

        System.out.println("What is your height(cm)?");
        Scanner inputHeight = new Scanner(System.in);
        user.setHeight(Integer.valueOf(inputHeight.nextLine()));

        System.out.println("What is your weight(pounds)?");
        Scanner inputWeight = new Scanner(System.in);
        user.setWeight(Double.valueOf(inputWeight.nextLine()));

        System.out.println("Are you male(M) or female(F)?");
        Scanner inputIsMale = new Scanner(System.in);
        String gender = inputIsMale.nextLine();
        if (gender.toLowerCase().equals("m")) {
            user.setIsMale(true);
        } else {
            user.setIsMale(false);
        }
        System.out.println("Your answers have been recorded!");
    }

    //MODIFIES: this
    //EFFECTS: stores the calorie Intake done for the day, replaces if calorie intake is already present
    public void inputCalories() {
        System.out.println("What was your caloric intake for the day?");
        Scanner inputCalories = new Scanner(System.in);
        int calories = Integer.valueOf(inputCalories.nextLine());
        boolean entryExists = user.addCalories(calories);
        if (!entryExists) {
            System.out.println("Your caloric intake has been recorded for day " + user.currentDay().getDayNumber());
            System.out.println("Total caloric intake for today: " + user.currentDay().getCalories());
        } else {
            System.out.println("Your caloric intake has been added to the prior calories for day "
                    + user.currentDay().getDayNumber());
            System.out.println("Total caloric intake for today: " + user.currentDay().getCalories());
        }
    }

    //MODIFIES: this
    //EFFECTS: stores the activity done for the day, adds if activity is already present
    public void inputActivity() {
        System.out.println("What distance did you run today?(Km)");
        Scanner inputActivity = new Scanner(System.in);
        Double activity = Double.valueOf(inputActivity.nextLine());
        boolean entryExists = user.addActivity(activity);
        if (!entryExists) {
            System.out.println("Your distance travelled had been recorded for day " + user.currentDay().getDayNumber());
            System.out.println("Total distance ran today: " + user.currentDay().getActivity() + "Km");
        } else {
            System.out.println("The distance you ran was added to the previous distance for day "
                    + user.currentDay().getDayNumber());
            System.out.println("Total distance ran today: " + user.currentDay().getActivity() + "Km");
        }
    }

    //EFFECTS: Prints list of calorie intake on all days
    public void printListOfDay() {
        for (Day d : user.getListOfDays()) {
            System.out.println("Day " + d.getDayNumber() + ": " + d.getCalories() + " calories consumed and "
                    + d.getActivity() + "Km distance ran");
        }
    }

    //MODIFIES: this
    //EFFECTS: warns user if no calorie or activity has been inputted for the day, then starts new day
    public void newDay() {
        System.out.print("Are you sure you want to end the day");
        if (user.currentDay().getCalories() == 0 && user.currentDay().getActivity() == 0) {
            System.out.print("(Your calorie intake for the day will be set to 0");
            System.out.println(" and your distance ran for the day will be set to 0)");
        } else if (user.currentDay().getCalories() == 0) {
            System.out.println("(Your calorie intake for the day will be set to 0");
        } else if (user.currentDay().getActivity() == 0) {
            System.out.println("(your distance ran for the day will be set to 0)");
        }
        System.out.println("\nAnswer Yes(Y) or NO(N)");
        Scanner answer = new Scanner(System.in);
        String answerString = answer.nextLine();
        if (answerString.toLowerCase().equals("y")) {
            user.newDay();
        }
    }

    //EFFECTS: Prints the functions that the user can use
    public void printText() {
        System.out.println("What would you like to do?");
        System.out.println("1. Create new User");
        System.out.println("2. Record Calorie intake for the day");
        System.out.println("3. Record physical activity for the day");
        System.out.println("4. View calorie intake and distance ran on previous days");
        System.out.println("5. Start a new day");
        System.out.println("6. End task");
    }

    // EFFECTS: asks to save the user, saves if true
    private void checkSaveUser() {
        try {
            System.out.println("Would you like to save your user information?");
            System.out.println("\nAnswer Yes(Y) or NO(N)");
            Scanner answer = new Scanner(System.in);
            String answerString = answer.nextLine();
            if (answerString.toLowerCase().equals("y")) {
                jsonWriter.open();
                jsonWriter.write(user);
                jsonWriter.close();
                System.out.println("Saved " + user.getName() + " to " + JSON_STORE);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    //MODIFIES: this
    //EFFECTS: asks to load the user, loads if input is true
    public void checkLoadUser() {
        try {
            System.out.println("Would you like to load your previous user information?");
            System.out.println("\nAnswer Yes(Y) or NO(N)");
            Scanner answer = new Scanner(System.in);
            String answerString = answer.nextLine();
            if (answerString.toLowerCase().equals("y")) {
                user = jsonReader.read();
                System.out.println("Loaded " + user.getName() + " from " + JSON_STORE);
            }
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
