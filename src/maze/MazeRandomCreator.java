package maze;

import java.util.ArrayList;

/**
 * used for creating a random generated maze
 */
public class MazeRandomCreator extends Maze{
    private int visitedCells;
    private ArrayList<ArrayList<Integer>> pathStack = new ArrayList<ArrayList<Integer>>();

    public MazeRandomCreator() {

    }
}
