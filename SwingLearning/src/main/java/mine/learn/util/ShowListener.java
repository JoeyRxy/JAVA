package mine.learn.util;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.*;
import java.util.regex.Matcher;
import java.util.regex.*;

import javax.swing.*;

/**
 * ShowListener
 */
public class ShowListener extends JFrame {

    private JTextField nameTextFiled = new JTextField(25);
    private JTextArea res = new JTextArea(40, 65);
    private static Pattern addListener = Pattern.compile("(add\\w+?Listener\\(.*?\\))");
    private static Pattern qualifier = Pattern.compile("\\w+\\.");// 获取方法

    class NameL implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String nm = nameTextFiled.getText().trim();
            if (nm.length() == 0) {
                res.setText("No Match");
                return;
            }
            Class<?> clazz;
            try {
                clazz = Class.forName("javax.swing." + nm);
            } catch (ClassNotFoundException e1) {
                res.setText("No Match");
                return;
            }

            Method[] methods = clazz.getMethods();
            res.setText("");
            for (Method method : methods) {
                Matcher matcher = addListener.matcher(method.toString());
                if (matcher.find()) {
                    res.append(qualifier.matcher(matcher.group(1)).replaceAll("") + "\n");// group(1): 方法名后面的所有参数
                }
            }
        }

    }

    public ShowListener() {
        NameL nameListener = new NameL();
        nameTextFiled.addActionListener(nameListener);// 这个listener的作用 是在打字的时候运行操作吗？
        JPanel top = new JPanel();// 新建一个panel，在里面添加东西
        top.add(new JLabel("Swing Class Name (press Enter)"));
        top.add(nameTextFiled);

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(res));// 直接在初始化中间层ScrollPane的时候就加入组件
        nameTextFiled.setText("JTextArea");
        nameListener.actionPerformed(new ActionEvent("", 0, ""));// TODO ?

    }

    public static void main(String[] args) {
        Console.run(new ShowListener(), 500, 400);
    }

}