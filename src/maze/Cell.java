package maze;

import ui.CellComponent;

import java.util.HashMap;

import javax.swing.JPanel;
import java.io.Serializable;

/**
 * Child class inheriting Grid mainly responsible for creating the walls and cells of a maze
 */
public class Cell extends Grid implements Serializable{
    private HashMap<String, Boolean> walls;
    private HashMap<String, JPanel> wallUI;
    private int cellType; // 0 is normal cell, 1 is start point, 2 is end point
    private int wallsActive;
    private CellComponent cellPanel;

    private boolean isPartOfTrail;

    /**
     * @param x dimension 1 user input inherited from Grid
     * @param y dimension 2 user input inherited from Grid
     */
    public Cell(int x, int y) {
        super(x, y);
        initWalls();
        wallUI = new HashMap<>();
        cellType = 0;
    }


    /**
     * @param location gives the location of the wall
     * @return walls.location
     */
    public Boolean getWallStatus(String location) {
        return walls.get(location);
    }

    /**
     * @param wall  represents the user input used for setting up a wall
     * @param state boolean value of the state of the wall
     */
    public void setWallStatus(String wall, boolean state) {
        walls.replace(wall, state);
    }


    /**
     * @param type
     */
    public void setType(int type) {
        this.cellType = type;
    }

    /**
     * @return
     */
    public int getType() {
        return this.cellType;
    }

    public CellComponent getCellPanel() {
        return cellPanel;
    }

    public void setCellPanel(CellComponent cellPanel) {
        this.cellPanel = cellPanel;
    }

    public void setWallPanel(String pos, JPanel wall) {
        wallUI.put(pos, wall);
    }

    public JPanel getWallPanel(String pos) {
        return wallUI.get(pos);
    }

    public int getWallsActive() {
        wallsActive = 0;

        for (boolean wallStatus :
                walls.values()) {
            if (wallStatus) wallsActive++;
        }
        return wallsActive;
    }

    /**
     * Responsible for handling walls on top,bottom,left and right side of the mazes
     */
    private void initWalls() {
        walls = new HashMap<>();
        walls.put("top", true);
        walls.put("bottom", true);
        walls.put("right", true);
        walls.put("left", true);
    }
}