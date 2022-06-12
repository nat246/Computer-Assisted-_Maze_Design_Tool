package ui;

import maze.Test.MazeException;
import maze.datamanager.MazeDataHandler;
import org.junit.jupiter.api.*;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;


class MenuUITest {
    /**
     * Unit Testing for menu UI parameters for checking the boundary conditions of the maze size exceeding 100x100
     */

    private MazeDataHandler mockData;

    private MenuUI menuUIUnderTest;

    @BeforeEach
    void setUp() throws Exception {

        menuUIUnderTest = new MenuUI(mockData);
    }

    @Test
    void SetIncorrectSize(){
        /**
         * Throws an Error when the maze Dimensions exceed 100x100
         */
        assertThrows(MazeException.class, () ->{
            menuUIUnderTest.setPreferredSize(new Dimension(120,140));
            menuUIUnderTest.getPreferredSize();

        });
    }
}
