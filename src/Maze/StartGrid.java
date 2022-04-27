package Maze;

import java.util.ArrayList;

public class StartGrid extends Grid {

    private ArrayList<Object> startingGrid;

    public StartGrid(int x, int y) {
        super(x, y);
    }

    public ArrayList<Object> GetStartGrid(){
        return startingGrid;
    }

    public void SetStartGrid(ArrayList<Object>startingGrid){
        this.startingGrid = startingGrid;
    }



}

