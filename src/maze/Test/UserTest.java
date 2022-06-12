package maze.Test;

import maze.Test.MazeException;
import org.junit.jupiter.api.*;
import user.User;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    /**
     * Junit test class for verifying the user login details
     */

    private User userTest;

    @BeforeEach
    void setUp() throws MazeException {
        /**
         * Initialising a new user
         */

        userTest = new User();
    }

    @Test
    public void CorrectUserName() throws MazeException {
        /**
         * Verifies the username of the user
         */
        userTest.setName("Test user");
        assertEquals("Test user", userTest.getName());

    }

    @Test
    public void CorrectUserDetails() throws MazeException {
        /**
         * Verifies the correct user details
         */
        userTest.setPassword("Apple");
        assertEquals("Apple", userTest.getPassword(), "Password is incorrect");
    }

    @Test
    public void CorrectUserID() throws MazeException {
        /**
         * Verifies the correct user ID
         */
        userTest.setUserId(1234);
        assertEquals(1234, userTest.getUserId(), "User ID is incorrect");
    }

    @Test
    public void IncorrectUserName() throws MazeException {
        /**
         * Checks for incorrect user Details
         */
        assertEquals("Testuser", userTest.getName(), "User Name is incorrect");


    }

    @Test
    public void IncorrectUserPassword() throws MazeException {
        /**
         * Checks for incorrect user Details
         */
        assertEquals("apple12", userTest.getPassword(), "Password is incorrect");

    }

    @Test
    public void IncorrectUserID() throws MazeException {
        /**
         * Checks for incorrect user Details
         */
        assertEquals(4321, userTest.getUserId(), "User ID is incorrect");

    }
}
