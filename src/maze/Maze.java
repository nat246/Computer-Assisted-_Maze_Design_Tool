package maze;

import ui.CellComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Responsible for Creating and storing various maze details such as size, MazeID, AuthorName etc
 */
public class Maze {


    private int[] size;
    private int mazeID;
    private String logo;
    private Boolean solvable;
    private String authorName, mazeName;
    private String dateCreated, lastEdited;
    private int editMode;
    private HashMap<List<Integer>, Cell> cells;
    private JPanel mazePanel = new JPanel();
    private BufferedImage image;

    public Maze() {
        this.cells = new HashMap<>();
    }

    /**
     *
     * @param size user input size of the maze
     * @param user prints the name of the Author of the maze
     */
    public Maze(int[] size, String user, boolean isRandomGen) {
        this.size = size;
        this.authorName = user;
        this.cells = new HashMap<>();

        int rowSize = size[0], colSize = size[1];
        for (int row = 0; row < rowSize; row++){
            for (int col = 0; col < colSize; col++) {
                Cell cell = new Cell(row, col);
                addCell(cell);
            }
        }
        if (isRandomGen){
            new MazeRandomCreator(this);
        }
//        createMazePanel();
    }

    /**
     * Gets the size of the maze
     * @return size
     */
    public int[] getSize() {
        return size;
    }

    /**
     * Sets the size of the maze
     * @param size
     */
    public void setSize(int[] size) {
        this.size = size;
    }

    /**
     * Gets the mazeID
     * @return mazeID
     */
    public int getMazeID() {
        return mazeID;
    }

    /**
     * Sets the ID of the maze
     * @param mazeID
     */
    public void setMazeID(int mazeID) { this.mazeID = mazeID; }

    /**
     * Gets the logo
     * @return
     */
    public String getLogo() {
        return logo;
    }

    /**
     * Sets the maze logo
     * @param logo
     */
    public void setLogo(String logo) {
        this.logo = logo;
    }

    /**
     * Gets if the maze is solvable or not
     * @return true or false if maze is solvable
     */
    public Boolean getSolvable() {
        return solvable;
    }

    /**
     * Set if the maze is solvable or not
     * @param solvable
     */
    public void setSolvable(Boolean solvable) {
        this.solvable = solvable;
    }

    /**
     * Gets the Author's name of the Maze
     * @return authorName
     */
    public String getAuthorName() {
        return authorName;
    }

    /**
     * Sets the Author's name of the Maze
     * @param authorName
     */
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    /**
     * Gets the Maze's name/title
     * @return mazeName
     */
    public String getMazeName() {
        return mazeName;
    }

    /**
     * Sets the Maze's name/title
     * @param mazeName
     */
    public void setMazeName(String mazeName) { this.mazeName = mazeName; }

    /**
     * Gets the creation date of the Maze
     * @return dateCreated
     */
    public String getDateCreated() {
        return dateCreated;
    }

    /**
     * Sets the created date of the Maze
     * @param dateCreated
     */
    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     *Gets the last edited date of the maze
     * @return lastEdited
     */
    public String getLastEdited() {
        return lastEdited;
    }

    /**
     * Sets the last edited date of the maze
     * @param lastEdited
     */
    public void setLastEdited(String lastEdited) {
        this.lastEdited = lastEdited;
    }

    /**
     * Gets the cell position
     * @param row x cell position of the maze
     * @param col y cell position of the maze
     * @return cellPos
     */
    public Cell getCell(int row, int col) {
        List<Integer> cellPos = new ArrayList<>(List.of(row, col));
        return this.cells.get(cellPos);
    }

    /**
     * Adds a cell
     * @param cell adds a new cell for the maze
     */

    public void addCell(Cell cell) {
        this.cells.put(cell.getPos(), cell);
    }

//    public Cell getAdjacentCell(String pos){
//
//    }
    public JPanel getMazePanel() {
        return mazePanel;
    }

    public void setImage(BufferedImage image) { this.image = image; }

    public BufferedImage getImage() { return this.image; }

    public void setMode(int mode) { this.editMode = mode; }

    public int getMode() { return this.editMode; }

//    private void createMazePanel() {
//        int rowLength = size[0];
//        int colLength = size[1];
//
//
//        // Gets the largest number between the size of the grid
//        int largest = Math.max(colLength, rowLength);
//
//        int preferredWidth = (mazePanel.getPreferredSize().height / largest) * colLength;
//        int preferredHeight = (mazePanel.getPreferredSize().width / largest) * rowLength;
//
//        mazePanel.setPreferredSize(new Dimension(preferredWidth, preferredHeight));
//        mazePanel.setLayout(new GridLayout(rowLength, colLength));
//
//        int rowIndex = 0, colIndex = 0;
//        int totalNumCells = rowLength * colLength;
//        // Creates the number of cells for the size of the maze
//        for (int i = 0; i < totalNumCells; i++) {
//            // Creates a new cell class and adds it to the maze class
//            /**
//             * TODO
//             */
//            Cell cell = new Cell(rowIndex, colIndex);
//
//            // Store new cell to maze
//            addCell(cell);
//
//            // Add new cell panel to the overall maze panel
//            mazePanel.add(new CellComponent(cell, this).newCellPanel());
//
//            // Checks whether the column has reached the end
//            colIndex++;
//            if (colIndex == colLength) { colIndex = 0; rowIndex++; }
//        }
//    }

}

