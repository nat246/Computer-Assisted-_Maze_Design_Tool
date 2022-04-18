package Maze;

import java.awt.CardLayout;

import javax.swing.*;


public final class UIHandler {

    public static void SwitchCard(CardLayout card, JPanel panel, String panelName) {
        card.show(panel, panelName);
    }
    
}
