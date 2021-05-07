import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.Color;

/**
 * Write a description of class Kiwi here.
 *
 * @author Grant Visker, John Hurley, Joseph Capper, and Logan Belak.
 * @version 5/7/2021
 */
public class Kiwi extends Fruit
{
    private int size = 25;
    private int upperLeftX = 0;
    private int upperLeftY = 500;

    public Kiwi(double xSpeed, double ySpeed, JComponent container){
        super(xSpeed, ySpeed, container);

    }

    public void paint(Graphics g){
        g.drawOval(upperLeftX, upperLeftY, size/2, size/2);
        g.setColor(Color.green);
        g.fillOval(upperLeftX, upperLeftY, size/2, size/2);
        g.setColor(Color.black);

    }

    public void slice(){

    }

    public void toss(){

    }
}
