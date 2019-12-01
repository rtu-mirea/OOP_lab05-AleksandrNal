package com.code;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeUser {
    private JFrame frame;
    private JButton button1;
    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private int num;

    public ChangeUser() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                boolean repeat = false;
                String name = textField1.getText();
                String login = textField2.getText();
                for (User user : VotingSystem.users){
                    if (user.getLogin().compareTo(login) == 0 && VotingSystem.users.indexOf(user) != num){
                        JOptionPane.showMessageDialog(null, "Этот логин уже занят");
                        repeat = true;
                        break;
                    }
                }
                if(repeat)
                    return;
                String password = textField3.getText();
                VotingSystem.users.set(num, new User(name, login, password));
                frame.dispose();
                new GUI().Admin();
            }
        });
    }

    public void on(int i){
        num = i;
        User user = VotingSystem.users.get(num);
        textField1.setText(user.getName());
        textField2.setText(user.getLogin());
        textField3.setText(user.getPassword());
        frame = new JFrame();
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
