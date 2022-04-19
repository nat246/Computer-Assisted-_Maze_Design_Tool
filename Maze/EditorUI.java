package Maze;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.event.*;

import javax.swing.*;

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
        gridBag = new GridBagConstraints();

        gridBag.gridwidth = 2;
        gridBag.gridheight = 1;

        outer.setLayout(gridLayout);
        
        getContentPane().add(outer);

    }

    private void informationPanel() {
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(BorderFactory.createTitledBorder("Information"));

        JLabel gridSize = new JLabel(String.format("<html><strong>Maze Size:</strong> [ X: %d, Y: %d ]</html>", 100, 100)); // temp size
        JLabel cellExplore = new JLabel(String.format("<html><strong>Cell Exploration:</strong> %d%%</html>", 50));
        JLabel deadendNum = new JLabel(String.format("<html><strong>No. Dead Cells:</strong> %d</html>", 4));
        JLabel isSolvable = new JLabel(String.format("<html><strong>Solvable:</strong> %b</html>", true));

        // Add to Panel
        infoPanel.add(gridSize);
        infoPanel.add(cellExplore);
        infoPanel.add(deadendNum);
        infoPanel.add(isSolvable);

        outer.add(UIHandler.NewGridItem(infoPanel, gridLayout, gridBag, 0, 0, 1, 1));
        
    }

    private void editorPanel() {
        JPanel mazePanel = new JPanel();
        mazePanel.setBorder(BorderFactory.createTitledBorder("Maze Editor"));
        mazePanel.setLayout(new GridLayout(2, 2));

        JLabel panelTitle = new JLabel("Maze");
        panelTitle.setSize(300, 10);

        
        mazePanel.add(panelTitle);

        
        outer.add(UIHandler.NewGridItem(mazePanel, gridLayout, gridBag, 2, 0, 1, 1));
    }

    private JPanel newCell() {
        JPanel cellPanel = new JPanel();
        

        return cellPanel;
    }

    

    
    
}
