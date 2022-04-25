package Maze;

import java.util.ArrayList;
import java.util.HashMap;

public class Maze {
    private int[] Size;
    private int MazeID;
    private int Logo;
    private Boolean Solvable;
    private String AuthorName, MazeName;
    private String DateCreated, LastEdited;
    private HashMap<int[], Cell> Cells;

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

    public String GetMazeAuthor() {

        return AuthorName;

    }
    public String GetMazeName() {

        return MazeName;
    }

    public String GetMazeDate() {

        return DateCreated;
    }

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

    public Cell GetCell(int[] cellPos) {
        return this.Cells.get(cellPos);
    }

    public void AddCell(Cell cell) {
        this.Cells.put(cell.getPos(), cell);
    }

}

