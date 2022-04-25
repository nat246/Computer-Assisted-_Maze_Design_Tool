package Maze;

import java.util.ArrayList;

public class User {
    private String name;
    private int userId;
    private ArrayList<Object> userMazes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public ArrayList<Object> getUserMazes() {
        return userMazes;
    }

    public void setUserMazes(ArrayList<Object> userMazes) {
        this.userMazes = userMazes;
    }

    /**
     *
     * @return user details
     */
    private static String getUser() {

        return  "0";
    }
}
