package Maze;

import java.util.HashMap;
import java.awt.*;
import javax.swing.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

/**
 * Child class inheriting Grid mainly responsible for creating the walls and cells of a maze
 */
public class Cell extends Grid {
    
    private HashMap<String, Boolean> walls;
    private int cellType; // 0 is normal cell, 1 is start point, 2 is end point

    /**
     *
     * @param x dimension 1 user input inherited from Grid
     * @param y dimension 2 user input inherited from Grid
     */
    public Cell(int x, int y) {
        super(x, y);
        initWalls();
        cellType = 0;
    }

    /**
     *
     * @param location gives the location of the wall
     * @return walls.location
     */
    public Boolean wallStatus(String location) {
        return walls.get(location);
    }

    /**
     *
     * @param wall represents the user input used for setting up a wall
     * @param state boolean value of the state of the wall
     */
    public void setWall(String wall, boolean state) {
        walls.replace(wall, state);
    }

    /**
     *
     * @param type
     */
    public void setType(int type) {
        this.cellType = type;
    }

    /**
     *
     * @return
     */
    public int getType() {
        return this.cellType;
    }

    /**
     * Responsible for handling walls on top,bottom,left and right side of the mazes
     */
    private void initWalls() {
        walls = new HashMap<>();
        walls.put("top", false);
        walls.put("bottom", false);
        walls.put("right", false);
        walls.put("left", false);
    }

    
}
