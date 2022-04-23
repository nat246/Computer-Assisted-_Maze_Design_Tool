package Maze;

import java.awt.*;
import javax.swing.*;

public abstract class Grid {

    private int width;
    private int height;

    public Grid(int w, int h) {
        width = w;
        height = h;
    }

    public class GridDimensions extends Grid { //class used for calculating the grid dimensions
        public GridDimensions(int w, int h) {
            super(w, h);
            int area = width * height;
        }
    }

    public class GridWalls extends Grid { //class used for calculating the number of walls in a grid
        public GridWalls(int w, int h) {
            super(w, h);
            int area = width * height;
            int nWalls;
        }
    }
}

