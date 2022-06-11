package maze;

import javax.swing.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;



/**
 JUnit Testing for the basic functionality of Grid class
 */
class GridTest {

    private Grid gridTest;

    @BeforeEach
    /**
     * Initialises to the default grid dimensions of 1x1
     */
    void setUp() {
        gridTest = new Grid(1, 1) {
        };
    }

    @Test
    /**
     * Gets the initial position of the grid
     */
    void testGetPos() {
        assertEquals(List.of(1,1), gridTest.getPos(),"The initial position of the grid is set for 1 x 1");
    }
}
