package ui;

import model.Day;
import model.User;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

//Opens the list panel where the user can view the list
public class ListPop implements ListSelectionListener, ActionListener {
    JFrame frame;
    JPanel leftPanel;
    JPanel rightPanel;
    JSplitPane split;
    JList list;
    JLabel rightDay;
    JLabel rightCalories;
    JLabel rightActivity;
    User user;
    JButton close;
    JButton average;
    JPanel averagePanel;
    JLabel averageLabel;
    JLabel averageLabelTwo;
    JButton averageClose;
    String dayOne = null;
    String dayTwo = null;
    Day day;

    //MODIFIES: this
    //EFFECTS: creates the frame and panel and adds the components
    public ListPop(User user) throws IOException {
        frame = new JFrame("Calorie and Fitness Tracker");
        leftPanel = new JPanel();
        rightPanel = new JPanel();
        this.user = user;

        initLabels();

        split = new JSplitPane(SwingConstants.VERTICAL, leftPanel, rightPanel);
        split.setEnabled(false);
        rightPanel.setBackground(new Color(195, 248, 196, 255));
        leftPanel.setBackground(new Color(217, 248, 217, 255));
        split.setBackground(new Color(195, 248, 196, 255));

        list = new JList(daysList(user));
        leftPanel.add(list);
        frame.setSize(500, 300);
        frame.setResizable(false);
        rightPanel.setLayout(null);
        frame.add(split);
        list.addListSelectionListener(this);
        list.setBackground(new Color(217, 248, 217, 255));

        initButtons();

        frame.setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: converts listOfDays to String[]
    public String[] daysList(User user) {
        String[] listOfDays = new String[0];
        if (user == null) {
            return listOfDays;
        }
        listOfDays = new String[user.getListOfDays().size()];
        for (Day d : user.getListOfDays()) {
            listOfDays[d.getDayNumber() - 1] = "Day: " + d.getDayNumber();
        }
        return listOfDays;
    }

    //MODIFIES: this
    //EFFECTS: initializes the buttons
    public void initButtons() {
        close = new JButton("Close");
        close.setBounds(150, 200, 100, 30);
        rightPanel.add(close);
        close.addActionListener(this);

        average = new JButton("Average");
        average.setBounds(150, 165, 100, 30);
        rightPanel.add(average);
        average.addActionListener(this);
    }

    //MODIFIES: this
    //EFFECTS: initializes the labels
    public void initLabels() {
        rightCalories = new JLabel("");
        rightCalories.setBounds(50, 50, 200, 100);
        rightPanel.add(rightCalories);

        rightActivity = new JLabel("");
        rightActivity.setBounds(50, 100, 200, 100);
        rightPanel.add(rightActivity);

        rightDay = new JLabel("");
        rightDay.setBounds(50, 0, 200, 100);
        rightPanel.add(rightDay);
    }


    //MODIFIES: this
    //EFFECTS: determines what will happen when a value in the list is selected
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (split.getRightComponent() == rightPanel) {
            String string = list.getSelectedValue().toString();
            int dayNumber = Integer.parseInt(string.replace("Day: ", ""));
            rightCalories.setText("Calories: " + user.getDay(dayNumber).getCalories() + " cal");
            rightActivity.setText("Distance Ran: " + user.getDay(dayNumber).getActivity() + " Km");
            rightDay.setText("Day " + dayNumber);
            user.logViewListEvent(dayNumber);
        } else if (split.getRightComponent() == averagePanel && dayOne == null) {
            dayOne = list.getSelectedValue().toString();
        } else if (split.getRightComponent() == averagePanel && dayOne != null) {
            setDayTwo();
        } else if (dayOne == list.getSelectedValue()) {
            //
        }
    }

    //MODIFIES: this
    //EFFECTS: sets dayTwo in accordance to dayOne and the input
    public void setDayTwo() {
        dayTwo = list.getSelectedValue().toString();
        int dayOneNumber = Integer.parseInt(dayOne.replace("Day: ", ""));
        int dayTwoNumber = Integer.parseInt(dayTwo.replace("Day: ", ""));
        if (dayOneNumber > dayTwoNumber) {
            day = user.getAverage(dayTwoNumber, dayOneNumber);
            averageLabel.setText("Average Calories: " + day.getCalories() + "cal  Average Distance ran: "
                    + day.getActivity() + "Km");
            averageLabelTwo.setText("From " + dayTwo + " to " + dayOne);
        } else {
            day = user.getAverage(dayOneNumber, dayTwoNumber);
            averageLabel.setText("Average Calories: " + day.getCalories() + "cal  Average Distance ran: "
                    + day.getActivity() + "Km");
            averageLabelTwo.setText("From " + dayOne + " to " + dayTwo);
        }
        user.removeDay(0);
    }

    //MODIFIES: this
    //EFFECTS: sets up the average panel
    public void setupAverage() {
        dayOne = null;
        dayTwo = null;
        averagePanel.setBackground(new Color(195, 248, 196, 255));
        averagePanel.setLayout(null);

        averageLabel = new JLabel("Please select the range of days you would like to calculate");
        averageLabel.setBounds(30, 50, 500, 100);
        averagePanel.add(averageLabel);

        averageLabelTwo = new JLabel();
        averageLabelTwo.setBounds(30, 30, 500, 100);
        averagePanel.add(averageLabelTwo);

        averageClose = new JButton("Close");
        averageClose.setBounds(170, 200, 70, 30);
        averagePanel.add(averageClose);
        averageClose.addActionListener(this);
    }

    //MODIFIES: this
    //EFFECTS: determines what will happen when a button is pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == close) {
            frame.dispose();
        } else if (e.getSource() == average) {
            averagePanel = new JPanel();
            setupAverage();
            split.setRightComponent(averagePanel);
        } else if (e.getSource() == averageClose) {
            split.setRightComponent(rightPanel);
        }
    }
}
