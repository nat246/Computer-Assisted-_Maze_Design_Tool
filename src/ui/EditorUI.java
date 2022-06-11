package ui;

import maze.Maze;
import maze.MazeRandomCreator;
import maze.datamanager.MazeDataHandler;
import user.User;
import maze.Cell;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**
 * Class responsible for Editing the UI frames and sections
 */ 

public class EditorUI extends JFrame {
    private Maze maze;
    private User user; // Change to User Class after Database and User Class has been created

    MazeDataHandler data;

    private int mazeRowLength, mazeColLength;

    JPanel outer;


    /**
     *
     * @param user new user input for creating or accessing a maze
     * @param maze helps in creating a maze or access a randomly generated maze
     */
    public EditorUI(User user, Maze maze, MazeDataHandler data) {
        super("Editor");
        this.data = data;
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

        JMenuItem save = new JMenuItem("Save");
        JMenuItem saveAs = new JMenuItem("Save as...");
        JMenuItem exportPNG = new JMenuItem("Export as PNG");

        // Events
        exitB.addActionListener(e -> this.dispose());

        // Add to Menu Bar
        fileMenu.add("New Maze");
        fileMenu.add(new JSeparator());
        fileMenu.add(save);
        fileMenu.add(saveAs);
        fileMenu.add(new JSeparator());
        fileMenu.add("Export as PNG");
        fileMenu.add(new JSeparator());
        fileMenu.add(exitB);

        // Edit menu
        JMenu editMenu = new JMenu("Edit");

        editMenu.add("Add Image");
        editMenu.add("Set Logo Image");

        // Add to menu bar
        bar.add(fileMenu);
        bar.add(editMenu);
        setJMenuBar(bar);

        //==Create menu item listener
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Maze m = new Maze();
                m.setMazeID(m.getMazeID());
                if (m.getMazeName() == "NULL") {
                    String mazename = JOptionPane.showInputDialog(saveAs, "Name:", null);
                    m.setMazeName(mazename);
                }
                data.saveMaze(m);

            }
        });

        //==Create menu item listener
        saveAs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Maze m = new Maze();
                String mazename = JOptionPane.showInputDialog(saveAs, "Name:", null);
                m.setMazeName(mazename);
                m.setAuthorName(user.getName());
                data.saveAsMaze(m);
            }
        });
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

        // Select mode
        JPanel modePanel = new JPanel();
        modePanel.setLayout(new BoxLayout(modePanel, BoxLayout.PAGE_AXIS));

        JLabel modeTitle = new JLabel("<html><br><h1>Edit Mode</h1></html>");
        modeTitle.setFont(new Font("SanSerif", Font.PLAIN, 20));


        JComboBox pickMode = new JComboBox<>(new String[] {"Wall Edit", "Place Image", "Remove Image", "Set Start", "Set End"});
        pickMode.setMaximumSize(new Dimension(pickMode.getMaximumSize().width, 25));

        // Image
        JPanel imagePanel = new JPanel();

        JButton imagePicker = new JButton("Choose Image...");
        imagePicker.setEnabled(false);

        JLabel imagePath = new JLabel("File");
        imagePath.setMaximumSize(new Dimension(150, imagePath.getPreferredSize().height));


        pickMode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selected = pickMode.getSelectedIndex();
                maze.setMode(selected);

                if (selected == 1) {
                    imagePicker.setEnabled(true);
                    return;
                }

                imagePicker.setEnabled(false);

            }
        });

        imagePicker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser file = new JFileChooser();
                file.setCurrentDirectory(new File(System.getProperty("user.home")));

                //filter the files
                FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg","gif","png");
                file.addChoosableFileFilter(filter);
                int result = file.showSaveDialog(null);

                if(result == JFileChooser.APPROVE_OPTION){
                    File selectedFile = file.getSelectedFile();
                    String path = selectedFile.getAbsolutePath();

                    try {
                        maze.setImage(ImageIO.read(new File(path)));
                        imagePath.setText(path);

                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                }
                else if(result == JFileChooser.CANCEL_OPTION){
                    System.out.println("No file is selected");
                }
            }
        });

        // Add to Panel
        infoPanel.add(infoTitle);
        infoPanel.add(gridSize);
        infoPanel.add(cellExplore);
        infoPanel.add(deadendNum);
        infoPanel.add(isSolvable);

        modePanel.add(modeTitle);
        modePanel.add(pickMode);
        modePanel.add(imagePicker);
        modePanel.add(imagePath);

        optionsPanel.add(optionsTitle);
        optionsPanel.add(solutionCheckBox);

        sectionPanel.add(infoPanel);
        sectionPanel.add(modePanel);
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
            Cell cell = maze.getCell(rowIndex, colIndex);

            // Add new cell panel to the overall maze panel
            mazePanel.add(new CellComponent(cell, maze).newCellPanel());

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
