import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
/**
 * Write a description of class Grapefruit here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Grapefruit extends Fruit
{
    /**
     * Constructor for objects of class Grapefruit
     */
    public Grapefruit(JComponent container)
    {
        super(5, -14, container, 140, container.getWidth()/6);
    }

    /**
    Draw the Grapefruit at its current location.

    @param g the Graphics object on which the Grapefruit should be drawn
     */
    public void paint(Graphics g){
        if(!sliced){
            g.setColor(Color.black);
            g.drawOval((int)upperLeftX, (int)upperLeftY, size/2, size/2);
            g.setColor(Color.yellow);
            g.fillOval((int)upperLeftX, (int)upperLeftY, size/2, size/2);
        }else{
            g.setColor(Color.yellow);
            g.fillArc((int)upperLeftX, (int)upperLeftY, size/2, size/2, 0, 180);
            g.fillArc((int)upperLeftX, (int)upperLeftY+5, size/2, size/2,180, 180);
            g.setColor(Color.black);
        }

    }
}
