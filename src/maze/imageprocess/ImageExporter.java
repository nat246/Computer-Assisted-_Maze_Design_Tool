package maze.imageprocess;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import java.io.*;

 /**
 * Class responsible for trying to export the maze in different formats (non-functional)
 */
public class ImageExporter implements ActionListener {
    private BufferedImage image;    // the rasterized image
    private JFrame frame;           // on-screen view

    // create a blank w-by-h image
    public ImageExporter(int w, int h) {
        image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        // set to TYPE_INT_ARGB to support transparency
    }

    // create an image by reading in the PNG, GIF, or JPEG from a filename
    public ImageExporter(String filename) {
        try { image = ImageIO.read(new File(filename)); }
        catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not open file: " + filename);
        }
        if (image == null)
            throw new RuntimeException("Invalid image file: " + filename);
    }

    // create an image by reading in the PNG, GIF, or JPEG from a file
    public ImageExporter(File file) {
        try { image = ImageIO.read(file); }
        catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not open file: " + file);
        }
        if (image == null)
            throw new RuntimeException("Invalid image file: " + file);
    }

    // to embed in a JPanel, JFrame or other GUI widget
    public JLabel getJLabel() {
        if (image == null) return null;         // no image available
        ImageIcon icon = new ImageIcon(image);
        return new JLabel(icon);
    }

    // view on-screen, creating new frame if necessary
    public void show() {

        // create the GUI for viewing the image if needed
        if (frame == null) {
            frame = new JFrame();

            JMenuBar menuBar = new JMenuBar();
            JMenu menu = new JMenu("File");
            menuBar.add(menu);
            JMenuItem menuItem1 = new JMenuItem(" Save...   ");
            menuItem1.addActionListener(this);
            menuItem1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                    Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
            menu.add(menuItem1);
            frame.setJMenuBar(menuBar);



            frame.setContentPane(getJLabel());
            // f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setTitle("Picture Frame");
            frame.setResizable(false);
            frame.pack();
            frame.setVisible(true);
        }

        // draw
        frame.repaint();
    }


    // accessor methods
    public int height() { return image.getHeight(null); }
    public int width()  { return image.getWidth(null);  }

    // return Color of pixel (i, j)
    public Color get(int i, int j) {
        return new Color(image.getRGB(i, j));
    }

    // change color of pixel (i, j) to c
    public void set(int i, int j, Color c) {
        image.setRGB(i, j, c.getRGB());
    }

    // save to given filename - suffix must be png, jpg, or gif
    public void save(String filename) { save(new File(filename)); }

    // save to given filename - suffix must be png, jpg, or gif
    public void save(File file) {
        String filename = file.getName();
        String suffix = filename.substring(filename.lastIndexOf('.') + 1);
        suffix = suffix.toLowerCase();
        if (suffix.equals("jpg") || suffix.equals("png")) {
            try { ImageIO.write(image, suffix, file); }
            catch (IOException e) { e.printStackTrace(); }
        }
        else {
            System.out.println("Error: filename must end in .jpg or .png");
        }
    }

    // open a save dialog when the user selects "Save As" from the menu
    public void actionPerformed(ActionEvent e) {
        FileDialog chooser = new FileDialog(frame,
                "Use a .png or .jpg extension", FileDialog.SAVE);
        chooser.setVisible(true);
        String filename = chooser.getFile();
        if (filename != null) {
            save(chooser.getDirectory() + File.separator + chooser.getFile());
        }
    }



    // test client: read in input file and display
    public static void main(String[] args) {
       new ImageExporter(args[2]);
       // pic.show();
    }

}
