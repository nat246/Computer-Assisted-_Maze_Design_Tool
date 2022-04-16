package Maze;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.*;

public class MazeUI extends javax.swing.JFrame {

    public MazeUI() {
        super("Maze Creator");
        initGUI();
        MenuComponents();
    }

    private void initGUI() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(420, 200));
        pack();

        setLocationRelativeTo(null);
    }

    private void MenuComponents() {
        // Creates a new Panel to fit all Card Components
        CardLayout card = new CardLayout();
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(card);

        menuPanel.add(MainMenu(), "main");
        menuPanel.add(CreateUserMenu(), "userP");
        menuPanel.add(CreateMazeMenu(), "mazeP");

        
        card.show(menuPanel, "main");


        getContentPane().add(menuPanel);
    }

    // The Main Menu where the User is to Select User
    private JPanel MainMenu() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.add(Box.createVerticalGlue());

        // Title
        JLabel title = new JLabel("Maze Creator");
        title.setFont(new Font("Monospaced", Font.PLAIN, 25));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Select User
        JComboBox<String> selectUser = new JComboBox<>(new String[] {" Select User...", " (Create New User)"} );
        selectUser.setMaximumSize(new Dimension(240, 50));

        // Continue Button
        JButton contButton = new JButton("Continue");
        contButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Add to Panel
        mainPanel.add(title);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        mainPanel.add(selectUser);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        mainPanel.add(contButton);
        mainPanel.add(Box.createVerticalGlue());

        return mainPanel;
    }

    // The menu to create a new user.
    private JPanel CreateUserMenu() {
        JPanel newUserPanel = new JPanel();

        return newUserPanel;
    }

    // The menu for importing a saved maze or create a new maze.
    private JPanel CreateMazeMenu() {
        JPanel newMenuPanel = new JPanel();

        return newMenuPanel;
    }
}
