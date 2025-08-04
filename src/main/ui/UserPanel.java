package ui;

import model.User;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


//Opens the user panel where the user can create a user
public class UserPanel implements ActionListener {
    JFrame frame;
    JPanel panel;
    JLabel title;
    JLabel nameLabel;
    JLabel heightLabel;
    JLabel weightLabel;
    JLabel maleLabel;
    JLabel femaleLabel;
    JTextField nameText;
    JTextField heightText;
    JTextField weightText;
    JCheckBox maleCheck;
    JCheckBox femaleCheck;
    JButton enter;
    JLabel image;

    //MODIFIES: this
    //EFFECTS: creates the frame and panel and adds the components
    public UserPanel() throws IOException {
        frame = new JFrame();
        frame.setSize(500, 300);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

        panel = new JPanel();
        frame.add(panel);
        panel.setBackground(new Color(171, 245, 171, 255));
        panel.setLayout(null);

        title = new JLabel("Enter Your Information");
        title.setBounds(180, 0, 250, 30);
        panel.add(title);

        maleCheck = new JCheckBox();
        panel.add(maleCheck);

        setLabels();

        setInputs();

        addImage();

        frame.setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: sets the name and position of all labels
    public void setLabels() {
        nameLabel = new JLabel("Name: ");
        nameLabel.setBounds(130, 50, 150, 30);
        panel.add(nameLabel);
        heightLabel = new JLabel("Height(cm):");
        heightLabel.setBounds(130, 80, 150, 30);
        panel.add(heightLabel);
        weightLabel = new JLabel("Weight(pounds):");
        weightLabel.setBounds(130, 110, 150, 30);
        panel.add(weightLabel);
        maleLabel = new JLabel("Male");
        maleLabel.setBounds(160, 140, 50, 30);
        panel.add(maleLabel);
        femaleLabel = new JLabel("Female");
        femaleLabel.setBounds(270, 140, 50, 30);
        panel.add(femaleLabel);
    }

    //MODIFIES: this
    //EFFECTS: sets the name and position of all inputs
    public void setInputs() {
        nameText = new JTextField();
        nameText.setBounds(240, 50, 150, 30);
        panel.add(nameText);
        heightText = new JTextField();
        heightText.setBounds(240, 80, 150, 30);
        panel.add(heightText);
        weightText = new JTextField();
        weightText.setBounds(240, 110, 150, 30);
        panel.add(weightText);
        maleCheck = new JCheckBox();
        maleCheck.setBounds(195, 145, 20, 20);
        panel.add(maleCheck);
        femaleCheck = new JCheckBox();
        femaleCheck.setBounds(320, 145, 20, 20);
        panel.add(femaleCheck);
        enter = new JButton("Create User");
        enter.setBounds(180, 180, 150, 30);
        panel.add(enter);
        enter.addActionListener(this);
        maleCheck.addActionListener(this);
        femaleCheck.addActionListener(this);
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
    //EFFECTS: determines what will happen when the buttons are pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (maleCheck.isSelected() && e.getSource() == maleCheck) {
            femaleCheck.setSelected(false);
        } else if (femaleCheck.isSelected() && e.getSource() == femaleCheck) {
            maleCheck.setSelected(false);
        }

        if (e.getSource() == enter) {
            if (nameText.getText() != null && heightText.getText() != null && weightText.getText() != null
                    && (maleCheck.isSelected() || femaleCheck.isSelected())) {
                User user = new User();
                user.setName(nameText.getText());
                user.setHeight(Integer.parseInt(heightText.getText()));
                user.setWeight(Double.parseDouble(weightText.getText()));
                try {
                    new GuiMaker(user);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                frame.dispose();
            }
        }
    }
}
