package Maze;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Responsible for Creating and storing various maze details such as size, MazeID, AuthorName etc
 */
public class Maze {


    private int[] Size;
    private int MazeID;
    private int Logo;
    private Boolean Solvable;
    private String AuthorName, MazeName;
    private String DateCreated, LastEdited;
    private HashMap<int[], Cell> Cells;

    public Maze() {
        this.Cells = new HashMap<>();
    }

    /**
     *
     * @param Size user input size of the maze
     * @param User prints the name of the Author of the maze
     */
    public Maze(int[] Size, String User) {
        this.Size = Size;
        this.AuthorName = User;
        this.Cells = new HashMap<>();
    }

    public int[] GetSize() {
        return Size;
    }

    public int getMazeID() {
        return MazeID;
    }

    public int getLogo(){
        return Logo;
    }

    public Boolean MazeSolvable(Boolean Solvable) {
        return Solvable;
    }

    /**
     *
     * @return AuthorName
     */
    public String GetMazeAuthor() {

        return AuthorName;

    }

    /**
     *
     * @return MazeName
     */
    public String GetMazeName() {

        return MazeName;
    }

    /**
     *
     * @return DateCreated
     */

    public String GetMazeDate() {

        return DateCreated;
    }

    /**
     *
     * @return LastEdited
     */
    public String GetMazeLastEdited() {
        return LastEdited;
    }

    public void SetMazeAuthor(String AuthorName) {

        this.AuthorName = AuthorName;
    }

    public void SetMazeName(String MazeName){
        this.MazeName = MazeName;
    }

    public void SetMazeDateCreated(String DateCreated) {
        this.DateCreated = DateCreated;
    }

    public void SetMazeLastEdited(String LastEdited) {
        this.LastEdited = LastEdited;
    }

    /**
     *
     * @param cellPos cell postion of the maze
     * @return cellPos
     */
    public Cell GetCell(int[] cellPos) {
        return this.Cells.get(cellPos);
    }

    /**
     *
     * @param cell adds a new cell for the maze
     */

    public void AddCell(Cell cell) {
        this.Cells.put(cell.getPos(), cell);
    }

}

