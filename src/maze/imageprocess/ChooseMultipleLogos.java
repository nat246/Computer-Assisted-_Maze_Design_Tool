package maze.imageprocess;
import java.awt.*;
import javax.swing.*;
public class ChooseMultipleLogos extends JPanel
{
    //Choosing multiple images as stored on the desktop with the filenames
    public void paint(Graphics g){
        Image img1 = Toolkit.getDefaultToolkit().getImage("img/user1.png");
        g.drawImage(img1, 10, 10, this);

        Image img2 = Toolkit.getDefaultToolkit().getImage("img/user2.png");
        g.drawImage(img2, 70, 8, this);

        Image img3 = Toolkit.getDefaultToolkit().getImage("img/user4.png");
        g.drawImage(img3, 130, 15, this);

        Image img4 = Toolkit.getDefaultToolkit().getImage("img/user3.png");
        g.drawImage(img4, 190, 20, this);
    }

    public static void main(String[] args){
        JFrame f = new JFrame("Display multiple logos");
        f.getContentPane().add(new ChooseMultipleLogos());
        f.setSize(250, 100);
        f.setVisible(true);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}