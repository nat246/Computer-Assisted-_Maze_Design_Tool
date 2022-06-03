package ui;

import maze.Maze;
import user.User;
import maze.Cell;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.Font;

import javax.swing.*;

/**
 * Class responsible for Editing the UI frames and sections
 */ 

public class EditorUI extends JFrame {
    private Maze maze;
    private User user; // Change to User Class after Database and User Class has been created

    private int mazeRowLength, mazeColLength;

    JPanel outer;

    /**
     *
     * @param user new user input for creating or accessing a maze
     * @param maze helps in creating a maze or access a randomly generated maze
     */
    public EditorUI(User user, Maze maze) {
        super("Editor");
        
        this.user = user;
        this.maze = maze;
        this.mazeRowLength = maze.getSize()[0];
        this.mazeColLength = maze.getSize()[1];
        initEditor();
        topBar();
        outerPanel();
        
    }

    /**
     * Initiate Editor window
     */
    private void initEditor() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setUndecorated(true);
        pack();
        setLocationRelativeTo(null);
    }

    /**
     * Menu Bar
     */
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

    /**
     * Panel that contains the information and the maze editor
     */
    private void outerPanel() {
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, editorPanel(), sidePanel());
        splitPane.setContinuousLayout(true);
        splitPane.setResizeWeight(0.9);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerSize(10);
        getContentPane().add(splitPane);
    }

    /**
     * Displays the information about the maze (E.g. Size, Exploration Percentage, Number of Dead cells, etc.)
     * @return sectionPanel returns the user selection from the editor UI
     */
    private JPanel sidePanel() {
        JPanel sectionPanel = new JPanel();
        sectionPanel.setLayout(new BoxLayout(sectionPanel, BoxLayout.PAGE_AXIS));
        sectionPanel.setMinimumSize(new Dimension(175, 10));

        // Information
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.PAGE_AXIS));
        
        JLabel infoTitle = new JLabel("<html><h1>Maze Information</h1></html>");
        JLabel gridSize = new JLabel(String.format("<html><strong>Maze Size:</strong> [ X: %d, Y: %d ]</html>", maze.getSize()[0], maze.getSize()[1]));
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

    /**
     * Panel where the maze is to be edited
     * @return sectionPanel takes in the user selection from the UI options
     */
    private JPanel editorPanel() {
        // Gets the largest number between the size of the grid
        int largest = Math.max(mazeColLength, mazeRowLength);

        JPanel sectionPanel = new JPanel();
        sectionPanel.setLayout(new BoxLayout(sectionPanel, BoxLayout.Y_AXIS));
        sectionPanel.setMinimumSize(new Dimension(1050, 1050));
        sectionPanel.setPreferredSize(new Dimension(1050, 1050));

        JPanel sectionInner = new JPanel();

        JPanel mazePanel = new JPanel();
        int preferredWidth = (sectionPanel.getPreferredSize().height / largest) * mazeColLength;
        int preferredHeight = (sectionPanel.getPreferredSize().width / largest) * mazeRowLength;

        mazePanel.setPreferredSize(new Dimension(preferredWidth, preferredHeight));
        mazePanel.setLayout(new GridLayout(mazeRowLength, mazeColLength));


        int rowIndex = 0, colIndex = 0;
        int totalNumCells = mazeRowLength * mazeColLength;
        // Creates the number of cells for the size of the maze
        for (int i = 0; i < totalNumCells; i++) {
            // Creates a new cell class and adds it to the maze class
            Cell cell = new Cell(rowIndex, colIndex);

            // Store new cell to maze
            maze.addCell(cell);

            // Add new cell panel to the overall maze panel
            mazePanel.add(new CellComponent(cell, maze, true).newCellPanel());

            // Checks whether the column has reached the end
            colIndex++;
            if (colIndex == mazeColLength) { colIndex = 0; rowIndex++; }
        }

        // Centre the maze
        sectionInner.add(mazePanel);
        sectionPanel.add(Box.createVerticalGlue());
        sectionPanel.add(sectionInner, BorderLayout.CENTER);
        sectionPanel.add(Box.createVerticalGlue());


        return sectionPanel;
    }
    
}
