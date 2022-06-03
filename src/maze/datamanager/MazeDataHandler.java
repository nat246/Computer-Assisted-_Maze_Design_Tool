package maze.datamanager;

import maze.Maze;
import user.User;

import javax.swing.*;
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

    DefaultListModel userListModel;
    DefaultListModel mazeListModel;
    MazeDataSource mazeData;

    public MazeDataHandler() {
        userListModel = new DefaultListModel();
        mazeListModel = new DefaultListModel();
        mazeData = new JDBCMazeDataSource();

        for(String user : mazeData.userSet()) {
            userListModel.addElement(user);
        }

        for(String maze: mazeData.mazeSet()) {
            mazeListModel.addElement(maze);
        }
    }

    public void addUser(User u) {
        if (!userListModel.contains(u.getName())) {
            userListModel.addElement(u.getName());
            mazeData.addUser(u);
        }
    }

    public void removeUser(User u) {
        userListModel.removeElement(u.getUserId());
        mazeData.deleteUser(u);
    }

    public void newMaze(Maze m) {
        if (!mazeListModel.contains(m.getMazeName())) {
            mazeListModel.addElement(m.getMazeName());
            mazeData.addMaze(m);
        }
    }

    public void deleteMaze(Maze m) {
        mazeListModel.removeElement(m.getMazeID());
        mazeData.deleteMaze(m);
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
