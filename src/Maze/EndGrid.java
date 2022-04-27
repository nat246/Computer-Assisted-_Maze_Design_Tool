package Maze;

import java.util.ArrayList;

public class EndGrid extends Grid {

    private ArrayList<Object> endGrid;

    public EndGrid(int x, int y) {
        super(x, y);
    }

    public ArrayList<Object> GetEndingGrid() {
        return endGrid;
    }

    public void SetEndingGrid(ArrayList<Object> endGrid) {
        this.endGrid = endGrid;
    }
}