package mine.learn.layout;

import java.awt.BorderLayout;
import java.awt.Button;

import javax.swing.JFrame;

import mine.learn.Console;

/**
 * BorderLayout1
 */
public class BorderLayout1 extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = -8242470405556277690L;

    public BorderLayout1() {
        add(new Button("North"), BorderLayout.NORTH);
        add(new Button("EAST"), BorderLayout.EAST);
    }

    public static void main(String[] args) {
        // 重复代码太多，重新写一个类Consolo.java
        // SwingUtilities.invokeLater(new Runnable(){

        // @Override
        // public void run() {
        // new BorderLayout1();

        // }
        // })
        Console.run(new BorderLayout1(), 300, 400);
    }
}