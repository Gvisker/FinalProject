import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * Lab 8 Vanishing Lines animation. used from lab 8.
 * 
 * @author Grant Visker and Jordan Breen
 * @Version Fall 2021
 */
class VanishingLine extends AnimatedLine {
    private int fadeSpeed = 25;

    private Color currColor = Color.black;

    public VanishingLine(Point startPoint, Point endPoint, JComponent container) 
    {
        super(startPoint, endPoint, container);
    }

    @Override
    public void paint(Graphics g) 
    {
        if(!done)
        {
            g.setColor(this.currColor);
            g.drawLine(location[0].x, location[0].y, location[1].x, location[1].y);
        }
    }

    @Override
    public void run() 
    {
        try 
        {
            sleep(30);
        }
        catch (InterruptedException e) 
        {}
        while (currColor.getRed()   + fadeSpeed <= 255 &&
        currColor.getGreen() + fadeSpeed <= 255 &&
        currColor.getBlue()  + fadeSpeed <= 255) 
        {

            try 
            {
                sleep(DELAY_TIME);
            }
            catch (InterruptedException e) 
            {}

            currColor = new Color(currColor.getRed()   + fadeSpeed, 
                currColor.getGreen() + fadeSpeed,
                currColor.getBlue()  + fadeSpeed);

            container.repaint();
        }

        done = true;
    }

}
