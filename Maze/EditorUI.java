package Maze;

import java.awt.Dimension;
import java.awt.Component;
import java.awt.event.*;

import javax.swing.*;

public class EditorUI extends JFrame {
    private String user; // Change to User Class after Database and User Class has been created

    public EditorUI(String user) {
        super("Editor");
        
        this.user = user;
        initEditor();
    }

    private void initEditor() {
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setPreferredSize(new Dimension(1000, 900));
        pack();

        setLocationRelativeTo(null);
    }

    

    
    
}
