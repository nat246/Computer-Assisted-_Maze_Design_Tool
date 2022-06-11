package maze;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
