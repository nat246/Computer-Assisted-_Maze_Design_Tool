package Maze;

import java.util.HashMap;

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
    private HashMap<int[], Cell> cells;

    public Maze() {
        this.cells = new HashMap<>();
    }

    /**
     *
     * @param Size user input size of the maze
     * @param User prints the name of the Author of the maze
     */
    public Maze(int[] Size, String User) {
        this.size = Size;
        this.authorName = User;
        this.cells = new HashMap<>();
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
    public void setMazeName(String mazeName) {
        this.mazeName = mazeName;
    }

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
     * @param cellPos cell position of the maze
     * @return cellPos
     */
    public Cell getCell(int[] cellPos) {
        return this.cells.get(cellPos);
    }

    /**
     * Adds a cell
     * @param cell adds a new cell for the maze
     */

    public void addCell(Cell cell) {
        this.cells.put(cell.getPos(), cell);
    }

}

