package Maze;

import java.util.HashMap;
import java.awt.*;
import javax.swing.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class Cell extends Grid {
    
    private HashMap<String, Boolean> walls;

    public Cell(int x, int y) {
        super(x, y);
        initWalls();
    }

    public Boolean wallStatus(String location) {
        return walls.get(location);
    }

    public void setWall(String wall, boolean state) {
        walls.replace(wall, state);
    }

    private void initWalls() {
        walls = new HashMap<>();
        walls.put("top", false);
        walls.put("bottom", false);
        walls.put("right", false);
        walls.put("left", false);
    }

    
}
