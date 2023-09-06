import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GAME_FIELD extends JPanel implements ActionListener {
    private final int SIZE = 320;
    private final int DOT_SIZE = 16;
    private final int ALL_DOTS = 400;
    private Image dot;
    private Image apple;
    private int appleX;
    private int appleY;
    private int[] x = new int[ALL_DOTS];
    private int[] y = new int[ALL_DOTS];
    private int dots;
    private Timer timer;
    private boolean LEFT = false;
    private boolean RIGHT = true;
    private boolean UP = false;
    private boolean DOWN = false;
    private boolean IN_GAME = true;


    public GAME_FIELD() {
        setBackground(Color.blue);
        loadImages();
        inItGame();
        addKeyListener(new FieldKeyListener());
        setFocusable(true);

    }

    public void inItGame() {
        dots = 3;
        for (int i = 0; i < dots; i++) {
            x[i] = 48 - i * DOT_SIZE;
            y[i] = 48;

        }
        timer = new Timer(250, this);
        timer.start();
        createApple();
    }

    public void createApple() {
        appleX = new Random().nextInt(20) * DOT_SIZE;
        appleY = new Random().nextInt(20) * DOT_SIZE;


    }

    public void loadImages() {
        ImageIcon iia = new ImageIcon("apple2.jpg");
        apple = iia.getImage();
        ImageIcon iid = new ImageIcon("snake.jpg");
        dot = iid.getImage();


    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (IN_GAME) {
            g.drawImage(apple, appleX, appleY, this);
            for (int i = 0; i < dots; i++) {
                g.drawImage(dot, x[i], y[i], this);


            }
        }else{
            String str = "ВЫ ПРОИГРАЛИ";
            //Font f = new Font("Arial",100,Font. BOLD);
            g.setColor(Color.red);
            //g.setFont(f);
            g.drawString(str,80,SIZE/2);
        }
    }

    public void move() {
        for (int i = dots; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];


        }
        if (LEFT) {
            x[0] -= DOT_SIZE;
        }
        if (RIGHT) {
            x[0] += DOT_SIZE;
        }
        if (UP) {
            y[0] -= DOT_SIZE;
        }
        if (DOWN) {
            y[0] += DOT_SIZE;
        }
    }

    public void checkApple() {
        if (x[0] == appleX && y[0] == appleY) {
            dots++;
            createApple();
        }
    }

    public void checkCollisions() {
        for (int i = dots; i > 0; i--) {
            if (i > 4 && x[0] == x[i] && y[0] == y[i]) {
                IN_GAME = false;
            }


        }
        if (x[0] > SIZE) {
            IN_GAME = false;
        }
        if (x[0] < 0) {
            IN_GAME = false;
        }
        if (y[0] > SIZE) {
            IN_GAME = false;
        }
        if (y[0] < 0) {
            IN_GAME = false;
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (IN_GAME) {
            checkApple();
            checkCollisions();
            move();

        }
        repaint();

    }

    class FieldKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_LEFT && !RIGHT) {
                LEFT = true;
                UP = false;
                DOWN = false;

            }
            if (key == KeyEvent.VK_RIGHT && !LEFT) {
                RIGHT = true;
                UP = false;
                DOWN = false;
            }
            if (key == KeyEvent.VK_UP && !DOWN) {
                UP = true;
                RIGHT = false;
                LEFT = false;
            }
            if (key == KeyEvent.VK_DOWN && !UP) {
                DOWN = true;
                RIGHT = false;
                LEFT = false;
            }
        }

    }

}

