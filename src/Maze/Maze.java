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

    public void SetMazeDetails(ArrayList<Object> AuthorName,ArrayList<Object> MazeName,ArrayList<Object> DateCreated, ArrayList<Object> LastEdited) {

        this.AuthorName = AuthorName;
        this.MazeName = MazeName;
        this.DateCreated = DateCreated;
        this.LastEdited = LastEdited;

    }
}
