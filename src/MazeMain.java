import maze.datamanager.MazeDataHandler;
import ui.MenuUI;
import user.User;

import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException;

public class MazeMain {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                initUI();
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                    | UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            }
        });
    }

    // Creates a new MazeUI Class that starts the User Interface.
    private static void initUI() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        //Placeholder user
        new MenuUI(new MazeDataHandler()).setVisible(true);
    }
}