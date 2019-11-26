package com.code;

import javax.swing.*;

public class Authorizatoin {

    public static void enable() {
        JFrame frame = new JFrame("Authorization");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 100);


        JPanel panel = new JPanel(); // the panel is not visible in output
        JButton b1 = new JButton("Зарегистрироваться");
        JButton b2 = new JButton("Войти");
        JButton b3 = new JButton("Выход");

        panel.add(b1);
        panel.add(b2);
        panel.add(b3);
        frame.add(panel);

        frame.setVisible(true);
    }
}
