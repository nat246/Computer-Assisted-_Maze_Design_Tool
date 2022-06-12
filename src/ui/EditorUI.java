package ui;

import maze.*;
import maze.datamanager.MazeDataHandler;
import user.User;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.NoSuchElementException;
import java.util.Stack;
import java.io.Serializable;

/**
 * Class responsible for Editing the UI frames and sections
 */ 

public class EditorUI extends JFrame implements Serializable{
    private final Maze maze;
    private final User user; // Change to User Class after Database and User Class has been created
    MazeDataHandler data;
    private final int mazeRowLength, mazeColLength;
    private boolean showSolution;
    private Stack<Cell> cellTrailPlaceHolder = new Stack<>();

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
    }

    public void startEditor() {
        initEditor();
        topBar();
        outerPanel();
        maze.getWallsEvent().update();
    }

    public void showPath() {
        showSolution = true;
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
        fileMenu.add(save);
        fileMenu.add(saveAs);
        fileMenu.add(new JSeparator());
        fileMenu.add(exportPNG);
        fileMenu.add(new JSeparator());
        fileMenu.add(exitB);

        // Add to menu bar
        bar.add(fileMenu);
        setJMenuBar(bar);

        //==Create menu item listener
        saveAs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                maze.setMazeName(JOptionPane.showInputDialog(saveAs, "Name:", null));
                maze.setAuthorName(user.getName());
                data.saveAsMaze(maze);
            }
        });

        exportPNG.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveScreenshot(maze.getMazePanel());
            }
        });
    }

    private void saveScreenshot(JPanel panel) {
        JFileChooser file = new JFileChooser();
        file.setFileFilter(new FileNameExtensionFilter("*.Images", "png", "jpg", "gif"));
        file.setFileSelectionMode(JFileChooser.FILES_ONLY);
        file.setCurrentDirectory(new File(System.getProperty("user.home")));
        BufferedImage img = new BufferedImage(panel.getSize().width, panel.getSize().height, BufferedImage.TYPE_INT_RGB);
        panel.paint(img.createGraphics());
        int result = file.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = file.getSelectedFile();
            try {
                ImageIO.write(img, "PNG", selectedFile.getAbsoluteFile());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        else {
            System.out.println("No file is selected");
        }
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
        sectionPanel.setLayout(new GridLayout(6, 0));
        sectionPanel.setMinimumSize(new Dimension(175, 10));

        // Information
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.PAGE_AXIS));
        
        JLabel infoTitle = new JLabel("<html><h1>Maze Information</h1></html>");
        JLabel gridSize = new JLabel(String.format("<html><strong>Maze Size:</strong> [ X: %d, Y: %d ]</html>", maze.getSize()[0], maze.getSize()[1]));
        JLabel cellExplore = new JLabel(String.format("<html><strong>Cell Exploration:</strong> %d%%</html>", 50));
        JLabel deadendNum = new JLabel(String.format("<html><strong>No. Dead Cells:</strong> %d</html>", (maze.getSize()[0] * maze.getSize()[1])));
        JLabel isSolvable = new JLabel(String.format("<html><strong>Solvable:</strong> %b</html>", true));

        // Options
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.PAGE_AXIS));

        JLabel optionsTitle = new JLabel("<html><br><h1>Options</h1></html>");
        optionsTitle.setFont(new Font("SanSerif", Font.PLAIN, 20));

        JCheckBox solutionCheckBox = new JCheckBox("Show Solution");
        solutionCheckBox.setSelected(showSolution);

        // Select mode
        JPanel modePanel = new JPanel();
        modePanel.setLayout(new BoxLayout(modePanel, BoxLayout.PAGE_AXIS));

        JPanel modeInnerPanel = new JPanel();
        modeInnerPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel modeTitle = new JLabel("<html><br><h1>Edit Mode</h1></html>");
        modeTitle.setFont(new Font("SanSerif", Font.PLAIN, 20));

        JComboBox<String> pickMode = new JComboBox<>(new String[] {"Wall Edit", "Place Image", "Remove Image", "Set Start", "Set End"});
        pickMode.setMaximumSize(new Dimension(pickMode.getMaximumSize().width, 25));
        pickMode.setPreferredSize(new Dimension(pickMode.getPreferredSize().width + 20, 25));

        // Image
        JButton imagePicker = new JButton("Choose Image...");
        imagePicker.setEnabled(false);

        JLabel imagePath = new JLabel("No Image Chosen");
//        imagePath.setMaximumSize(new Dimension(150, imagePath.getPreferredSize().height));

        // Update Side Panel information
        maze.getWallsEvent().addCellListener(() -> {
            deadendNum.setText(String.format("<html><strong>No. Dead Cells:</strong> %d</html>", maze.getDeadEnds()));
            isSolvable.setText(String.format("<html><strong>Solvable:</strong> %b</html>", updateTrail()));

            // Update cell exploration percent
            double explore =  ((double) cellTrailPlaceHolder.size() / (maze.getSize()[0] * maze.getSize()[1]));
            int percent = (int)(explore * 100);
            cellExplore.setText(String.format("<html><strong>Cell Exploration:</strong> %d%%</html>", percent));
        });

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

        solutionCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSolution = solutionCheckBox.isSelected();
                maze.getWallsEvent().update();
            }
        });

        // Add to Panel
        infoPanel.add(infoTitle);
        infoPanel.add(gridSize);
        infoPanel.add(cellExplore);
        infoPanel.add(deadendNum);
        infoPanel.add(isSolvable);

        modeInnerPanel.add(pickMode);
        modeInnerPanel.add(imagePicker);

        modePanel.add(modeTitle);
        modePanel.add(imagePath);
        modePanel.add(modeInnerPanel);

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

        JPanel mazePanel = new MazeCreator(maze).mazePanel();
        int preferredWidth = (sectionPanel.getPreferredSize().height / largest) * mazeColLength;
        int preferredHeight = (sectionPanel.getPreferredSize().width / largest) * mazeRowLength;

        mazePanel.setPreferredSize(new Dimension(preferredWidth, preferredHeight));
        mazePanel.setLayout(new GridLayout(mazeRowLength, mazeColLength));

        // Centre the maze
        sectionInner.add(mazePanel);
        sectionPanel.add(Box.createVerticalGlue());
        sectionPanel.add(sectionInner, BorderLayout.CENTER);
        sectionPanel.add(Box.createVerticalGlue());

        // Trail
        updateTrail();
        return sectionPanel;
    }

    private boolean updateTrail()  {
        try{
            for (Cell cell :
                    cellTrailPlaceHolder) {
                cell.getCellPanel().setType(0);
            }

            MazeSolver mazeSolver = new MazeSolver(maze);

            if (showSolution){
                mazeSolver.colorPath();
                cellTrailPlaceHolder = mazeSolver.getTrailStack();
            }

        } catch (NoSuchElementException err) {
            return false;
        }
        return true;
    }

}
