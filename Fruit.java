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

    public int size;

    // delay time between frames of animation (ms)
    public static final int DELAY_TIME = 33;

    // what to add to ySpeed to simulate gravity?
    public static final double GRAVITY = 0.3;

    private double xSpeed, ySpeed;

    private double upperLeftX, upperLeftY;

    private boolean done;

    private JComponent container;

    /**
    Construct a new Fruit object.


    @param xSpeed initial x speed, pixels per second
    @param ySpeed initial y speed, pixels per second
    @param container the Swing component in which this ball is being
    drawn to allow it to call that component's repaint method
     */
    public Fruit(double xSpeed, double ySpeed,
    JComponent container) {
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.container = container;
    }

    /**
    Draw the ball at its current location.

    @param g the Graphics object on which the ball should be drawn
     */
    public abstract void paint(Graphics g);
    
    public abstract void toss();
    
    public abstract void slice();

    /**
    This object's run method, which manages the life of the ball as it
    bounces around the screen.
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
    Return whether the ball has completed its fall to the bottom.

    @return whether the ball has completed its fall to the bottom
     */
    public boolean done() {

        return done;
    }
}
