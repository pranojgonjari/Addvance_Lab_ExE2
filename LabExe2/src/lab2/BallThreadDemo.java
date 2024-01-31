package lab2;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ball extends Thread {
    private static final int MAX_LOCATION = 300;

    private final Color color;
    private final int speed;
    private final Lock lock;

    private int x;

    public Ball(Color color, int speed, Lock lock) {
        this.color = color;
        this.speed = speed;
        this.lock = lock;
        this.x = 0;
    }

    public void run() {
        while (true) {
            move();
            checkLocation();
        }
    }

    private void move() {
        synchronized (lock) {
            x += speed;
            SwingUtilities.invokeLater(() -> BallPanel.getInstance().repaint());
        }

        try {
            Thread.sleep(20); // Adjust the sleep time for smoother animation
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void checkLocation() {
        synchronized (lock) {
            if (x >= MAX_LOCATION) {
                System.out.println(color + " ball reached the destination. Waiting for others.");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(color + " ball woke up and restarted.");
                x = 0;
            }

            if (x == 0) {
                System.out.println(color + " ball restarted.");
                lock.notifyAll();
            }
        }
    }

    public int getX() {
        return x;
    }
}

class BallPanel extends JPanel {
    private static final BallPanel instance = new BallPanel();
    private final Lock lock = new ReentrantLock();

    private BallPanel() {
        BallThreadDemo.redBall = new Ball(Color.RED, 5, lock);
        BallThreadDemo.greenBall = new Ball(Color.GREEN, 3, lock);
        BallThreadDemo.blueBall = new Ball(Color.BLUE, 2, lock);

        BallThreadDemo.redBall.start();
        BallThreadDemo.greenBall.start();
        BallThreadDemo.blueBall.start();
    }

    public static BallPanel getInstance() {
        return instance;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.RED);
        g.fillOval(BallThreadDemo.redBall.getX(), 50, 20, 20);

        g.setColor(Color.GREEN);
        g.fillOval(BallThreadDemo.greenBall.getX(), 100, 20, 20);

        g.setColor(Color.BLUE);
        g.fillOval(BallThreadDemo.blueBall.getX(), 150, 20, 20);
    }
}

public class BallThreadDemo {
    public static Ball redBall;
    public static Ball greenBall;
    public static Ball blueBall;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Ball Thread Demo");
            frame.setSize(400, 300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(BallPanel.getInstance());
            frame.setVisible(true);
        });
    }
}


