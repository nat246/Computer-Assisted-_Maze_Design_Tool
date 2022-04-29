package user;

/**
 * Class responsible for handling the user input details including name and unique user ID
 */

public class User {
    private String name;
    private int userId;

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
     * gets the user's details
     * @return user details
     */
    private static String getUser() {

        return  "0";
    }
}
