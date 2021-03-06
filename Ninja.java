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

    private JButton start;
    private JLabel score, tempScore;
    private JLabel title, spacer,spacer2;

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
                    if (f.done()) {
                        synchronized (lock){
                            list.remove(i);
                        }
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
        title = new JLabel("JavaSwing Ninja");
        title.setFont(new Font("Verdana", Font.BOLD, 30));
        spacer = new JLabel("                    ");
        spacer2 = new JLabel("                    ");
        score = new 
        JLabel("Score: " + totalScore);
        start = new 
        JButton("Start");
        tempScore = new 
        JLabel("");
        score.setFont(new 
            Font("Verdana", Font.PLAIN, 18));
        tempScore.setFont(new 
            Font("Verdana", Font.PLAIN, 18));
        title.setForeground(new Color(255,215,0));
        panel.add(title);
        panel.add(spacer2);
        panel.add(score);

        panel.add(tempScore);
        panel.add(spacer);
        panel.add(start);

        frame.add(panel);   
        panel.addMouseListener(this);
        panel.addMouseMotionListener(this);
        start.addActionListener(this);
        time = new javax.swing.Timer(3000, this);

        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Method actionPerformed starts the game and creates fruit threads.
     *
     * @param e An action event.
     */
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == start) {
            time.start();
            synchronized (lock){
                list.clear();
            }
            start.setText("New game");
            tempScore.setText("");
            totalScore = 0;
            score.setText("Score: " + totalScore);
        }else{
            Fruit newFruit;
            int fruitID = r.nextInt(5);
            time.start();
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
            time.setDelay(r.nextInt(4000) + 500);
        }
        panel.repaint();
    }

    /**
    Mouse press event handler.

    @param e mouse event info
     */
    @Override
    public void mousePressed(MouseEvent e) {

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
        int i = 0;
        while (i < list.size()) {
            Fruit f = list.get(i);
            if(!f.sliced){
                if(mouseCollide(lastMouse, f.upperLeftX, f.upperLeftY, f)){
                    if(f instanceof Kiwi){
                        totalScore += 10;
                        tempScore.setForeground(Color.GREEN);
                        tempScore.setText("+10");
                    }else if(f instanceof Watermelon){
                        totalScore += 1;   
                        tempScore.setForeground(Color.GREEN);
                        tempScore.setText("+1");
                    }else if(f instanceof Orange){
                        totalScore += 7;   
                        tempScore.setForeground(Color.GREEN);
                        tempScore.setText("+7");
                    }else if(f instanceof Grapefruit){
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
            i++;
        }
    }

    /**
    Mouse release event handler.

    @param e mouse event info
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        lastMouse = null;
    }

    /**
     * Method mouseCollide detects for collision between mouse and fruit object.
     *
     * @param mousePosition A parameter
     * @param x x position of fruit.
     * @param y y position of fruit.
     * @param f The fruit to check collision for.
     * @return If the mouse collided with the fruit object.
     */
    public boolean mouseCollide(Point mousePosition, double x, double y, Fruit f){
        double centerX = x + (f.size / 2);
        double centerY = y + (f.size / 2);

        if(mousePosition != null){
            if(f instanceof Watermelon){
                //Needs an if statement for Watermelon because dimensions are not equal all around.
                centerY = y + (f.size / 3);
                if(Math.abs(mousePosition.x - centerX) < (f.size / 2) && Math.abs(mousePosition.y - centerY) < (f.size / 3)){
                    f.sliced = true;
                    return true;   
                }
            }else if(mousePosition.distance(new Point((int)centerX,(int)centerY)) < (f.size / 2)){
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
