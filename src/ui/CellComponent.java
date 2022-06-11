package ui;

import maze.Cell;
import maze.Maze;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CellComponent {

    private final Cell cell;
    private final Maze maze;
    private final JPanel cellPanel;

    private JLabel icon;
    private Color defaultColour;

    public CellComponent(Cell cell, Maze maze) {
        this.cell = cell;
        this.maze = maze;
        cellPanel = new JPanel();
    }

    /**
     * Method for each cell in the maze
     * @return cellPanel dimensions for creating a new cell
     */
    public JPanel newCellPanel() {
        cell.setCellPanel(this);
        cellPanel.setLayout(null);
        icon = new JLabel();
        defaultColour = cellPanel.getBackground();

        cellPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switch (maze.getMode()) {
                    case 1 -> createImage();
                    case 2 -> removeImage();
                    case 3 -> setType(1);
                    case 4 -> setType(2);
                    default -> {}
                }
            }
        });

        // Create a wall on each side
        SwingUtilities.invokeLater(() -> {
             cellPanel.add(cellWall("top", cell.getWallStatus("top")));
             cellPanel.add(cellWall("bottom", cell.getWallStatus("bottom")));
             cellPanel.add(cellWall("left", cell.getWallStatus("left")));
             cellPanel.add(cellWall("right", cell.getWallStatus("right")));

            if (maze.getStartPos().equals(cell.getPos())) { setType(1); }
            if (maze.getEndPos().equals(cell.getPos())) { setType(2); }
        });
        maze.updateDeadEnd();
        return cellPanel;
    }

    public void setType(int typeID) {
        switch (typeID) {
            case 1 -> { // Start Point
                maze.getCell(maze.getStartPos().get(0), maze.getStartPos().get(1)).getCellPanel().setType(0);
                maze.setStartPos(cell.getPos().get(0), cell.getPos().get(1));
                cellPanel.setBackground(Color.GREEN);
            }
            case 2 -> { // End Point
                maze.getCell(maze.getEndPos().get(0), maze.getEndPos().get(1)).getCellPanel().setType(0);
                maze.setEndPos(cell.getPos().get(0), cell.getPos().get(1));
                cellPanel.setBackground(Color.RED);
            }
            case 3 -> // Path
                    cellPanel.setBackground(Color.PINK);
            default -> // Default
                    cellPanel.setBackground(defaultColour);
        }
    }

    private JPanel cellWall(String wallPosition, boolean state) {
        // Color of the wall
        JPanel wall = wallColor(wallPosition, state);

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
        switch (wallPosition) {
            case "top" -> wall.setBounds(topX, topY, horizontalWallLength, topWallWidth);
            case "bottom" -> wall.setBounds(bottomX, bottomY, horizontalWallLength, bottomWallWidth);
            case "left" -> wall.setBounds(leftX, leftY, leftWallWidth, verticalWallLength);
            case "right" -> wall.setBounds(rightX, rightY, rightWallWidth, verticalWallLength);
        }

        wall.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cell.setWallPanel(wallPosition, wall);
        maze.updateDeadEnd();
        return wall;
    }

    /**
     * Creates a singular wall
     * @param position Location of the new wall to be placed
     * @return newWall according to the user inputs
     */
    private JPanel wallColor(String position, boolean state) {
        JPanel newWall = new JPanel();
        final Color transparent = new Color(0,0,0,0);
        final Color black = Color.BLACK;

        if (state){
            newWall.setBackground(black);
        } else {
            newWall.setOpaque(false);
            newWall.setBackground(transparent);
        }

        newWall.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (maze.getMode() != 0) return;

                System.out.println("clicked");
                cell.setWallStatus(position, !cell.getWallStatus(position));
                boolean wallStatus = cell.getWallStatus(position);

                newWall.setOpaque(wallStatus);
                newWall.setBackground((wallStatus) ? black : transparent);
                switchAdjacentColor(position, (wallStatus) ? black : transparent, wallStatus);

                maze.updateDeadEnd();
                maze.getWallsEvent().setState(state);

            }
        });

        return newWall;
    }

    private void switchAdjacentColor(String position, Color color, boolean opacity){
        Cell adjCell;
        String pos;
        JPanel wall = new JPanel();
        try {
            switch (position) {
                case "top" -> {
                    adjCell = maze.getCell(cell.getPos().get(0) - 1, cell.getPos().get(1));
                    pos = "bottom";
                    wall = adjCell.getWallPanel(pos);
                    adjCell.setWallStatus(pos, cell.getWallStatus(position));
                }
                case "bottom" -> {
                    adjCell = maze.getCell(cell.getPos().get(0) + 1, cell.getPos().get(1));
                    pos = "top";
                    wall = adjCell.getWallPanel(pos);
                    adjCell.setWallStatus(pos, cell.getWallStatus(position));
                } case "left" -> {
                    adjCell = maze.getCell(cell.getPos().get(0), cell.getPos().get(1) - 1);
                    pos = "right";
                    wall = adjCell.getWallPanel(pos);
                    adjCell.setWallStatus(pos, cell.getWallStatus(position));
                } case "right" -> {
                    adjCell = maze.getCell(cell.getPos().get(0), cell.getPos().get(1) + 1);
                    pos = "left";
                    wall = adjCell.getWallPanel(pos);
                    adjCell.setWallStatus(pos, cell.getWallStatus(position));
                }
            }
        } catch (NullPointerException error) {
            System.out.println("wall not found");
        }

        wall.setOpaque(opacity);
        wall.setBackground(color);
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
        catch (NullPointerException ignored) {}
    }

}
