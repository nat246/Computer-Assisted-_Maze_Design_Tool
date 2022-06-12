package maze;

import javax.swing.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * /**
 * JUnit Testing for the basic functionality of Cell class.
 *  */

class MazeTest {

    private Maze mazeTest;

    @BeforeEach
    /**
     * Setting up the initial maze
     */
    void setUp() {
        mazeTest = new Maze(new int[]{0}, "user", false);
    }

    @Test
    void testInitCells() {
        /**
         * Initialises the cells
         */
        mazeTest.initCells();

        // Verify the results
    }

    @Test
    void testGetCell() {
        /**
         * Get the cells dimension
         */
        final Cell result = mazeTest.getCell(0, 0);
        assertEquals(0,result,"Initial Cells are 0x0");

    }

    @Test
    void testAddCell() {

        /**
         * Adds new cells
         */
        final Cell cell = new Cell(0, 0);

        // Run the test
      mazeTest.addCell(cell);


        // Verify the results
    }


}
