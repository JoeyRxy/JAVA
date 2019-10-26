package mine.learn.util;

import java.awt.FlowLayout;
import java.lang.reflect.Constructor;

import javax.swing.*;
import javax.swing.border.*;

/**
 * ButtonGroups
 */
public class ButtonGroups extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 7946924202316553925L;
    private static String[] ids = { "June", "Ward", "Beaver", "Wally", "Eddie", "Lumpy" };

    public static JPanel makeButtonPanel(Class<? extends AbstractButton> clazz, String[] ids) {
        ButtonGroup bg = new ButtonGroup();
        JPanel jp = new JPanel();
        String title = clazz.getName();
        title = title.substring(title.lastIndexOf(".") + 1);
        jp.setBorder(new TitledBorder(title));
        AbstractButton btn = new JButton("failed");

        for (String id : ids) {
            Constructor<? extends AbstractButton> ctor;
            try {
                ctor = clazz.getConstructor(String.class);
                btn = (AbstractButton) ctor.newInstance(id);
            } catch (Exception e) {
                System.err.println("can't make" + clazz);
                e.printStackTrace();
            }
            bg.add(btn);
            jp.add(btn);
        }
        return jp;
    }

    public ButtonGroups() {
        setLayout(new FlowLayout());
        add(makeButtonPanel(JButton.class, ids));
        add(makeButtonPanel(JToggleButton.class, ids));
        add(makeButtonPanel(JCheckBox.class, ids));
        add(makeButtonPanel(JRadioButton.class, ids));
    }

    public static void main(String[] args) {
        Console.run(new ButtonGroups(), 700, 700);
    }
}