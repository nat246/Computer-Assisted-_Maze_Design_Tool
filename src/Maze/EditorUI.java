package Maze;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.*;
import java.awt.Font;

import javax.swing.*;

public class EditorUI extends JFrame {
    private String user; // Change to User Class after Database and User Class has been created

    JPanel outer;

    public EditorUI(String user) {
        super("Editor");
        
        this.user = user;
        initEditor();
        topBar();
        outerPanel();
        
    }

    // Initiate Editor window
    private void initEditor() {
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setUndecorated(true);

        pack();

        setLocationRelativeTo(null);
    }

    // Menu Bar 
    private void topBar() {
        JMenuBar bar = new JMenuBar();

        // File menu
        JMenu fileMenu = new JMenu("File");

        JMenuItem exitB = new JMenuItem("Exit to menu");

        // Events
        exitB.addActionListener(e -> this.dispose());

        // Add to Menu Bar
        fileMenu.add("New Maze");
        fileMenu.add(new JSeparator());
        fileMenu.add("Save");
        fileMenu.add("Save as...");
        fileMenu.add(new JSeparator());
        fileMenu.add("Export as PNG");
        fileMenu.add(new JSeparator());
        fileMenu.add(exitB);

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

    // Panel that contains the information and the maze editor
    private void outerPanel() {
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, editorPanel(), sidePanel());
        splitPane.setContinuousLayout(true);
        splitPane.setResizeWeight(0.9);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerSize(10);
        getContentPane().add(splitPane);
    }

    // Displays the information about the maze (E.g. Size, Exploration Percentage, Number of Dead cells, etc.)
    private JPanel sidePanel() {
        JPanel sectionPanel = new JPanel();
        sectionPanel.setLayout(new BoxLayout(sectionPanel, BoxLayout.PAGE_AXIS));
        sectionPanel.setMinimumSize(new Dimension(175, 10));

        // Information
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.PAGE_AXIS));
        
        JLabel infoTitle = new JLabel("<html><h1>Maze Information</h1></html>");
        JLabel gridSize = new JLabel(String.format("<html><strong>Maze Size:</strong> [ X: %d, Y: %d ]</html>", 10, 10)); // temp size
        JLabel cellExplore = new JLabel(String.format("<html><strong>Cell Exploration:</strong> %d%%</html>", 50));
        JLabel deadendNum = new JLabel(String.format("<html><strong>No. Dead Cells:</strong> %d</html>", 4));
        JLabel isSolvable = new JLabel(String.format("<html><strong>Solvable:</strong> %b</html>", true));

        // Options
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.PAGE_AXIS));

        JLabel optionsTitle = new JLabel("<html><br><h1>Options</h1></html>");
        optionsTitle.setFont(new Font("SanSerif", Font.PLAIN, 20));

        JCheckBox solutionCheckBox = new JCheckBox("Show Solution");

        // Add to Panel
        infoPanel.add(infoTitle);
        infoPanel.add(gridSize);
        infoPanel.add(cellExplore);
        infoPanel.add(deadendNum);
        infoPanel.add(isSolvable);

        optionsPanel.add(optionsTitle);
        optionsPanel.add(solutionCheckBox);

        sectionPanel.add(infoPanel);
        sectionPanel.add(optionsPanel);

        return sectionPanel;
    }

    // Panel where the maze is to be edited
    private JPanel editorPanel() {
        int testSize = 10;

        JPanel sectionPanel = new JPanel();

        JPanel mazePanel = new JPanel();
        mazePanel.setMinimumSize(new Dimension(1050, 1050));
        mazePanel.setLayout(new GridLayout(testSize, testSize));
        
        for (int i = 0; i < testSize*testSize; i++) {
            JPanel createCell = newCell();
            mazePanel.add(createCell);
        }

        sectionPanel.add(mazePanel);

        return sectionPanel;
    }

    // Method for each cell in the maze
    private JPanel newCell() {
        JPanel cellPanel = new JPanel();
        cellPanel.setLayout(new BorderLayout());
        cellPanel.setPreferredSize(new Dimension(100, 100));

        // Create Walls
        SwingUtilities.invokeLater(() -> {
            // TOP
            cellPanel.add(UIHandler.CreateWall(new Dimension(cellPanel.getWidth(), cellPanel.getHeight() / 10)), BorderLayout.NORTH);

            // BOTTOM
            cellPanel.add(UIHandler.CreateWall(new Dimension(cellPanel.getWidth(), cellPanel.getHeight() / 10)), BorderLayout.SOUTH);

            // LEFT
            cellPanel.add(UIHandler.CreateWall(new Dimension(cellPanel.getWidth() / 10, cellPanel.getHeight())), BorderLayout.LINE_START);

            // RIGHT
            cellPanel.add(UIHandler.CreateWall(new Dimension(cellPanel.getWidth() / 10, cellPanel.getHeight())), BorderLayout.LINE_END);
        });

        return cellPanel;
    }

    
}
