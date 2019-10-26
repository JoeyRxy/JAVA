package mine.learn.thinkinginjava;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

import mine.learn.util.Console;

/**
 * TrackEvent
 * <p>
 * 跟踪多个事件
 * <p>
 * EASY
 */
public class TrackEvent extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 7364184205346849174L;

    private HashMap<String, JTextField> h = new HashMap<>();

    private String[] event = { "focusGained", "focusLost", "keyPressed", "keyReleased", "keyTyped", "mouseClicked",
            "mouseEntered", "mouseExited", "mousePressed", "mouseReleased", "mouseDragged", "mouseMoved" };

    private MyButton b1 = new MyButton(Color.red, "test1");
    private MyButton b2 = new MyButton(Color.green, "test2");

    class MyButton extends JButton {
        /**
         *
         */
        private static final long serialVersionUID = -2788834534183089787L;

        public MyButton(Color color, String label) {
            super(label);
            setBackground(color);
            addFocusListener(fl);
            addKeyListener(kl);
            addMouseListener(ml);
            addMouseMotionListener(mml);
        }

        public void report(String field, String msg) {
            h.get(field).setText(msg);
        }

        FocusListener fl = new FocusListener() {

            @Override
            public void focusLost(FocusEvent e) {
                report(Thread.currentThread().getStackTrace()[1].getMethodName(), e.paramString());
            }

            @Override
            public void focusGained(FocusEvent e) {
                report(Thread.currentThread().getStackTrace()[1].getMethodName(), e.paramString());
            }
        };

        KeyListener kl = new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                report(Thread.currentThread().getStackTrace()[1].getMethodName(), e.paramString());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                report(Thread.currentThread().getStackTrace()[1].getMethodName(), e.paramString());
            }

            @Override
            public void keyPressed(KeyEvent e) {
                report(Thread.currentThread().getStackTrace()[1].getMethodName(), e.paramString());
            }
        };

        MouseListener ml = new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent e) {
                report(Thread.currentThread().getStackTrace()[1].getMethodName(), e.paramString());
            }

            @Override
            public void mousePressed(MouseEvent e) {
                report(Thread.currentThread().getStackTrace()[1].getMethodName(), e.paramString());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                report(Thread.currentThread().getStackTrace()[1].getMethodName(), e.paramString());
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                report(Thread.currentThread().getStackTrace()[1].getMethodName(), e.paramString());
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                report(Thread.currentThread().getStackTrace()[1].getMethodName(), e.paramString());
            }
        };

        MouseMotionListener mml = new MouseMotionListener() {

            @Override
            public void mouseMoved(MouseEvent e) {
                report(Thread.currentThread().getStackTrace()[1].getMethodName(), e.paramString());
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                report(Thread.currentThread().getStackTrace()[1].getMethodName(), e.paramString());
            }
        };
    }

    public TrackEvent() {
        setLayout(new GridLayout(event.length + 1, 2));
        for (String evt : event) {
            JTextField txt = new JTextField();
            txt.setEditable(true);
            add(new JLabel(evt, JLabel.RIGHT));
            add(txt);
            h.put(evt, txt);
        }
        add(b1);
        add(b2);
    }

    public static void main(String[] args) {
        Console.run(new TrackEvent(), 700, 500);
    }
}