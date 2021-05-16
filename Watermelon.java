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
public class Watermelon extends Fruit
{   
    public Watermelon(
    JComponent container){
        super(0, -15, container, 200, container.getWidth()/2);
    }

    public void paint(Graphics g){
        g.setColor(new Color(0, 130, 0));
        if(sliced){
            g.fillArc((int)upperLeftX, (int)upperLeftY, size/2, size/3, 0, 180);
            g.fillArc((int)upperLeftX, (int)upperLeftY + 10, size/2, size/3, 180, 180);
        }else{
            g.fillOval((int)upperLeftX, (int)upperLeftY, size/2, size/3);
        }

    }

}
