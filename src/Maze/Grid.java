package Maze;

public abstract class Grid {
    private int x, y;
    private int size;

    public Grid(int x, int y) {
        // Set Default Size (1 x 1)
        this.size = 1;

        // Position
        this.x = x;
        this.y = y;
    }

    // Gets the Position of the Grid
    public int[] getPos() {
        int[] position =  {x, y};
        return position;
    }

    // Sets the size of the Grid
    public void setSize(int size) {
        this.size = size;
    }

    // Gets the size of the Grid
    public int getSize() {
        return size;
    }

}

