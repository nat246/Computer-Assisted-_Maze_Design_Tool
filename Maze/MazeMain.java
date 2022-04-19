package Maze;

import javax.swing.UnsupportedLookAndFeelException;

public class MazeMain {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        initUI();
    }

    // Creates a new MazeUI Class that starts the User Interface.
    private static void initUI() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        new MenuUI().setVisible(true);
    }
}