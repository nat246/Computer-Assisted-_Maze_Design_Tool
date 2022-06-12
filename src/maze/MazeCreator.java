package maze;

import ui.CellComponent;

import javax.swing.*;

/**
 * Used for creating a maze
 */
public class MazeCreator {
    private Maze maze;
    private int rowSize, colSize;
    private JPanel mazePanel;

    public MazeCreator(Maze maze) {
        this.maze = maze;
        this.rowSize = maze.getSize()[0];
        this.colSize = maze.getSize()[1];
        this.mazePanel = new JPanel();
    }

    public JPanel mazePanel(){
        int rowIndex = 0, colIndex = 0;
        int totalNumCells = rowSize * colSize;
        // Creates the number of cells for the size of the maze
        for (int i = 0; i < totalNumCells; i++) {
            // Creates a new cell class and adds it to the maze class
            Cell cell = maze.getCell(rowIndex, colIndex);

            // Add new cell panel to the overall maze panel
            mazePanel.add(new CellComponent(cell, maze).newCellPanel());

            // Checks whether the column has reached the end
            colIndex++;
            if (colIndex == colSize) { colIndex = 0; rowIndex++; }
        }

        maze.setMazePanel(mazePanel);
        return mazePanel;
    }

}