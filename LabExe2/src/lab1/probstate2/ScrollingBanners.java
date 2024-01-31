package lab1.probstate2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ScrollingBanners extends JFrame implements Runnable {
    private String banner1 = "String1";
    private String banner2 = "String2";
    private int x1, x2;

    ScrollingBanners() {
        setTitle("Scrolling Banners");
        setSize(400, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        x1 = getWidth();
        x2 = 0;

        Timer timer = new Timer(50, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                x1 -= 5;
                x2 += 5;
                repaint();
            }
        });

        timer.start();
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(50);
                x1 -= 5;
                x2 += 5;
                repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.drawString(banner1, x1, 50);
        g.drawString(banner2, x2, 50);
    }

    public static void main(String[] args) {
        new Thread(new ScrollingBanners()).start();
    }
}
