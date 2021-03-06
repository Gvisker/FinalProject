import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * An abstract representation of a fruit to be tossed and sliced.
 *
 * @author Grant Visker, John Hurley, Joseph Capper, and Logan Belak.
 * @version 5/7/2021
 */
public abstract class Fruit extends Thread
{
    // delay time between frames of animation (ms)
    public static final int DELAY_TIME = 33;

    // what to add to ySpeed to simulate gravity?
    public static final double GRAVITY = 0.25;

    protected double xSpeed, ySpeed;

    protected boolean done;

    protected JComponent container;

    protected int size;

    protected double upperLeftX, upperLeftY;

    protected boolean sliced;

    /**
    Construct a new Fruit object.

    @param xSpeed initial x speed, pixels per second
    @param ySpeed initial y speed, pixels per second
    @param container the Swing component in which this fruit is being
    drawn to allow it to call that component's repaint method
     */
    public Fruit(double xSpeed, double ySpeed,
    JComponent container, int size, double upperLeftX) {
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.container = container;
        this.upperLeftX = upperLeftX;
        upperLeftY = container.getHeight();
        this.size = size;
        sliced = false;
    }

    /**
    Draw the fruit at its current location.

    @param g the Graphics object on which the fruit should be drawn
     */
    public abstract void paint(Graphics g);

    /**
    This object's run method, which manages the life of the fruit as it
    is tossed on the screen.
     */
    @Override
    public void run() {

        while (!done) {

            try {
                sleep(DELAY_TIME);
            }
            catch (InterruptedException e) {
            }

            // every iteration, update the coordinates
            // by a pixel
            upperLeftX += xSpeed;
            upperLeftY += ySpeed;

            // gravity factor also
            ySpeed += GRAVITY;

            container.repaint();
        }
    }

    /**
    Return whether the fruit has completed its fall to the bottom.

    @return whether the fruit has completed its fall to the bottom
     */
    public boolean done() {
        return done;
    }
}
