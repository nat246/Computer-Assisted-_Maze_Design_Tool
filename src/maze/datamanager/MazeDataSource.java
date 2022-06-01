package maze.datamanager;

import maze.Maze;
import user.User;

import java.util.Set;

public interface MazeDataSource {
    /**
     * Adds user to list if not already in it
     * @param u User to add
     */
    void addUser(User u);

    /**
     * Gets the user's ID
     * @param u User ID to get
     */
    void getUserID(User u);

    /**
     * Gets the user's username
     * @param u Username to get
     */
    void getUsername(User u);

    /**
     * Gets the user's password
     * @param u Password to get
     */
    void getPassword(User u);

    /**
     * Deletes the user from the database
     * @param u User to delete
     */
    void deleteUser(User u);

    /**
     * Adds a maze to the database
     * @param m Maze to add
     */
    void addMaze(Maze m);

    /**
     * Gets the maze ID
     * @param m Maze ID to get
     */
    void getMazeID(Maze m);

    /**
     * Retrieves the maze from the database to allow users to edit it
     * @param m Gets the maze
     */
    void getMaze(Maze m);

    /**
     * Gets the name of the maze
     * @param m gets the maze name
     */
    void getMazeName(Maze m);

    /**
     * Gets the creator of the maze
     * @param m gets the maze creator
     */
    void getMazeCreator(Maze m);

    /**
     * Gets the date and time the maze was created
     * @param m gets creation date and time
     */
    void getMazeCreationDate(Maze m);

    /**
     * Gets the date and time the laze was last edited by a user
     * @param m gets date and time last edited
     */
    void getMazeLastEdited(Maze m);

    /**
     * Deletes the maze from the database
     * @param m Database to delete
     */
    void deleteMaze(Maze m);

    /**
     * Finalises any resources used by the data source and ensures data is persisted
     */
    void close();
}
