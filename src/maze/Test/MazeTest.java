package maze.Test;

import maze.Maze;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 JUnit Testing for the basic functionality of Maze class
 */
class MazeTest {

    private Maze mazeTest;

    @BeforeEach
   public void setUpMaze()throws MazeException{
        /**
         * Initialises the maze parameters for checking
         */
        mazeTest = new Maze(new int[]{4,4},"user",false);
    }


    @Test
    public void checkAuthor() throws MazeException{
        /**
         * Checks if the Author name of the maze
         */
        mazeTest.setAuthorName("User");
        assertEquals("User",mazeTest.getAuthorName(),"The default Author name is user");
    }
    @Test
    public void checkMazeId() throws MazeException{
        /**
         * Checks for the Maze ID
         */
        mazeTest.setMazeID(1234);
        assertEquals(1234,mazeTest.getMazeID(),"The maze ID is 1234");
    }

    @Test
    public void checkDateCreated() throws MazeException{
        /**
         * Checks the date of creation of the Maze
         */
        mazeTest.setDateCreated("12/06/2022");
        assertEquals("12/06/2022",mazeTest.getDateCreated(),"The Date of creation of the maze is wrong");
    }

    @Test
    public void checkDateEdited() throws MazeException{
        /**
         * Checks the last date of edit of the following Maze
         */
        mazeTest.setLastEdited("13/06/2022");
        assertEquals("13/06/2022",mazeTest.getLastEdited(),"The Date of creation of the maze is wrong");
    }

    @Test
    public void isSolvable() throws MazeException{
        /**
         * Checks if the following maze is solvable or not
         */
        mazeTest.setSolvable(true);
        assertEquals(true,mazeTest.getSolvable(),"The Date of creation of the maze is wrong");
    }

    @Test
    public void MazeName()throws MazeException{
        /**
         * Verifies the name of the Maze
         */
        mazeTest.setMazeName("Basic Maze");
        assertEquals("Basic Maze",mazeTest.getMazeName(),"The name of the maze is Basic Maze");
    }

    @Test
    public void startPos()throws MazeException{
        /**
         * Checks the default starting position of the maze at 0x0
         */
        assertEquals(List.of(0,0),mazeTest.getStartPos(),"the default starting position of the maze is 0");
    }
    @Test
    public void CheckMode() throws Exception{
        /**
         * Verifies if the starting mode of the maze is at 0
         */

        assertEquals(0,mazeTest.getMode(),"");
    }
    @Test
    public void CheckDeadEnd() throws Exception{
        /**
         * Checks the status of active walls and verifies if it is a dead end when the default maze is at 0
         */
        assertEquals(0,mazeTest.getDeadEnds());
    }

}