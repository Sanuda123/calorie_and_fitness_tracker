package ui;

import model.User;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

//Opens the save user panel where the user can save
public class SaveWindow extends JFrame implements ActionListener {
    User user;
    JButton save;
    JButton noSave;
    JLabel label;
    private static final String JSON_STORE = "./data/smth.json";
    private JsonWriter jsonWriter;



    //MODIFIES: this
    //EFFECTS: creates the frame and panel and adds the components
    public SaveWindow(User user) {
        setTitle("Calorie and Fitness Tracker");
        jsonWriter = new JsonWriter(JSON_STORE);
        setSize(500,300);
        this.user = user;
        setResizable(false);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setBackground(new Color(171, 245, 171, 255));
        save = new JButton("Save");
        save.setBounds(250, 125,100,30);
        add(save);
        save.addActionListener(this);
        noSave = new JButton("Don't Save");
        noSave.setBounds(150, 125, 100, 30);
        add(noSave);
        noSave.addActionListener(this);
        label = new JLabel("Would you like to Save?");
        label.setBounds(175, 75, 200, 30);
        add(label);

        setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: uses JSON to write the user information
    public void saveUser() {
        try {
            jsonWriter.open();
            jsonWriter.write(user);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            setBackground(Color.RED);
        }
    }

    //MODIFIES: this
    //EFFECTS: determines what will happen when the two buttons are pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == save) {
            saveUser();
        }
        System.out.println(user.printLog());
        System.exit(1);
    }
}
