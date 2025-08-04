package ui;

import model.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

//Opens the main frame and panel where calories and activity can be added
public class GuiMaker implements ActionListener, WindowListener {
    JFrame frame;
    JPanel panel;
    JLabel calorieLabel;
    JLabel calorieAddedLabel;
    JLabel activityLabel;
    JLabel activityAddedLabel;
    JTextField calorieText;
    JTextField activityText;
    JButton calorieButton;
    JButton activityButton;
    JButton viewListButton;
    JButton endDayButton;
    User user;
    JLabel image;


    //MODIFIES: this
    //EFFECTS: creates the frame and panel and adds the components
    public GuiMaker(User user) throws IOException {
        this.user = user;
        panel = new JPanel();
        frame = new JFrame("Calorie and Fitness Tracker");
        frame.setSize(500,300);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(null);
        panel.setBackground(new Color(195, 248, 196, 255));

        frame.addWindowListener(this);

        initCalories();

        initActivity();

        viewListButton = new JButton("View List of Days");
        createButton(viewListButton, 80, 130, "viewListButton", 180);
        endDayButton = new JButton("End Day");
        createButton(endDayButton, 250, 130, "endDayButton", 180);
//        progress = new JProgressBar();
//        progress.setBounds(150, 200, 200, 40);
//        panel.add(progress);

        addImage();
        frame.setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: creates a button
    private void createButton(JButton button, int y, String name) {
        button.setBounds(250, y, 50, 25);
        button.setActionCommand(name);
        button.addActionListener(this);
        panel.add(button);
    }

    //MODIFIES: this
    //EFFECTS: creates a button with a movable x cooridinate
    private void createButton(JButton button,int x, int y, String name, int width) {
        button.setBounds(x, y, width, 25);
        button.setActionCommand(name);
        button.addActionListener(this);
        panel.add(button);
    }

    //MODIFIES: this
    //EFFECTS: creates a textField
    private void createText(JTextField text, int y) {
        text.setBounds(150, y, 85, 25);
        panel.add(text);
    }

    //MODIFIES: this
    //EFFECTS: creates a Label
    private void createLabel(JLabel label, int x, int y) {
        label.setBounds(x, y, 200, 25);
        panel.add(label);
    }

    //MODIFIES: this
    //EFFECTS: initializes the button, field and label for Calories
    public void initCalories() {
        calorieLabel = new JLabel("Calories Consumed:");
        createLabel(calorieLabel,10, 20);
        calorieText = new JTextField();
        createText(calorieText, 20);
        calorieButton = new JButton("Add");
        createButton(calorieButton, 20, "calorieButton");
        calorieAddedLabel = new JLabel("");
        createLabel(calorieAddedLabel,300, 20);
    }

    //MODIFIES: this
    //EFFECTS: initializes the button, field and label for Activity
    public void initActivity() {
        activityLabel = new JLabel("Distance Ran:");
        createLabel(activityLabel,10, 55);
        activityText = new JTextField();
        createText(activityText, 55);
        activityButton = new JButton("Add");
        createButton(activityButton, 55, "activityButton");
        activityAddedLabel = new JLabel("");
        createLabel(activityAddedLabel,300, 55);
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
    //EFFECTS: determines what will happen when each button is clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("calorieButton")) {
            this.calorieAddedLabel.setText("Calories Added for day " + user.currentDay().getDayNumber());
            user.addCalories(Integer.parseInt(calorieText.getText()));


        } else if (e.getActionCommand().equals("activityButton")) {
            this.activityAddedLabel.setText("Distance Added for day " + user.currentDay().getDayNumber());
            user.addActivity(Double.parseDouble(activityText.getText()));
        } else if (e.getActionCommand().equals("endDayButton")) {
            user.newDay();
            this.calorieAddedLabel.setText("");
            this.activityAddedLabel.setText("");
        } else if (e.getActionCommand().equals("viewListButton")) {
            try {
                new ListPop(user);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    //EFFECTS: Nothing(must be implemented for the interface)
    @Override
    public void windowOpened(WindowEvent e) {

    }


    //MODIFIES: this
    //EFFECTS: opens saveWindow when the close button is pressed
    @Override
    public void windowClosing(WindowEvent e) {
        new SaveWindow(user);
    }

    //EFFECTS: Nothing(must be implemented for the interface)
    @Override
    public void windowClosed(WindowEvent e) {

    }

    //EFFECTS: Nothing(must be implemented for the interface)
    @Override
    public void windowIconified(WindowEvent e) {

    }

    //EFFECTS: Nothing(must be implemented for the interface)
    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    //EFFECTS: Nothing(must be implemented for the interface)
    @Override
    public void windowActivated(WindowEvent e) {

    }

    //EFFECTS: Nothing(must be implemented for the interface)
    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
