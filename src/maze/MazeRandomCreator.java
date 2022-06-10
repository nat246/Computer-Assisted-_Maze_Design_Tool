package maze;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * used for creating a random generated maze
 * Class is purely functions, no objects
 */
public class MazeRandomCreator{
    private final List<List<Object>> visitedCells = new ArrayList<>();
    private final ArrayList<ArrayList<Integer>> pathStack = new ArrayList<ArrayList<Integer>>();
    private final Maze maze;
    private final int rowSize, colSize;
    public MazeRandomCreator(Maze maze){
        this.maze = maze;
        rowSize = maze.getSize()[0];
        colSize = maze.getSize()[1];
        initRandomMaze();
    }

    private void initRandomMaze(){

        for (int i = 0; i < 100; i++){
            int row = new Random().nextInt(rowSize);
            int col = new Random().nextInt(colSize);

            String wallSelection;
            int randSelection = new Random().nextInt(4);
            switch (randSelection) {
                case 1 -> wallSelection = "top";
                case 2 -> wallSelection = "bottom";
                case 3 -> wallSelection = "left";
                default -> wallSelection = "right";
            }

            ArrayList<Object> contain = new ArrayList<>();
            contain.add(row);
            contain.add(col);
            contain.add(wallSelection);
            if (visitedCells.contains(contain)){
                System.out.println("Already visited");
                continue;
            } else {
                visitedCells.add(contain);
            }


            switchWallPanel(wallSelection, row, col);
        }
        System.out.println(visitedCells);
    }

    /**
     * TODO change method name to removeWallPanel/placeWallPanel?
     * @param position Takes in the wall possition
     * @param row Takes in the row index
     * @param col Takes in the column index
     */
    private void switchWallPanel(String position, int row, int col){
        Cell cell = maze.getCell(row,col);
        cell.setWallStatus(position, false);

        switchAdjacentWallPanel(cell,position);
    }

    private void switchAdjacentWallPanel(Cell cell, String position){
        boolean adjacentState = cell.getWallStatus(position);
        int rowI = cell.getPos().get(0);
        int colI = cell.getPos().get(1);

        try {
            switch (position) {
                case "top" -> maze.getCell(rowI - 1, colI).setWallStatus("bottom", adjacentState);
                case "bottom" -> maze.getCell(rowI + 1, colI).setWallStatus("top", adjacentState);
                case "left" -> maze.getCell(rowI, colI - 1).setWallStatus("right", adjacentState);
                case "right" -> maze.getCell(rowI, colI + 1).setWallStatus("left", adjacentState);
            }
        } catch (NullPointerException error) {
            System.out.println("Adjacent wall does not exist");
        }
    }



}
