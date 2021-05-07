import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * Class responsible for drawing the orange.
 *
 * @author Grant Visker, John Hurley, Joseph Capper, and Logan Belak.
 * @version 5/7/2021
 */
public class Orange extends Fruit
{
    private int size = 25;
    private int upperLeftX;

    public Orange(double xSpeed, double ySpeed, JComponent container, int size, double upperLeftX){
        super(1.5, 0, container, 15, container.getWidth()/3);
    }

    /**
    Draw the orange at its current location.

    @param g the Graphics object on which the orange should be drawn
     */
    public void paint(Graphics g) {
        if(sliced){
            g.fillArc((int)upperLeftX, (int)upperLeftY, size/2, size/2, 0, 180);
            g.setColor(Color.orange);
            g.fillArc((int)upperLeftX, (int)upperLeftY - size/2, size/2, size/2, 180, -180);
            g.setColor(Color.black);
        } else {
            g.drawOval((int)upperLeftX, (int)upperLeftY, size/2, size/2);
            g.setColor(Color.orange);
            g.fillOval((int)upperLeftX, (int)upperLeftY, size/2, size/2);
            g.setColor(Color.black);
        }

    }
}
