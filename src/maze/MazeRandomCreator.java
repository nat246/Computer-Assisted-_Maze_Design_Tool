package maze;

import ui.CellComponent;

import java.util.*;

/**
 * used for creating a random generated maze
 * Class is purely functions, no objects
 */
public class MazeRandomCreator{
    private final List<Cell> visitedCells = new ArrayList<>();
    private final Stack<Cell> trailStack = new Stack<>();
    private final Maze maze;
    private final int rowSize, colSize;
    private Cell cellExplorer;

    public MazeRandomCreator() {
        this.maze = new Maze();
        this.rowSize = 1;
        this.colSize = 1;
    }

    public MazeRandomCreator(Maze maze){
        this.maze = maze;

        rowSize = maze.getSize()[0];
        colSize = maze.getSize()[1];

        // initialise start and end cells
        int randStartRow = new Random().nextInt(rowSize);
        int randEndRow = new Random().nextInt(rowSize);
        int randStartCol = new Random().nextInt(colSize);
        int randEndCol = new Random().nextInt(colSize);
        while (randStartCol == randEndCol && randStartRow == randEndRow){
            if (rowSize == 1 && colSize == 1){
                break;
            }
            randEndRow = new Random().nextInt(rowSize);
            randStartCol = new Random().nextInt(colSize);
        }
        cellExplorer = maze.getCell(randStartRow,randStartCol);
        maze.setStartPos(randStartRow, randStartCol);
        maze.setEndPos(randEndRow, randEndCol);

        visitedCells.add(cellExplorer);
        trailStack.add(cellExplorer);

        initRandomMaze();
    }

    private void initRandomMaze(){
        while (visitedCells.size() != (rowSize*colSize)){
            int explorerRow = cellExplorer.getPos().get(0);
            int explorerCol = cellExplorer.getPos().get(1);

            List<List<Object>> availableN = availableNeighbours(explorerRow, explorerCol);

            if (availableN.size() != 0){
                int randNeighbour = new Random().nextInt(availableN.size());

                removeWall(availableN.get(randNeighbour).get(1).toString());
                cellExplorer = (Cell) availableN.get(randNeighbour).get(0);
                visitedCells.add(cellExplorer);
                trailStack.add(cellExplorer);
            } else{
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


        if (topNotVisited){
            neighbours.add(new ArrayList<>(List.of(topN, "top")));
        }
        if (bottomNotVisited){
            neighbours.add(new ArrayList<>(List.of(bottomN, "bottom")));
        }
        if (leftNotVisited){
            neighbours.add(new ArrayList<>(List.of(leftN, "left")));
        }
        if (rightNotVisited){
            neighbours.add(new ArrayList<>(List.of(rightN, "right")));

        }

        return neighbours;
    }
    /**
     * @param position Takes in the wall position
     */
    private void removeWall(String position){
        cellExplorer.setWallStatus(position, false);
        removeAdjacentWall(position);
    }

    private void removeAdjacentWall(String position){
        boolean adjacentState = cellExplorer.getWallStatus(position);
        int rowI = cellExplorer.getPos().get(0);
        int colI = cellExplorer.getPos().get(1);

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
