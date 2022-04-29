package maze;

/**
 * Abstract class used for creating cells and walls of the maze
 */

public abstract class Grid {
    private int x, y;
    private int size;

    /**
     *
     * @param x dimension 1 input for the maze
     * @param y dimension 2 input for the maze
     */
    public Grid(int x, int y) {
        // Set Default Size (1 x 1)
        this.size = 1;

        // Position
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the Position of the Grid
     * @return position the current postion of the grid on the maze
     */
    public int[] getPos() {
        int[] position =  {x, y};
        return position;
    }

    /**
     * Sets the size of the Grid
     * @param size sets the size of the grid according to the user input
     */
    public void setSize(int size) {
        this.size = size;
    }


    /**
     * Gets the size of the Grid
     * @return size
     */
    public int getSize() {
        return size;
    }

}

