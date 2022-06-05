package ui;

import maze.Cell;
import maze.Maze;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class CellComponent {

    private Boolean editorMode = false;
    private Cell cell;
    private Maze maze;
    // [N,E,S,W]
    private int[] wallsActivated = new int[]{0,0,0,0};

    public CellComponent(Cell cell, Maze maze, Boolean editorMode) {
        this.cell = cell;
        this.maze = maze;
        this.editorMode = editorMode;
    }

    public CellComponent(Cell cell, Maze maze, int[] wallsActivated) {
        this.cell = cell;
        this.maze = maze;
    }

    /**
     * Method for each cell in the maze
     * @return cellPanel dimensions for creating a new cell
     */
    public JPanel newCellPanel() {
        JPanel cellPanel = new JPanel();
        cellPanel.setLayout(null);

        // Create a wall on each side
        SwingUtilities.invokeLater(() -> {
             cellPanel.add(cellWall("top", cellPanel));
             cellPanel.add(cellWall("bottom", cellPanel));
             cellPanel.add(cellWall("left", cellPanel));
             cellPanel.add(cellWall("right", cellPanel));
        });

        return cellPanel;
    }

    private JPanel cellWall(String wallPosition, JPanel cellPanel) {

        // Color of the wall
        JPanel wall = wallColor(wallPosition);

        // Wall coordinates
        int topX = 0;
        int topY = 0;
        int bottomX = 0;
        int bottomY = ((cellPanel.getHeight() - wallThickness(cellPanel, "bottom")));
        int leftX = 0;
        int leftY = 0;
        int rightX = ((cellPanel.getWidth() - wallThickness(cellPanel, "right")));
        int rightY = 0;

        // Wall Width and Length
        int verticalWallLength = cellPanel.getWidth();
        int horizontalWallLength = cellPanel.getHeight();
        int topWallWidth = wallThickness(cellPanel, "top");
        int bottomWallWidth = wallThickness(cellPanel, "bottom");
        int leftWallWidth = wallThickness(cellPanel, "left");
        int rightWallWidth = wallThickness(cellPanel, "right");


        // Set wall size and coordinates
        switch (wallPosition){
            case "top":
                wall.setBounds(topX, topY, horizontalWallLength, topWallWidth);
                break;

            case "bottom":
                wall.setBounds(bottomX, bottomY, horizontalWallLength, bottomWallWidth);
                break;

            case "left":
                wall.setBounds(leftX, leftY, leftWallWidth, verticalWallLength);
                break;

            case "right":
                wall.setBounds(rightX, rightY, rightWallWidth, verticalWallLength);
                break;
        }


        cell.setWallPanel(wallPosition, wall);

        return wall;
    }

    /**
     * Creates a singular wall
     * @param location Location of the new wall to be placed
     * @return newWall according to the user inputs
     */
    private JPanel wallColor(String location) {
        JPanel newWall = new JPanel();

        // get each wall's state/status
        // if wall on side is true then color black


        newWall.setBackground(Color.BLACK);

        newWall.addMouseListener(new MouseAdapter() {
            float currentTransparency = 1f;
            final Color hoverColour = Color.ORANGE; //new Color((float)(255/255), (float)(185/255), (float)(60/255), 1f);
            final Color defaultColour = new Color(0, 0, 0, currentTransparency);

            @Override
            public void mouseEntered(MouseEvent e) {
                newWall.setBackground(hoverColour);
                JPanel wall;

                try {
                    switch (location) {
                        case "top":
                            wall = maze.getCell(new ArrayList<Integer>(java.util.List.of(cell.getPos().get(0) - 1, cell.getPos().get(1)))).getWallPanel("bottom");
                            wall.setBackground(hoverColour);
                            break;
                        case "bottom":
                            wall = maze.getCell(new ArrayList<Integer>(java.util.List.of(cell.getPos().get(0) + 1, cell.getPos().get(1)))).getWallPanel("top");
                            wall.setBackground(hoverColour);
                            break;
                        case "left":
                            wall = maze.getCell(new ArrayList<Integer>(java.util.List.of(cell.getPos().get(0), cell.getPos().get(1) - 1))).getWallPanel("right");
                            wall.setBackground(hoverColour);
                            break;
                        case "right":
                            wall = maze.getCell(new ArrayList<Integer>(java.util.List.of(cell.getPos().get(0), cell.getPos().get(1) + 1))).getWallPanel("left");
                            wall.setBackground(hoverColour);
                            break;
                    }
                } catch (NullPointerException error) {
                    System.out.println("wall not found");
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                newWall.setBackground(defaultColour);
                JPanel wall;

                try {
                    switch (location) {
                        case "top":
                            wall = maze.getCell(new ArrayList<Integer>(java.util.List.of(cell.getPos().get(0) - 1, cell.getPos().get(1)))).getWallPanel("bottom");
                            wall.setBackground(defaultColour);
                            break;
                        case "bottom":
                            wall = maze.getCell(new ArrayList<Integer>(java.util.List.of(cell.getPos().get(0) + 1, cell.getPos().get(1)))).getWallPanel("top");
                            wall.setBackground(defaultColour);
                            break;
                        case "left":
                            wall = maze.getCell(new ArrayList<Integer>(java.util.List.of(cell.getPos().get(0), cell.getPos().get(1) - 1))).getWallPanel("right");
                            wall.setBackground(defaultColour);
                            break;
                        case "right":
                            wall = maze.getCell(new ArrayList<Integer>(List.of(cell.getPos().get(0), cell.getPos().get(1) + 1))).getWallPanel("left");
                            wall.setBackground(defaultColour);
                            break;
                    }
                } catch (NullPointerException error) {
                    System.out.println("wall not found");
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (cell.wallStatus(location)) {
                    currentTransparency = 0.2f;
                    newWall.setBackground(new Color(0, 0, 0, currentTransparency));
                    System.out.println(currentTransparency);
                }
                else {
                    currentTransparency = 0f;
                    newWall.setBackground(new Color(0, 0, 0, currentTransparency));
                    System.out.println(currentTransparency);
                }
                cell.setWall(location, !cell.wallStatus(location));
                System.out.println(cell.wallStatus(location));
                System.out.println(String.format("pos: (%d, %d)", cell.getPos().get(0), cell.getPos().get(1)));
            }

        });


        return newWall;
    }

    private int wallThickness(JPanel panel, String position) {
        int horizontalSize = panel.getWidth() / 10;
        int verticalSize = panel.getWidth() / 10;

        switch (position) {
            case "top":
                if (cell.getPos().get(0) != 0) return verticalSize ;
                else return verticalSize * 2;
            case "bottom":
                if (cell.getPos().get(0) != maze.getSize()[0] - 1) return verticalSize;
                else return verticalSize * 2;
            case "left":
                if (cell.getPos().get(1) != 0) return horizontalSize;
                else return horizontalSize * 2;
            case "right":
                if (cell.getPos().get(1) != maze.getSize()[1] - 1) return horizontalSize;
                else return horizontalSize * 2;
        }

        return 0;
    }

}
