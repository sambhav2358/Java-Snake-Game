package snakey;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;


public class GamePanel extends JPanel implements ActionListener {

    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
    static final int DELAY = 75;
    final int x[] = new int[GAME_UNITS];
    final int y[] = new int[GAME_UNITS];
    int bodyParts = ComponentHelper.startSnakeSize;
    int applesEaten = 0;
    int appleX;
    int appleY;
    char direction = 'R';
    boolean running = false;
    Timer timer;
    Random random;

    GamePanel() {
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }

    public void startGame() {
        newApple();
        running = true;
        timer = new Timer(DELAY,this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        if (running) {
            for(int i=0; i<SCREEN_HEIGHT/UNIT_SIZE; i++) {
                g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
                g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
            }
            g.setColor(Color.red);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

            for(int i=0; i<bodyParts; i++) {
                if(i==0) {
                    g.setColor(ComponentHelper.snakeHeadColor);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                } else {
                    g.setColor(ComponentHelper.snakeBodyColor);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
            }
            g.setColor(Color.white);
            g.setFont(new Font("Courier New", Font.BOLD, 40));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString(String.valueOf(applesEaten),(SCREEN_WIDTH - metrics.stringWidth(String.valueOf(applesEaten)))/2,g.getFont().getSize());
        }
        else {
            gameOver(g);
        }
    }

    public void newApple() {
        appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
        appleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
    }

    public void move() {
        for(int i=bodyParts;i>0;i--) {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
        switch(direction) {
            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'D':
                y[0] = y[0] + UNIT_SIZE;
                break;
            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
            case 'R':
                x[0] = x[0] + UNIT_SIZE;
                break;
        }
    }

    public void checkApple() {
        if ((x[0] == appleX)&&(y[0] == appleY)) {
            bodyParts++;
            applesEaten++;
            newApple();
        }
    }

    public void checkCollisions() {
        // checks if head collides with body
        for(int i=bodyParts; i>0; i--) {
            if ((x[0] == x[i]) && (y[0] == y[i])) {
                running = false;
            }
        }
        // checks if head touches left border
        if (x[0] < 0) {
            running = false;
        }
        // checks if head touches right border
        if (x[0] > SCREEN_WIDTH-UNIT_SIZE) {
            running = false;
        }
        // checks if head touches top border
        if (y[0] < 0) {
            running = false;
        }
        // checks if head touches bottom border
        if (y[0] > SCREEN_HEIGHT-UNIT_SIZE) {
            running = false;
        }

        if (!running) {
             timer.stop();
        }

    }

    public void gameOver(Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("Courier New", Font.BOLD, 20));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("score: " + applesEaten,(SCREEN_WIDTH - metrics1.stringWidth("score: " + applesEaten))/2,g.getFont().getSize());

        // Game Over text
        g.setColor(Color.green);
        g.setFont(new Font("Courier New", Font.PLAIN, 40));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Game over",(SCREEN_WIDTH - metrics2.stringWidth("Game Over"))/2,SCREEN_HEIGHT/2);
        g.setFont(new Font("Courier New", Font.PLAIN, 20));
        FontMetrics metrics3 = getFontMetrics(g.getFont());
        g.drawString("Press 'R' to play again",(SCREEN_WIDTH - metrics3.stringWidth("Press 'R' to play again"))/2,SCREEN_HEIGHT/2 + 70);
        
        FontMetrics metrics4 = getFontMetrics(g.getFont());
        g.drawString("Press 'B' to exit",(SCREEN_WIDTH - metrics4.stringWidth("Press 'B' to exit"))/2,SCREEN_HEIGHT/2 + 140);
        
        ComponentHelper.totalMoney += applesEaten;
    }

    public void restartGame() {
    	dispose();
    	new MainFrame(new GamePanel());
    }
    
    public void dispose() {
	    JFrame parent = (JFrame) this.getTopLevelAncestor();
	    parent.dispose();
	}

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkApple();
            checkCollisions();
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch(e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if(direction != 'R') {
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if(direction != 'L') {
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if(direction != 'D') {
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(direction != 'U') {
                        direction = 'D';
                    }
                    break;
                case KeyEvent.VK_R:
                	restartGame();
                	break;
                case KeyEvent.VK_B:
                	dispose();
                	new MainFrame(new MainPanel());
                	break;
            }
        }
    }

}