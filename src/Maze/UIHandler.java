package Maze;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;

import javax.swing.*;


public final class UIHandler {


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
