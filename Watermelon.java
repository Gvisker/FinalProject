import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.Color;

/**
 * A representation of a watermelon.
 *
 * @author Grant Visker, John Hurley, Joseph Capper, and Logan Belak.
 * @version 5/20/2021
 */
public class Watermelon extends Fruit
{   
    public Watermelon(
    JComponent container){
        super(0, -15, container, 200, container.getWidth()/2 - 100);
    }

    public void paint(Graphics g){
        g.setColor(new Color(0, 130, 0));
        if(sliced){
            g.fillArc((int)upperLeftX, (int)upperLeftY, size/2, size/3, 0, 180);
            g.fillArc((int)upperLeftX, (int)upperLeftY + 10, size/2, size/3, 180, 180);
        }else{
            g.setColor(Color.black);
            g.drawOval((int)upperLeftX, (int)upperLeftY, size/2, size/3);
            g.setColor(new Color(0, 130, 0));
            g.fillOval((int)upperLeftX, (int)upperLeftY, size/2, size/3);
        }

    }
}
