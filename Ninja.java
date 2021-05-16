import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * Fruit Ninja
 *
 * @author Grant Visker, John Hurley, Joseph Capper, and Logan Belak.
 * @version 5/7/2021
 */
public class Ninja extends MouseAdapter implements Runnable {

    // multiplier to convert the press/release distances to initial
    // speeds in the x and y directions
    public static final double SLING_FACTOR = 0.25;

    //list of Fruit objects currently on the screen
    private java.util.List<Fruit> list;

    private JPanel panel;
    private JPanel gamePanel;

    private JButton start;
    private JLabel score;
    private JLabel title;

    private Point pressPoint;

    /**
    The run method to set up the graphical user interface
     */
    @Override
    public void run() {

        JFrame.setDefaultLookAndFeelDecorated(true);

        JFrame frame = new JFrame("Swing Ninja");
        frame.setPreferredSize(new Dimension(800,800));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {

                super.paintComponent(g);

                int i = 0;
                while (i < list.size()) {
                    Fruit f = list.get(i);
                    if (f.done()) {
                        list.remove(i);
                    }
                    else {
                        f.paint(g);
                        i++;
                    }
                }
            }
        };

        gamePanel = new JPanel();

        frame.add(panel);
        panel.addMouseListener(this);
        panel.addMouseMotionListener(this);

        // construct the list
        list = new ArrayList<Fruit>();


        frame.pack();
        frame.setVisible(true);
    }

    /**
    Mouse press event handler.

    @param e mouse event info
     */
    @Override
    public void mousePressed(MouseEvent e) {
        Fruit newFruit = new Apple(panel);
        Fruit newFruit2 = new Orange(panel);
        Fruit newFruit3 = new Kiwi(panel);
        Fruit newFruit4 = new Grapefruit(panel);
        Fruit newFruit5 = new Watermelon(panel);
        list.add(newFruit);
        list.add(newFruit2);
        list.add(newFruit3);
        list.add(newFruit4);
        list.add(newFruit5);
        newFruit.start();
        newFruit2.start();
        newFruit3.start();
        newFruit4.start();
        newFruit5.start();
        panel.repaint();
    }

    /**
    Mouse drag event handler.
    @param e mouse event info
     */
    @Override
    public void mouseDragged(MouseEvent e) {
    }

    /**
    Mouse release event handler.

    @param e mouse event info
     */
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    public static void main(String args[]) {
        javax.swing.SwingUtilities.invokeLater(new Ninja());
    }
}
