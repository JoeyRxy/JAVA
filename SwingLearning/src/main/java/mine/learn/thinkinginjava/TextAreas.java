package mine.learn.thinkinginjava;

// import java.awt.FlowLayout;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

/**
 * TextAreas
 */
public class TextAreas extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = -8335813813704068310L;
    private JButton b = new JButton("Add Data"), c = new JButton("Clear Data");
    private JTextArea textArea = new JTextArea(20, 40);
    private Map<String, String> map = new HashMap<>();

    public TextAreas() {
        super(Class.class.getName());
        map.put("fuck1", "you");
        map.put("fuck2", "her");
        map.put("fuck3", "him");
        map.put("fuck4", "everyone");
        b.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                for (var m : map.entrySet()) {
                    textArea.append(m.getKey() + ":\t" + m.getValue() + "\n");
                }
            }
        });
        c.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
            }
        });

        setLayout(new FlowLayout());
        add(new JScrollPane(textArea));
        this.add(b);
        super.add(c);
        setVisible(true);
        setBounds(200, 200, 400, 500);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new TextAreas();
            }
        });
    }
}