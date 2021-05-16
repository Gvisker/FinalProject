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
    public Orange(JComponent container){
        super(-3, -17, container, 80, container.getWidth()/3 * 2);
    }

    /**
    Draw the orange at its current location.

    @param g the Graphics object on which the orange should be drawn
     */
    public void paint(Graphics g) {
        if(!sliced){
            g.setColor(Color.black);
            g.drawOval((int)upperLeftX, (int)upperLeftY, size/2, size/2);
            g.setColor(Color.orange);
            g.fillOval((int)upperLeftX, (int)upperLeftY, size/2, size/2);
        } else {
            g.setColor(Color.orange);
            g.fillArc((int)upperLeftX, (int)upperLeftY, size/2, size/2, 0, 180);
            g.fillArc((int)upperLeftX, (int)upperLeftY+5, size/2, size/2,180, 180);
        }

    }
}
