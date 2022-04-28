package Maze;

import java.util.ArrayList;

/**
 * Class responsible for handling the user input details including name and unique user ID
 */

public class User {
    private String name;
    private int userId;
    private ArrayList<Object> userMazes;

    /**
     * Gets the name of the User
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name if the User
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the user id of the user
     * @return userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the user id of the user
     * @param userId
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets all the mazes created by the user
     * @return userMazes
     */
    public ArrayList<Object> getUserMazes() {
        return userMazes;
    }

    /**
     * Adds the mazes to the user's profile
     * @param userMazes
     */
    public void addUserMazes(ArrayList<Object> userMazes) {
        this.userMazes = userMazes;
    }

    /**
     * gets the user's details
     * @return user details
     */
    private static String getUser() {

        return  "0";
    }
}
