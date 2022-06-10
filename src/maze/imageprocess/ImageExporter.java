package maze.imageprocess;
/**
 * Class responsible for exporting the maze in different formats
 */

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ImageExporter extends JPanel
{

    public ImageExporter()
    {
        JFrame frame = new JFrame("TheFrame");
        frame.add(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,400);
        frame.setVisible(true);

        try
        {
            BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics2D = image.createGraphics();
            frame.paint(graphics2D);
            ImageIO.write(image,"jpeg", new File(System.getProperty("user.home")));
        }
        catch(Exception exception)
        {
            //code
        }
    }

    protected void paintComponent(Graphics g)
    {
        g.drawRect(50,50,50,50);
    }

    public static void main(String[] args)
    {
        new ImageExporter();
       // new ChooseLogo();
    }
}
