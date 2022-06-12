package maze;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;
import java.io.Serializable;

/**
 * Provides the solution and solves the mazes saved in the database
 */
public class MazeSolver implements Serializable {
    private Maze maze;
    private final int rowSize, colSize;
    private Cell startCell, goalCell, cellExplorer;

    private final List<Cell> visitedCells = new ArrayList<>();
    private final Stack<Cell> trailStack = new Stack<>();

    public MazeSolver(Maze maze) {
        this.maze = maze;
        this.rowSize = maze.getSize()[0];
        this.colSize = maze.getSize()[1];

        this.startCell = maze.getCell(maze.getStartPos().get(0), maze.getStartPos().get(1));
        this.goalCell = maze.getCell(maze.getEndPos().get(0), maze.getEndPos().get(1));

        this.cellExplorer = startCell;
        visitedCells.add(cellExplorer);
        trailStack.add(cellExplorer);

        initSolver();
    }

    private void initSolver() {
        while (cellExplorer != goalCell) {
            int explorerRow = cellExplorer.getPos().get(0);
            int explorerCol = cellExplorer.getPos().get(1);

            List<List<Object>> availableN = availableNeighbours(explorerRow, explorerCol);

            if (availableN.size() != 0){
                int randNeighbour = new Random().nextInt(availableN.size());

                cellExplorer = (Cell) availableN.get(randNeighbour).get(0);
                visitedCells.add(cellExplorer);
                trailStack.add(cellExplorer);
            } else {
                trailStack.pop();
                cellExplorer = trailStack.lastElement();
            }
        }

    }

    private List<List<Object>> availableNeighbours(int explorerRow, int explorerCol){
        List<List<Object>> neighbours = new ArrayList<>();

        boolean topNotVisited, bottomNotVisited, leftNotVisited, rightNotVisited;

        Cell topN = maze.getCell(explorerRow-1, explorerCol);
        Cell bottomN = maze.getCell(explorerRow+1, explorerCol);
        Cell leftN = maze.getCell(explorerRow, explorerCol-1);
        Cell rightN = maze.getCell(explorerRow, explorerCol+1);

        boolean topWall = cellExplorer.getWallStatus("top");
        boolean bottomWall = cellExplorer.getWallStatus("bottom");
        boolean leftWall = cellExplorer.getWallStatus("left");
        boolean rightWall = cellExplorer.getWallStatus("right");


        if (topN == null){
            topNotVisited = false;
        } else {
            topNotVisited = !visitedCells.contains(topN);
        }

        if (bottomN == null){
            bottomNotVisited = false;
        } else {
            bottomNotVisited = !visitedCells.contains(bottomN);
        }

        if (leftN == null){
            leftNotVisited = false;
        } else {
            leftNotVisited = !visitedCells.contains(leftN);
        }

        if (rightN == null){
            rightNotVisited = false;
        } else {
            rightNotVisited = !visitedCells.contains(rightN);
        }


        if (topNotVisited && !topWall){
            neighbours.add(new ArrayList<>(List.of(topN, "top")));
        }
        if (bottomNotVisited && !bottomWall){
            neighbours.add(new ArrayList<>(List.of(bottomN, "bottom")));
        }
        if (leftNotVisited && !leftWall){
            neighbours.add(new ArrayList<>(List.of(leftN, "left")));
        }
        if (rightNotVisited && !rightWall){
            neighbours.add(new ArrayList<>(List.of(rightN, "right")));

        }

        return neighbours;
    }

    public void colorPath(){
        // Removing these from the list doesn't interfere with start and end colours
        trailStack.remove(0);
        trailStack.remove(goalCell);

        // Sets color to each cell in trail
        for (Cell cell :
                trailStack) {
            cell.getCellPanel().setType(3);
        }

    }

    public Stack<Cell> getTrailStack() {
        trailStack.remove(0);
        trailStack.remove(goalCell);
        return trailStack;
    }

}
