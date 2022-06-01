package maze.datamanager;

import maze.Maze;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Creates a new maze
 */
public class MazeDataHandler {

    public static void newMaze() {

    }

    /**
     * Gets the maze from the database
     * @param mazeID Unique ID given for each maze
     * @return maze from specific ID
     */
    public static Maze getMaze(int mazeID) {
        Maze maze = new Maze();

        return maze;
    }

    /**
     * Saves the maze
     * @param mazeID
     */
    public static void saveMaze(int mazeID) {

    }

    /**
     * Deletes specific maze off the database
     * @param mazeID
     */
    public static void deleteMaze(int mazeID){

    }

}
