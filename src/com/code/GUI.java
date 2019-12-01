package com.code;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class GUI {

    public void Menu(){
        JFrame frame = new JFrame("Menu");
        frame.getContentPane().setBackground(Color.YELLOW);
        frame.setSize(300, 110);

        JPanel panel = new JPanel();
        panel.setBackground(Color.GRAY);
        JButton b1 = new JButton("Регистрация");
        b1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        b1.setForeground(Color.blue);
        b1.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton b2 = new JButton("Вход");
        b2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        b2.setForeground(Color.blue);
        b2.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton b3 = new JButton("Выход");
        b3.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        b3.setForeground(Color.blue);
        b3.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton b4 = new JButton("Открыть");
        b4.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        b4.setForeground(Color.blue);
        b4.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton b5 = new JButton("Сохранить");
        b5.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        b5.setForeground(Color.blue);
        b5.setAlignmentX(Component.CENTER_ALIGNMENT);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Registration();
                frame.dispose();
            }
        });

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Authorization();
                frame.dispose();
            }
        });

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

                int returnValue = fc.showOpenDialog(null);
                // int returnValue = jfc.showSaveDialog(null);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fc.getSelectedFile();
                    ClassSeriyazableFile csf = new ClassSeriyazableFile(selectedFile.getAbsolutePath());
                    try {
                        VotingSystem.users = csf.readfile();
                    }catch (Exception ex){
                        JOptionPane.showMessageDialog(null, "Ошибка");
                    }
                }
            }
        });

        b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

                int returnValue = fc.showOpenDialog(null);
                // int returnValue = jfc.showSaveDialog(null);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fc.getSelectedFile();
                    ClassSeriyazableFile csf = new ClassSeriyazableFile(selectedFile.getAbsolutePath());
                    try {
                        csf.collection(VotingSystem.users);
                    }catch (Exception ex){
                        JOptionPane.showMessageDialog(null, "Ошибка");
                    }
                }
            }
        });

        panel.add(b1);
        panel.add(b2);
        panel.add(b3);
        panel.add(b4);
        panel.add(b5);
        frame.getContentPane().add(panel);

        frame.setVisible(true);
    }

    public void Registration(){
        JFrame frame = new JFrame("Registration");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        JPanel panel = new JPanel();
        panel.setBackground(Color.GRAY);

        JPanel p1 = new JPanel();
        JLabel l1 = new JLabel("Имя");
        l1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        l1.setForeground(Color.blue);
        l1.setAlignmentX(Component.LEFT_ALIGNMENT);
        JTextField t1 = new JTextField(10);
        t1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        t1.setForeground(Color.blue);
        t1.setAlignmentX(Component.LEFT_ALIGNMENT);
        p1.add(l1);
        p1.add(t1);

        JPanel p2 = new JPanel();
        JLabel l2 = new JLabel("Логин");
        l2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        l2.setForeground(Color.blue);
        l2.setAlignmentX(Component.LEFT_ALIGNMENT);
        JTextField t2 = new JTextField(10);
        t2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        t2.setForeground(Color.blue);
        t2.setAlignmentX(Component.LEFT_ALIGNMENT);
        p2.add(l2);
        p2.add(t2);

        JPanel p3 = new JPanel();
        JLabel l3 = new JLabel("Пароль");
        l3.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        l3.setForeground(Color.blue);
        l3.setAlignmentX(Component.LEFT_ALIGNMENT);
        JTextField t3 = new JTextField(10);
        t3.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        t3.setForeground(Color.blue);
        t3.setAlignmentX(Component.LEFT_ALIGNMENT);
        p3.add(l3);
        p3.add(t3);

        JPanel p4 = new JPanel();
        JButton b1 = new JButton("Зарегистрироваться");
        b1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        b1.setForeground(Color.blue);
        b1.setAlignmentX(Component.CENTER_ALIGNMENT);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean repeat = false;
                String name = t1.getText();
                String login = t2.getText();
                for (User user : VotingSystem.users){
                    if (user.getLogin().compareTo(login) == 0){
                        JOptionPane.showMessageDialog(null, "Этот логин уже занят");
                        repeat = true;
                        break;
                    }
                }
                if(repeat)
                    return;
                String password = t3.getText();
                VotingSystem.users.add(VotingSystem.addUser(name, login, password));
                frame.dispose();
                Menu();
            }
        });
        p4.add(b1);

        panel.add(p1);
        panel.add(p2);
        panel.add(p3);
        panel.add(p4);

        frame.add(panel);

        frame.setVisible(true);
    }

    public void Authorization(){
        JFrame frame = new JFrame("Authorization");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(250, 200);

        JPanel panel = new JPanel();
        panel.setBackground(Color.GRAY);

        JPanel p1 = new JPanel();
        JLabel l1 = new JLabel("Логин");
        l1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        l1.setForeground(Color.blue);
        l1.setAlignmentX(Component.LEFT_ALIGNMENT);
        JTextField t1 = new JTextField(10);
        t1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        t1.setForeground(Color.blue);
        t1.setAlignmentX(Component.LEFT_ALIGNMENT);
        p1.add(l1);
        p1.add(t1);

        JPanel p2 = new JPanel();
        JLabel l2 = new JLabel("Пароль");
        l2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        l2.setForeground(Color.blue);
        l2.setAlignmentX(Component.LEFT_ALIGNMENT);
        JTextField t2 = new JTextField(10);
        t2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        t2.setForeground(Color.blue);
        t2.setAlignmentX(Component.LEFT_ALIGNMENT);
        p2.add(l2);
        p2.add(t2);

        JPanel p3 = new JPanel();
        JButton b1 = new JButton("Войти");
        b1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        b1.setForeground(Color.blue);
        b1.setAlignmentX(Component.CENTER_ALIGNMENT);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VotingSystem.currentUser = null;
                String login = t1.getText();
                String password = t2.getText();
                for (User user : VotingSystem.users){
                    if (user.enter(login, password)){
                        VotingSystem.currentUser = user;
                    }
                }
                if (VotingSystem.currentUser == null){
                    JOptionPane.showMessageDialog(null, "Неверно введен логин или пароль");
                    frame.dispose();
                    Menu();
                    return;
                }
                if (VotingSystem.currentUser.getClass().getName().compareTo("com.code.Admin") == 0){
                    frame.dispose();
                    Admin();
                }else{
                    frame.dispose();
                    User();
                }
            }
        });
        p3.add(b1);

        panel.add(p1);
        panel.add(p2);
        panel.add(p3);

        frame.add(panel);

        frame.setVisible(true);
    }

    public void Admin(){
        JFrame frame = new JFrame("Admin");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(330, 400);

        JPanel panel = new JPanel();
        panel.setBackground(Color.GRAY);
        JTextField t1 = new JTextField(10);
        t1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        t1.setForeground(Color.blue);
        t1.setAlignmentX(Component.LEFT_ALIGNMENT);
        JButton b1 = new JButton("Новое голосование");
        b1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        b1.setForeground(Color.blue);
        b1.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField t2 = new JTextField(10);
        t2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        t2.setForeground(Color.blue);
        t2.setAlignmentX(Component.LEFT_ALIGNMENT);
        JButton b2 = new JButton("Добавить кандидата");
        b2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        b2.setForeground(Color.blue);
        b2.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField t3 = new JTextField(10);
        t3.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        t3.setForeground(Color.blue);
        t3.setAlignmentX(Component.LEFT_ALIGNMENT);
        JButton b5 = new JButton("Изменить пользователя");
        b5.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        b5.setForeground(Color.blue);
        b5.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField t4 = new JTextField(10);
        t4.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        t4.setForeground(Color.blue);
        t4.setAlignmentX(Component.LEFT_ALIGNMENT);
        JButton b6 = new JButton("Изменить кандидата");
        b6.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        b6.setForeground(Color.blue);
        b6.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton b3 = new JButton("Подвести итоги");
        b3.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        b3.setForeground(Color.blue);
        b3.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton b4 = new JButton("Выход");
        b4.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        b4.setForeground(Color.blue);
        b4.setAlignmentX(Component.CENTER_ALIGNMENT);

        String[] tables = {"Имя", "Логин", "Пароль"};
        String[][] data = new String[VotingSystem.users.size()][3];
        int i = 0;
        for (User u : VotingSystem.users){
            data[i][0] = u.getName();
            data[i][1] = u.getLogin();
            data[i][2] = u.getPassword();
            i++;
        }
        JTable t = new JTable(data, tables);
        t.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        t.setForeground(Color.blue);
        t.setAlignmentX(Component.LEFT_ALIGNMENT);
        t.setPreferredScrollableViewportSize(t.getPreferredSize());
        t.setFillsViewportHeight(true);
        JScrollPane sp = new JScrollPane(t);


        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = t1.getText();
                VotingSystem.currentVoting = new Voting(title);
                VotingSystem.currentElectors = new ArrayList<Elector>();
            }
        });

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (VotingSystem.currentVoting == null){
                    JOptionPane.showMessageDialog(null, "Голосование ещё не создано");
                    return;
                }
                String name = t2.getText();
                if (VotingSystem.currentVoting.find_Candidate(name) == null) {
                    VotingSystem.currentVoting.add_Candidate(name);
                    return;
                }
                JOptionPane.showMessageDialog(null, "Это имя уже занято");
            }
        });

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (VotingSystem.currentVoting == null){
                    JOptionPane.showMessageDialog(null, "Голосование ещё не создано");
                    return;
                }
                VotingSystem.currentVoting.sort();
                StringBuilder s = new StringBuilder("Результат голосования \"" + VotingSystem.currentVoting.getTitle() + "\"");
                for (int i = 0; i < VotingSystem.currentVoting.getList().size(); i++){
                    Candidate candidate = VotingSystem.currentVoting.getCandidat(i);
                    s.append("\n").append(i + 1).append(") ").append(candidate.getName()).append(" с ").append(candidate.getVoices()).append(" голосами");
                }
                JOptionPane.showMessageDialog(null, s.toString());
                VotingSystem.currentVoting = null;
                VotingSystem.currentElectors = null;
            }
        });

        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Menu();
            }
        });

        b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int num = 0;
                try{
                    num = Integer.parseInt(t3.getText());
                    if (num > VotingSystem.users.size() || num <= 0){
                        throw new Exception();
                    }
                }catch (Exception ee){
                    JOptionPane.showMessageDialog(null, "Ошибка ввода");
                    return;
                }
                new ChangeUser().on(num);
                frame.dispose();

            }
        });

        b6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int num = 0;
                try{
                    num = Integer.parseInt(t4.getText());
                    if (num > VotingSystem.currentVoting.getCandidats().size() || num < 0){
                        throw new Exception();
                    }
                }catch (Exception ee){
                    JOptionPane.showMessageDialog(null, "Ошибка ввода");
                    return;
                }
                new ChangeCandidate().on(num);
                frame.dispose();

            }
        });
        panel.add(t1);
        panel.add(b1);
        panel.add(t2);
        panel.add(b2);

        panel.add(t3);
        panel.add(b5);
        panel.add(t4);
        panel.add(b6);

        panel.add(b3);
        panel.add(b4);
        panel.add(sp);
        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    public void User(){
        JFrame frame = new JFrame("Admin");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);

        JPanel panel = new JPanel();
        panel.setBackground(Color.GRAY);
        JButton b1 = new JButton("проголосовать");
        b1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        b1.setForeground(Color.blue);
        b1.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton b2 = new JButton("Выход");
        b2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        b2.setForeground(Color.blue);
        b2.setAlignmentX(Component.CENTER_ALIGNMENT);

        String[] cands = {"Голосование ещё не создано"};
        if (VotingSystem.currentElectors != null){
            ArrayList<Candidate> names = VotingSystem.currentVoting.getCandidats();
            cands = new String[names.size()];
            int i = 0;
            for (Candidate c : names){
                cands[i] = c.getName();
                i++;
            }
        }

        JComboBox com = new JComboBox(cands);
        com.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        com.setForeground(Color.blue);
        com.setAlignmentX(Component.LEFT_ALIGNMENT);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = 0;
                if (VotingSystem.currentElectors == null){
                    JOptionPane.showMessageDialog(null, "Голосование ещё не создано");
                    return;
                }
                if (!VotingSystem.is_currentElector(VotingSystem.currentUser.getLogin(), VotingSystem.currentUser.getPassword())) {
                    VotingSystem.currentElectors.add(new Elector(VotingSystem.currentUser.getName(), VotingSystem.currentUser.getLogin(), VotingSystem.currentUser.getPassword()));
                    index = VotingSystem.currentElectors.size() - 1;
                }else{
                    for (int i = 0; i < VotingSystem.currentElectors.size(); i++){
                        if (VotingSystem.currentUser.getLogin().compareTo(VotingSystem.currentElectors.get(i).getLogin()) == 0) {
                            index = i;
                            break;
                        }
                    }
                }
                if (!VotingSystem.currentElectors.get(index).isVoted()) {

                    String name = Objects.requireNonNull(com.getSelectedItem()).toString();
                    Candidate candidate = VotingSystem.currentVoting.find_Candidate(name);
                    if (candidate == null) {
                        JOptionPane.showMessageDialog(null, "Такого кандидата нет");
                        return;
                    }
                    candidate.addVoices();
                    VotingSystem.currentElectors.get(index).vote();
                }else
                    JOptionPane.showMessageDialog(null, "Уже проголосовал");
            }
        });

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Menu();
            }
        });

        panel.add(com);
        panel.add(b1);
        panel.add(b2);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }
}
