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

    public User getUser(User u) {
        return mazeData.getUser(u.getName());
    }

    public void newMaze(Maze m) {
        if (!mazeListModel.contains(m.getMazeName())) {
            mazeListModel.addElement(m.getMazeName());
            mazeData.addMaze(m);
        }
    }

    /**
     * Deletes specific maze off the database
     * @param m
     */
    public void deleteMaze(Maze m) {
        mazeListModel.removeElement(m.getMazeID());
        mazeData.deleteMaze(m);
    }
    /**
     * Gets the maze from the database
     * @param m
     * @return maze from specific ID
     */
    public Maze getMaze(Maze m) {
        return mazeData.getMaze(m.getMazeName());
    }

    /**
     * Saves the maze
     * @param m
     */
    public void saveMaze(Maze m) {

    }

    public void persist() {
        mazeData.close();
    }
}
