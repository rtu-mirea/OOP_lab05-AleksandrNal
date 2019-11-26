package com.code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Registration {

    public static void enable() {
        //Creating the Frame
        JFrame frame = new JFrame("Registration");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        JPanel panel = new JPanel();

        JPanel p1 = new JPanel();
        JLabel l1 = new JLabel("Имя");
        JTextField t1 = new JTextField(10);
        p1.add(l1);
        p1.add(t1);

        JPanel p2 = new JPanel();
        JLabel l2 = new JLabel("Логин");
        JTextField t2 = new JTextField(10);
        p2.add(l2);
        p2.add(t2);

        JPanel p3 = new JPanel();
        JLabel l3 = new JLabel("Пароль");
        JTextField t3 = new JTextField(10);
        p3.add(l3);
        p3.add(t3);

        JPanel p4 = new JPanel();
        JButton b1 = new JButton("Зарегистрироваться");
        p4.add(b1);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        panel.add(p1);
        panel.add(p2);
        panel.add(p3);
        panel.add(p4);

        frame.add(panel);

        frame.setVisible(true);
    }
}