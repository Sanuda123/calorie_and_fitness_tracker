package ui;

import model.User;
import persistence.JsonReader;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

//Opens the load user frame and Panel
public class LoadWindow implements ActionListener {
    User user;
    JFrame frame;
    JButton loadUser;
    JButton newUser;
    JLabel label;
    JPanel panel;
    private static final String JSON_STORE = "./data/smth.json";
    private JsonReader jsonReader;
    JLabel image;

    //MODIFIES: this
    //EFFECTS creates the frame and panel and adds all other features
    public LoadWindow() throws IOException {
        jsonReader = new JsonReader(JSON_STORE);
        frame = new JFrame("Calorie and Fitness Tracker");
        frame.setSize(500, 300);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);
        panel.setBackground(new Color(195, 248, 196, 255));

        label = new JLabel("Calorie And Fitness Tracker");
        label.setBounds(150, 50, 200, 50);
        panel.add(label);

        initButtons();

        addImage();

        frame.setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: determines what happens when each button is clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "loadUser") {
            try {
                user = jsonReader.read();
                new GuiMaker(user);
                frame.setVisible(false);

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getActionCommand() == "newUser") {
            try {
                new UserPanel();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: adds an image to the background
    public void addImage() throws IOException {
        BufferedImage imageIcon = ImageIO.read(new File("./data/FinalPic.jpg"));
        image = new JLabel(new ImageIcon(imageIcon));
        image.setBounds(80,-15, 30, 30);
        image.setSize(300, 300);
        panel.add(image);
    }

    //MODIFIES: this
    //EFFECTS: initializes the two buttons
    public void initButtons() {
        loadUser = new JButton("Load User");
        loadUser.setBounds(100, 100, 100, 50);
        loadUser.addActionListener(this);
        loadUser.setActionCommand("loadUser");
        panel.add(loadUser);


        newUser = new JButton("New User");
        newUser.setBounds(250, 100, 100, 50);
        newUser.addActionListener(this);
        newUser.setActionCommand("newUser");
        panel.add(newUser);
    }

}
