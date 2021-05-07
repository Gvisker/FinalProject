import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.Color;

/**
 * This class will draw a Kiwi on the graphics screen and have it's abstract class determine
 * what will happen to it.
 *
 * @author Grant Visker, John Hurley, Joseph Capper, and Logan Belak.
 * @version 5/7/2021
 */
public class Kiwi extends Fruit
{
    private final int SIZE = 25;
    private int upperLeftX, upperLeftY;

    public Kiwi(double xSpeed, double ySpeed, JComponent container, int size, double upperLeftX){
        super(1, ySpeed, container, 25, 0);

    }

    public void paint(Graphics g){
        if(!true){
            g.drawOval(upperLeftX, upperLeftY, size/2, size/2);
            g.setColor(Color.green);
            g.fillOval(upperLeftX, upperLeftY, size/2, size/2);
            g.setColor(Color.black);
        }else{
            g.fillArc(upperLeftX, upperLeftY, size/2, size/2, 0, 0);
            g.setColor(Color.green);
            g.fillArc(upperLeftX, upperLeftY, size/2, size/2, 0, 0);
            g.setColor(Color.black);
        }

    }
}
