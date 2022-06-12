package maze.Test;

import javax.swing.*;

import maze.Cell;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 JUnit Testing for the basic functionality of Cell class
 */
class CellTest {

    private Cell cellTest;

    @BeforeEach
    void setUp() throws MazeException{
        /**
         * Initialises the dimensions before testing
         */
        cellTest = new Cell(2,4);
    }
//
    @Test
    public void testCellType()throws MazeException {
        /**
         * Checks for the Incorrect cell type
         */
        assertEquals("234", cellTest.getType(),"Incorrect cell type");
    }


    @Test
    void testCellSize()throws MazeException{
        /**
         * Checks for the size of the cell
         */
        assertEquals(1,cellTest.getSize(),"Default size is set at 1");
    }

    @Test
    void testWallPanel()throws MazeException{
        /**
         * Verifies the position of the wall panel
         */
        final JPanel result = cellTest.getWallPanel("pos");

    }
    @Test
    void testWallStatus()throws MazeException{
        /**
         * Checks for incorrect active wall status
         */
        assertEquals(0,cellTest.getWallsActive(),"The active wall status is 4");
    }
}
