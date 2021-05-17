import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * Fruit Ninja
 *
 * @author Grant Visker, John Hurley, Joseph Capper, and Logan Belak.
 * @version 5/7/2021
 */
public class Ninja extends MouseAdapter implements Runnable, ActionListener {

    // multiplier to convert the press/release distances to initial
    // speeds in the x and y directions
    public static final double SLING_FACTOR = 0.25;

    //list of Fruit objects currently on the screen
    private java.util.List<Fruit> list = new ArrayList<Fruit>();

    private JPanel panel;
    private JPanel gamePanel;

    private JButton start;
    private JLabel score, tempScore;
    private JLabel title;

    private final int KIWI_SIZE = 50;
    private final int WATERMELON_SIZE = 200;
    private final int ORANGE_SIZE = 80;
    private final int GRAPEFRUIT_SIZE = 140;
    private final int APPLE_SIZE = 100;

    private ArrayList<AnimatedLine> lines = new ArrayList<>();
    private Point lastMouse;
    private Point pressPoint;
    protected javax.swing.Timer time;
    private Random r = new Random();
    private Object lock = new Object();
    private int totalScore = 0;
    /**
    The run method to set up the graphical user interface
     */
    @Override
    public void run() {

        JFrame.setDefaultLookAndFeelDecorated(true);

        JFrame frame = new JFrame("Swing Ninja");
        frame.setPreferredSize(new Dimension(800,800));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {

                super.paintComponent(g);

                int i = 0;

                while (i < list.size()) {
                    Fruit f = list.get(i);

                    if(!(f.sliced)){
                        if(mouseCollide(lastMouse, f.upperLeftX, f.upperLeftY, f)){
                            if(f.size == KIWI_SIZE){
                                totalScore += 10;
                                tempScore.setForeground(Color.GREEN);
                                tempScore.setText("+10");
                            }else if(f.size == WATERMELON_SIZE){
                                totalScore += 1;   
                                tempScore.setForeground(Color.GREEN);
                                tempScore.setText("+1");
                            }else if(f.size == ORANGE_SIZE){
                                totalScore += 7;   
                                tempScore.setForeground(Color.GREEN);
                                tempScore.setText("+7");
                            }else if(f.size == GRAPEFRUIT_SIZE){
                                totalScore += 3;   
                                tempScore.setForeground(Color.GREEN);
                                tempScore.setText("+3");
                            }else{
                                totalScore += 5; 
                                tempScore.setForeground(Color.GREEN);
                                tempScore.setText("+5");
                            }
                            score.setText("Score: " + totalScore);
                        }
                    }

                    if (f.done()) {
                        list.remove(i);
                    }
                    else {
                        f.paint(g);
                        i++;
                    }
                }
                for (AnimatedLine l : lines) {
                    l.paint(g);
                }
            }
        };

        gamePanel = new JPanel();
        score = new JLabel("Score: " + totalScore);
        tempScore = new JLabel("");
        score.setFont(new Font("Verdana", Font.PLAIN, 18));
        tempScore.setFont(new Font("Verdana", Font.PLAIN, 18));
        panel.add(score);
        panel.add(tempScore);

        frame.add(panel);   
        panel.addMouseListener(this);
        panel.addMouseMotionListener(this);
        time = new javax.swing.Timer(3000, this);
        time.start();

        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        Fruit newFruit;
        int fruitID = r.nextInt(5);
        if(fruitID == 0){
            newFruit = new Apple(panel);
        }else if(fruitID == 1){ 
            newFruit = new Orange(panel);
        }else if(fruitID == 2){ 
            newFruit = new Kiwi(panel);
        }else if(fruitID == 3){ 
            newFruit = new Grapefruit(panel);
        }else{ 
            newFruit = new Watermelon(panel);
        }
        synchronized (lock){
            list.add(newFruit);
        }
        newFruit.start();
        panel.repaint();
    }

    /**
    Mouse press event handler.

    @param e mouse event info
     */
    @Override
    public void mousePressed(MouseEvent e) {
        // Fruit newFruit = new Apple(panel);
        // Fruit newFruit2 = new Orange(panel);
        // Fruit newFruit3 = new Kiwi(panel);
        // Fruit newFruit4 = new Grapefruit(panel);
        // Fruit newFruit5 = new Watermelon(panel);
        // list.add(newFruit);
        // list.add(newFruit2);
        // list.add(newFruit3);
        // list.add(newFruit4);
        // list.add(newFruit5);
        // newFruit.start();
        // newFruit2.start();
        // newFruit3.start();
        // newFruit4.start();
        // newFruit5.start();
        // panel.repaint();
        lastMouse = e.getPoint();
    }

    /**
    Mouse drag event handler.
    @param e mouse event info
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        Point p[] = new Point[2];
        p[0] = lastMouse;
        lastMouse = e.getPoint();
        p[1] = lastMouse;
        VanishingLine newLine = new VanishingLine(p[0], p[1], panel);
        lines.add(newLine);
        newLine.start();
        panel.repaint();
    }

    /**
    Mouse release event handler.

    @param e mouse event info
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        lastMouse = null;
    }

    public boolean mouseCollide(Point mousePosition, double x, double y, Fruit f){

        double centerX = x + (f.size / 2);
        double centerY = y + (f.size / 2);

        if(!(mousePosition == null)){
            if(f.size == 200){
                //Needs an if statement for Watermelon because dimensions are not equal all around.
                //if(){
                //  f.sliced = true;
                //return true;   
                //}
            }

            if(mousePosition.distance(new Point((int)centerX,(int)centerY)) <= (f.size / 2)){
                f.sliced = true;
                return true;   
            }

        }

        return false;

    }

    public static void main(String args[]) {
        javax.swing.SwingUtilities.invokeLater(new Ninja());
    }
}
