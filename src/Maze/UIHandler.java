package Maze;

import java.awt.*;
import javax.swing.*;


public final class UIHandler {

    public static void SwitchCard(CardLayout card, JPanel panel, String panelName) {
        card.show(panel, panelName);
    }

    public static EditorUI CreateEditor(String user) {
        return new EditorUI(user);
    }


    
    public static Component NewGridItem(Component component, GridBagLayout layout, GridBagConstraints gridBag, int gridx, int gridy, int weightx, int weighty) {
        /*
        Sourced from StackOverflow (with slight changes):
        https://stackoverflow.com/questions/30656473/how-to-use-gridbaglayout 
        */
        
        gridBag.gridx = gridx;
        gridBag.gridy = gridy;

        layout.setConstraints(component, gridBag);
        
        return component;
    }
    
}
