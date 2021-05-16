import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * Lab 8 Lines animation. used from Lab 8
 * 
 * @author Grant Visker and Jordan Breen
 * @Version Fall 2021
 */
abstract class AnimatedLine extends Thread {

    public static final int DELAY_TIME = 33;

    protected Point[] location;

    protected int bottom;

    protected boolean done;

    protected JComponent container;

    public AnimatedLine(Point startPoint, Point endPoint, JComponent container) 
    {
        location = new Point[2];
        location[0] = new Point(startPoint);
        location[1] = new Point(endPoint);
        this.bottom = container.getHeight();
        this.container = container;
    }

    public abstract void paint(Graphics g); 
    
    @Override
    public abstract void run();

    public boolean done() 
    {
        return done;
    }
}
