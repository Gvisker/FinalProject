import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.Color;

/**
 * A representation of an apple.
 *
 * @author Grant Visker, John Hurley, Joseph Capper, and Logan Belak.
 * @version 5/7/2021
 */
public class Apple extends Fruit
{   
    public Apple(
    JComponent container){
        super(3, -16, container, 100, container.getWidth()/5);
    }

    public void paint(Graphics g){
        g.setColor(new Color(180, 0, 0));
        if(sliced){
            g.fillArc((int)upperLeftX, (int)upperLeftY, size/2, size/2, 0, 180);
            g.fillArc((int)upperLeftX, (int)upperLeftY + 10, size/2, size/2, 180, 180);
        }else{
            g.setColor(Color.black);
            g.drawOval((int)upperLeftX, (int)upperLeftY, size/2, size/2);
            g.setColor(new Color(180, 0, 0));
            g.fillOval((int)upperLeftX, (int)upperLeftY, size/2, size/2);
        }

    }

}
