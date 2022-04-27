package Maze;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;

import javax.swing.*;

/**
 * Handles the UI class and creates a new wall for the maze
 */

public final class UIHandler {

    /**
     *
     * @param size user input for creating a new wall
     * @return newWall returns the wall created using the user input size
     */
    // Import to Cell Object soon
    public static JPanel CreateWall(Dimension size) {
        JPanel newWall = new JPanel();

        newWall.setPreferredSize(size);
        newWall.setBackground(Color.BLACK);

        newWall.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                newWall.setBackground(Color.ORANGE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                newWall.setBackground(Color.BLACK);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                // TO DO
            }
            
            
        });

        return newWall;
    }

    /**
     *
     * @param component creates a new component
     * @param layout arranges the layout of the grid
     * @param gridBag Aligns the layout of the
     * @param gridx row input values of a grid
     * @param gridy column input values of a grid
     * @param weightx
     * @param weighty
     * @return
     */
    
    public static Component NewGridItem(Component component, GridBagLayout layout, GridBagConstraints gridBag, int gridx, int gridy, int weightx, int weighty) {
        /*
        Sourced from StackOverflow (with slight changes):
        https://stackoverflow.com/questions/30656473/how-to-use-gridbaglayout 
        */
        
        gridBag.gridx = gridx;
        gridBag.gridy = gridy;

        gridBag.weightx = weightx;
        gridBag.weighty = weighty;

        layout.setConstraints(component, gridBag);
        
        return component;
    }
    
}
