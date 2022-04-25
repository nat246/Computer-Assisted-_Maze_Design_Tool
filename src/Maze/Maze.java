package Maze;

import java.util.ArrayList;

public class Maze {

    private int Size;
    private int MazeID = 0;
    private int Logo;
    private Boolean Solvable;
    private ArrayList<Object> AuthorName;
    private ArrayList<Object> MazeName;
    private ArrayList<Object> DateCreated;
    private ArrayList<Object> LastEdited;


    public int GetSize() {
        return Size;
    }

    public int getMazeID() {
        MazeID++;
        return MazeID;
    }

    public int getLogo(){

        return Logo;
    }

    public Boolean MazeSolvable(Boolean Solvable) {
        return Solvable;
    }

    public ArrayList<Object> GetMazeAuthor() {

        return AuthorName;

    }
    public ArrayList<Object> GetMazeName() {

        return MazeName;
    }

    public ArrayList<Object> GetMazeDate() {

        return DateCreated;
    }

    public ArrayList<Object> GetMazeLastEdited() {

        return LastEdited;
    }

    public void SetMazeAuthor(ArrayList<Object> AuthorName) {

        this.AuthorName = AuthorName;
    }

    public void SetMazeName(ArrayList<Object> MazeName){
        this.MazeName = MazeName;
    }

    public void SetMazeDateCreated(ArrayList<Object> DateCreated) {
        this.DateCreated = DateCreated;
    }

    public void SetMazeLastEdited(ArrayList<Object> LastEdited) {
        this.LastEdited = LastEdited;
    }
}
