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

    public Kiwi(JComponent container){
        super(3, -18, container, 50, 0);

    }

    public void paint(Graphics g){
        if(!sliced){
            g.setColor(Color.black);
            g.drawOval((int)upperLeftX, (int)upperLeftY, size/2, size/2);
            g.setColor(new Color(205, 133, 63));
            g.fillOval((int)upperLeftX, (int)upperLeftY, size/2, size/2);
            g.setColor(Color.black);
        }else{
            g.setColor(new Color(205, 133, 63));
            g.fillArc((int)upperLeftX, (int)upperLeftY, size/2, size/2, 0, 180);
            g.fillArc((int)upperLeftX, (int)upperLeftY+5, size/2, size/2,180, 180);
            g.setColor(Color.black);
        }

    }
}
