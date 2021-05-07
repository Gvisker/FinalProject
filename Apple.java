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
    public Apple(double xSpeed, double ySpeed,
    JComponent container, int size, double upperLeftX){
        super(0.5, 0, container, 15, container.getWidth()/3);
    }

    public void paint(Graphics g){
        g.setColor(Color.red);
        if(sliced){
            g.fillArc((int)upperLeftX, (int)upperLeftY, size/2, size/2, 0, 180);
            g.fillArc((int)upperLeftX, (int)upperLeftY + size, size/2, size/2, 180, -180);
        }else{
            g.fillOval((int)upperLeftX, (int)upperLeftY, size/2, size/2);
        }

    }

}
