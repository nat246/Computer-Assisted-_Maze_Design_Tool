package ui;

import maze.Cell;
import maze.Maze;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CellComponent {

    private Boolean editorMode = false;
    private Cell cell;
    private Maze maze;

    private JPanel cellPanel;
    private JLabel icon;
    private Color defaultColour;

    public CellComponent(Cell cell, Maze maze, Boolean editorMode) {
        this.cell = cell;
        this.maze = maze;
        this.editorMode = editorMode;

    }


    /**
     * Method for each cell in the maze
     * @return cellPanel dimensions for creating a new cell
     */
    public JPanel newCellPanel() {
        cellPanel = new JPanel();
        cellPanel.setLayout(null);
        defaultColour = cellPanel.getBackground();

        cellPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switch (maze.getMode()) {
                    case 1:
                        createImage();
                        break;
                    case 2:
                        removeImage();
                        break;
                    default:
                        break;
                }

            }
        });

        // Create a wall on each side
        SwingUtilities.invokeLater(() -> {
             cellPanel.add(cellWall("top", cellPanel));
             cellPanel.add(cellWall("bottom", cellPanel));
             cellPanel.add(cellWall("left", cellPanel));
             cellPanel.add(cellWall("right", cellPanel));

             // if !activateWall[right] => cellPanel.add(whiteCellWall("right, cellPanel));

        });

        return cellPanel;
    }

    public void setPathBackground(boolean path) {
        if (path) {
            cellPanel.setBackground(Color.ORANGE);
            return;
        }
        cellPanel.setBackground(defaultColour);
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


        wall.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cell.setWallPanel(wallPosition, wall);

        return wall;
    }


    /**
     * Creates a singular wall
     * @param position Location of the new wall to be placed
     * @return newWall according to the user inputs
     */
    private JPanel wallColor(String position) {
        JPanel newWall = new JPanel();
        newWall.setBackground(Color.BLACK);

        newWall.addMouseListener(new MouseAdapter() {
            final Color transparent = new Color(0,0,0,0);
            final Color black = Color.BLACK;

            @Override
            public void mouseClicked(MouseEvent e) {
                JPanel adjacentWall = new JPanel();
                if (maze.getMode() != 0) return;
//                 Change wall color to transparent
                if (cell.getWallStatus(position)) {
                    newWall.setOpaque(false);
                    newWall.setBackground(transparent);
                    adjacentWall = switchColor(adjacentWall, position, transparent, false);

                }
                // Change wall color to black
                else {
                    newWall.setOpaque(true);
                    newWall.setBackground(black);
                    adjacentWall = switchColor(adjacentWall,position,black, true);
                }


                cell.setWallStatus(position, !cell.getWallStatus(position));
                System.out.println(cell.getWallStatus(position));
                System.out.println(String.format("pos: (%d, %d)", cell.getPos().get(0), cell.getPos().get(1)));

            }
        });

        return newWall;
    }

    private JPanel switchColor(JPanel wall, String position, Color color, boolean opacity){

        try {
            switch (position) {
                case "top":
                    wall = maze.getCell(cell.getPos().get(0) - 1, cell.getPos().get(1)).getWallPanel("bottom");
                    break;
                case "bottom":
                    wall = maze.getCell(cell.getPos().get(0) + 1, cell.getPos().get(1)).getWallPanel("top");
                    break;
                case "left":
                    wall = maze.getCell(cell.getPos().get(0), cell.getPos().get(1) - 1).getWallPanel("right");
                    break;
                case "right":
                    wall = maze.getCell(cell.getPos().get(0), cell.getPos().get(1) + 1).getWallPanel("left");
                    break;
            }
        } catch (NullPointerException error) {
            System.out.println("wall not found");
        }

        wall.setOpaque(opacity);
        wall.setBackground(color);
        return wall;
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

    private void createImage() {
        icon = new JLabel();
        cellPanel.add(icon);

        SwingUtilities.invokeLater(() -> {
            icon.setSize(cellPanel.getSize());

            // Set Image Size
            try {
                Image newImg = maze.getImage().getScaledInstance(icon.getWidth(), icon.getHeight(), Image.SCALE_SMOOTH);
                icon.setIcon(new ImageIcon(newImg));
            } catch (NullPointerException e) {
                System.out.println("No Image Selected.");
            }

        });
    }

    private void removeImage() {
        try {
            icon.setIcon(null);
        }
        catch (NullPointerException e) {}
    }

}
