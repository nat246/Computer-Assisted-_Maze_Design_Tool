package Maze;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.*;
import java.awt.Font;

import javax.swing.*;

/**
 * Class responsible for Editing the UI frames and sections
 */ 

public class EditorUI extends JFrame {
    private Maze maze;
    private User user; // Change to User Class after Database and User Class has been created

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
        initEditor();
        topBar();
        outerPanel();
        
    }

    /**
     *
     */

    // Initiate Editor window
    private void initEditor() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setUndecorated(true);
        pack();
        setLocationRelativeTo(null);
    }

    /**
     * Method used for editing the top section of the UI
     */
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

    /**
     * Method used for editing the outer panel of the UI
     */

    // Panel that contains the information and the maze editor
    private void outerPanel() {
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, editorPanel(maze.getSize()[0], maze.getSize()[1]), sidePanel());
        splitPane.setContinuousLayout(true);
        splitPane.setResizeWeight(0.9);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerSize(10);
        getContentPane().add(splitPane);
    }

    /**
     *
     * @return sectionPanel returns the user selection from the editor UI
     */

    // Displays the information about the maze (E.g. Size, Exploration Percentage, Number of Dead cells, etc.)
    private JPanel sidePanel() {
        JPanel sectionPanel = new JPanel();
        sectionPanel.setLayout(new BoxLayout(sectionPanel, BoxLayout.PAGE_AXIS));
        sectionPanel.setMinimumSize(new Dimension(175, 10));

        // Information
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.PAGE_AXIS));
        
        JLabel infoTitle = new JLabel("<html><h1>Maze Information</h1></html>");
        JLabel gridSize = new JLabel(String.format("<html><strong>Maze Size:</strong> [ X: %d, Y: %d ]</html>", maze.getSize()[0], maze.getSize()[1])); // temp size
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
     *
     * @param w width dimension of the grid
     * @param h height dimension of the grid
     * @return sectionPanel takes in the user selection from the UI options
     */

    // Panel where the maze is to be edited
    private JPanel editorPanel(int w, int h) {
        // Gets the largest number between the size of the grid
        int largest = (h >= w) ? h : w;
        int x = 0, y = 0;

        JPanel sectionPanel = new JPanel();
        sectionPanel.setLayout(new BoxLayout(sectionPanel, BoxLayout.Y_AXIS));
        sectionPanel.setMinimumSize(new Dimension(1050, 1050));
        sectionPanel.setPreferredSize(new Dimension(1050, 1050));

        JPanel sectionInner = new JPanel();

        JPanel mazePanel = new JPanel();
        mazePanel.setPreferredSize(new Dimension((sectionPanel.getPreferredSize().width / largest) * w, (sectionPanel.getPreferredSize().height / largest) * h));
        mazePanel.setLayout(new GridLayout(h, w));
        
        // Creates the number of cells for the size of the maze
        for (int i = 0; i < w * h; i++) {
            // Creates a new cell class and adds it to the maze class
            Cell cell = new Cell(x, y);
            maze.addCell(cell);

            JPanel createCell = newCell(cell);
            mazePanel.add(createCell);
            
            // Checks whether the column has reached the end
            y++;
            if (y == h) { y = 0; x++; }
        }

        // Centre the maze
        sectionInner.add(mazePanel);
        sectionPanel.add(Box.createVerticalGlue());
        sectionPanel.add(sectionInner, BorderLayout.CENTER);
        sectionPanel.add(Box.createVerticalGlue());


        return sectionPanel;
    }

    /**
     *
     * @param cell object takes in the dimensions for creating a new cell in a maze
     * @return cellPanel dimensions for creating a new cell
     */


    // Method for each cell in the maze
    private JPanel newCell(Cell cell) {
        JPanel cellPanel = new JPanel();
        cellPanel.setLayout(new BorderLayout());
        
        // Create Walls
        SwingUtilities.invokeLater(() -> {
            // TOP
            cellPanel.add(createWall(cell, new Dimension(cellPanel.getWidth(), cellPanel.getHeight() / 10), "top"), BorderLayout.PAGE_START);

            // BOTTOM
            cellPanel.add(createWall(cell, new Dimension(cellPanel.getWidth(), cellPanel.getHeight() / 10), "bottom"), BorderLayout.PAGE_END);

            // LEFT
            cellPanel.add(createWall(cell, new Dimension(cellPanel.getWidth() / 10, cellPanel.getHeight()), "left"), BorderLayout.LINE_START);

            // RIGHT
            cellPanel.add(createWall(cell, new Dimension(cellPanel.getWidth() / 10, cellPanel.getHeight()), "right"), BorderLayout.LINE_END);
            
        });

        return cellPanel;
    }

    /**
     *
     * @param cell create a new cell
     * @param size input size of the new wall
     * @param location Location of the new wall to be placed
     * @return newWall according to the user inputs
     */


    // Creates a singular wall
    private JPanel createWall(Cell cell, Dimension size, String location) {
        JPanel newWall = new JPanel();
        newWall.setPreferredSize(size);
        newWall.setBackground(Color.BLACK);


        newWall.addMouseListener(new MouseAdapter() {
            Color currentColour = Color.BLACK;


            @Override
            public void mouseEntered(MouseEvent e) {
                newWall.setBackground(Color.ORANGE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                newWall.setBackground(currentColour);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (cell.wallStatus(location)) newWall.setOpaque(true);
                else newWall.setOpaque(false);;
                cell.setWall(location, !cell.wallStatus(location));
                System.out.println(cell.wallStatus(location));
            }
            
        });
        return newWall;
    }

    
}
