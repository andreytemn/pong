package main;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.concurrent.ThreadFactory;

/**
 * Created by andrey on 12.11.16.
 */
public class ConsoleListener extends Observable implements Runnable {

    private JFrame jFrame;

    public void init() {
        jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            jFrame.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    setChanged();
                    notifyObservers(new Object[]{e.getKeyCode()});
                }
            });
        }
    }
}
