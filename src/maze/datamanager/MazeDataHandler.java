package maze.datamanager;

import maze.Maze;
import user.User;

import javax.swing.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.HashSet;
import java.util.Set;
import java.io.Serializable;

/**
 * Creates a new maze and allows the user to edit the existing mazes in the database
 */
public class MazeDataHandler implements Serializable{

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

        for(Integer maze: mazeData.mazeSet()) {
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
        userListModel.removeElement(u.getName());
        mazeData.deleteUser(u);
    }

    public User getUser(User u) {
        return mazeData.getUser(u.getName());
    }

    public boolean login(User u) {
        if (userListModel.contains(u.getName())) {
            if (userListModel.contains(u.getPassword())) {
                return true;
            }
            else { return false; }
        }
        else { return false; }
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
     * @param id
     * @return maze from specific ID
     */
    public Maze getMaze(Integer id) {
        return mazeData.getMaze(id);
    }

    /**
     * Saves the maze that already exists on the database
     * @param m
     */
    public void saveMaze(Maze m) {
        if (!mazeListModel.contains(m.getMazeName())) {
            mazeListModel.addElement(m.getMazeID());
            mazeData.saveMaze(m);
        }
        else {
            saveAsMaze(m);
        }
    }

    /**
     * Saves the maze and allows users to name the maze
     * @param m
     */
    public void saveAsMaze(Maze m) {
        if (!mazeListModel.contains(m.getMazeID())) {
            mazeListModel.addElement(m.getMazeID());
            mazeData.addMaze(m);
        }
        else {
            mazeData.saveMaze(m);
        }
    }

    public int getMazeCount() {
        return mazeListModel.getSize();
    }

    public void getTime() {
        mazeData.getTime();
    }

    public void persist() {
        mazeData.close();
    }

    public byte[] mazeSerialisation(Maze m) {
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        try (ObjectOutputStream os = new ObjectOutputStream(bs)) {
            os.writeObject(m);
            return bs.toByteArray();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        throw new RuntimeException();
    }

    public Object mazeDeserialisation(byte[] bytes) {
        try {
            return new ObjectInputStream(
                    new ByteArrayInputStream(bytes)).readObject();
        } catch (IOException | ClassNotFoundException ex) {
            throw new IllegalArgumentException(ex);
        }
    }
}
