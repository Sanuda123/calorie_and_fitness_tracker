package ui;

import model.User;
import java.io.FileNotFoundException;
import java.io.IOException;

//Runs the Gui interface
public class GUI {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        new LoadWindow();
    }
}