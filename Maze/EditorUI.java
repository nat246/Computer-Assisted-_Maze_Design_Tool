package Maze;

import java.awt.Dimension;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.*;

import javax.swing.*;

public class EditorUI extends JFrame {
    private String user; // Change to User Class after Database and User Class has been created

    JPanel outer;
    GridBagLayout gridLayout;

    public EditorUI(String user) {
        super("Editor");
        
        this.user = user;
        initEditor();
        outerPanel();
        topBar();
    }

    private void initEditor() {
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setPreferredSize(new Dimension(1000, 750)); // 16:12 Aspect Ratio
        setMinimumSize(new Dimension(500, 375));
        pack();

        setLocationRelativeTo(null);
    }

    private void topBar() {
        JMenuBar bar = new JMenuBar();

        // File menu
        JMenu fileMenu = new JMenu("File");

        fileMenu.add("New Maze");
        fileMenu.add("Open Maze...");
        fileMenu.add(new JSeparator());
        fileMenu.add("Save");
        fileMenu.add("Save as...");
        fileMenu.add(new JSeparator());
        fileMenu.add("Export as PNG");
        fileMenu.add(new JSeparator());
        fileMenu.add("Exit");

        // Edit menu
        JMenu editMenu = new JMenu("Edit");

        editMenu.add("Add Image");
        editMenu.add("Set Logo Image");

        // Test menu
        JMenu testMenu = new JMenu("Test");
        testMenu.add("Test solvable");

        // Add to menu bar
        bar.add(fileMenu);
        bar.add(editMenu);
        bar.add(testMenu);
        setJMenuBar(bar);
    }

    private void outerPanel() {
        outer = new JPanel();
        gridLayout = new GridBagLayout();

        outer.setLayout(gridLayout);
        
    }

    

    
    
}
