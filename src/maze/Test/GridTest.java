package maze.Test;


import maze.Grid;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

/**
 JUnit Testing for the basic functionality of Grid class.
 */
class GridTest {

    private Grid gridTest;
//
    @BeforeEach
    /**
     * Initialises to the default grid dimensions of 1x1
     */
    void setUpGrid() throws MazeException {
        gridTest = new Grid(1, 1) {
        };
    }

    @Test
    /**
     * Checks for the initial position of the maze which is set at 1x1
     */
    void testGetPos() throws MazeException {
        assertEquals(List.of(1,1), gridTest.getPos(),"The initial position of the grid is set for 1 x 1");
    }

    @Test
    /**
     * Checks for wrong size of the grid when the original size of maze is 1x1 = 1
     */
    void WrongSize() {
       assertEquals(10,gridTest.getSize(),"Size of the maze is incorrect");

    }
}
