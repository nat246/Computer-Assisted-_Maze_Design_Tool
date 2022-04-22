package Maze;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.event.*;

import javax.swing.*;

import javax.swing.SwingUtilities;

public class EditorUI extends JFrame {
    private String user; // Change to User Class after Database and User Class has been created

    JPanel outer;
    GridBagLayout gridLayout;
    GridBagConstraints gridBag;

    public EditorUI(String user) {
        super("Editor");
        
        this.user = user;
        initEditor();
        outerPanel();
        topBar();
        informationPanel();
        editorPanel();
    }

    private void initEditor() {
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        // setPreferredSize(new Dimension(1380, 1035)); // 16:12 Aspect Ratio
        // setMinimumSize(new Dimension(1380, 1035));

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

    // Panel that contains the information and the maze editor
    private void outerPanel() {
        outer = new JPanel();
        gridLayout = new GridBagLayout();
        gridBag = new GridBagConstraints();

        gridBag.gridwidth = 2;
        gridBag.gridheight = 1;

        outer.setLayout(gridLayout);
        
        getContentPane().add(outer);

    }

    // Displays the information about the maze (E.g. Size, Exploration Percentage, Number of Dead cells, etc.)
    private void informationPanel() {
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setMinimumSize(new Dimension(125, 175));
        infoPanel.setBorder(BorderFactory.createTitledBorder("Information"));

        JLabel gridSize = new JLabel(String.format("<html><strong>Maze Size:</strong><br> [ X: %d, Y: %d ]</html>", 100, 100)); // temp size
        JLabel cellExplore = new JLabel(String.format("<html><strong>Cell Exploration:</strong><br> %d%%</html>", 50));
        JLabel deadendNum = new JLabel(String.format("<html><strong>No. Dead Cells:</strong><br> %d</html>", 4));
        JLabel isSolvable = new JLabel(String.format("<html><strong>Solvable:</strong><br> %b</html>", true));

        // Add to Panel
        infoPanel.add(gridSize);
        infoPanel.add(cellExplore);
        infoPanel.add(deadendNum);
        infoPanel.add(isSolvable);

        outer.add(UIHandler.NewGridItem(infoPanel, gridLayout, gridBag, 0, 0, 0, 0));
        
    }

    private void editorPanel() {
        int testSize = 10;

        JPanel mazePanel = new JPanel();
        mazePanel.setMinimumSize(new Dimension(1050, 1050));
        mazePanel.setLayout(new GridLayout(testSize, testSize));
        
        for (int i = 0; i < testSize*testSize; i++) {
            JPanel createCell = newCell();
            mazePanel.add(createCell);
        }


        
        outer.add(UIHandler.NewGridItem(mazePanel, gridLayout, gridBag, 3, 0, 0, 0));
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
