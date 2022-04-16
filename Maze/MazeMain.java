package Maze;


public class MazeMain {

    public static void main(String[] args) {
        initUI();
    }

    // Creates a new MazeUI Class that starts the User Interface.
    private static void initUI() {
        new MazeUI().setVisible(true);
    }
}