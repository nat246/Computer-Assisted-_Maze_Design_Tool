package maze;

import javax.swing.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/*
JUnit Testing for the basic functionality of Cell class
 */
class CellTest {

    private Cell cellTest;

    @BeforeEach
    void setUp() {
        /**
         * Initialises the dimensions before testing
         */
        cellTest = new Cell(2,4);
    }

    @Test
    void testCellType(){
        /**
         * Checks for the cell type
         */
        assertEquals(0, cellTest.getType(),"Cell starts at 0");
    }


    @Test
    void testCellSize(){
        /**
         * Checks for the size of the cell
         */
        assertEquals(1,cellTest.getSize(),"Smallest cell size starts at 1");
    }

    @Test
    void testGetWallPanel() {
        /**
         * Verifies the position of the wall panel
         */
        final JPanel result = cellTest.getWallPanel("pos");

    }
}
