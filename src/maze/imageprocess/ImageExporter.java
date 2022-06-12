package maze.imageprocess;
/**
 * Class responsible for exporting the maze in different formats
 */

import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;


public class ImageExporter extends JPanel {
    JLabel jLabel1;
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt){
        try
        {
            Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage capture = new Robot().createScreenCapture(screenRect);
            ImageIcon icon  = new ImageIcon(capture);
            jLabel1.setIcon(icon);

            ImageIO.write(capture,"png",new File("C:\\Users\\MyPC\\Desktop\\screenShot.png"));

        }
        catch (Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    public static void main(String[] args){new ImageExporter();}
}