package com.code;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;


public class VotingSystem {
    private static ArrayList<User> users;
    private static Voting currentVoting = null;
    private static User currentUser;
    private static ArrayList<Elector> currentElectors = null;

    public static User addUser(String name, String login, String password){
        return new Elector(name, login, password);
    }

    public static User findUser(String name, String password){
        for (User user : users){
            if (name.compareTo(user.getName()) == 0 && password.compareTo(user.getPassword()) == 0){
                return user;
            }
        }
        return null;
    }

    public static boolean is_currentElector(String login, String password){
        if (currentElectors != null)
            for (Elector elector : currentElectors){
                if (elector.getLogin().compareTo(login) == 0 && elector.getPassword().compareTo(password) == 0)
                    return true;
            }
        return false;
    }

    public static void Menu(){
        JFrame frame = new JFrame("Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 110);

        JPanel panel = new JPanel();
        JButton b1 = new JButton("Регистрация");
        JButton b2 = new JButton("Вход");
        JButton b3 = new JButton("Выход");
        JButton b4 = new JButton("Открыть");
        JButton b5 = new JButton("Сохранить");

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
                        users = csf.readfile();
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
                        csf.collection(users);
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

    public static void Registration(){
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
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean repeat = false;
                String name = t1.getText();
                String login = t2.getText();
                for (User user : users){
                    if (user.getLogin().compareTo(login) == 0){
                        JOptionPane.showMessageDialog(null, "Этот логин уже заният");
                        repeat = true;
                        break;
                    }
                }
                if(repeat)
                    return;
                String password = t3.getText();
                users.add(addUser(name, login, password));
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

    public static void Authorization(){
        JFrame frame = new JFrame("Authorization");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(250, 200);

        JPanel panel = new JPanel();

        JPanel p1 = new JPanel();
        JLabel l1 = new JLabel("Логин");
        JTextField t1 = new JTextField(10);
        p1.add(l1);
        p1.add(t1);

        JPanel p2 = new JPanel();
        JLabel l2 = new JLabel("Пароль");
        JTextField t2 = new JTextField(10);
        p2.add(l2);
        p2.add(t2);

        JPanel p3 = new JPanel();
        JButton b1 = new JButton("Войти");
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentUser = null;
                String login = t1.getText();
                String password = t2.getText();
                for (User user : users){
                    if (user.enter(login, password)){
                        currentUser = user;
                    }
                }
                if (currentUser == null){
                    JOptionPane.showMessageDialog(null, "Неверно введен логин или пароль");
                    frame.dispose();
                    Menu();
                    return;
                }
                if (currentUser.getClass().getName().compareTo("com.code.Admin") == 0){
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

    public static void Admin(){
        JFrame frame = new JFrame("Admin");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 400);

        JPanel panel = new JPanel();
        JTextField t1 = new JTextField(10);
        JButton b1 = new JButton("Новое голосование");
        JTextField t2 = new JTextField(10);
        JButton b2 = new JButton("Добавить кандидата");
        JButton b3 = new JButton("Подвести итоги");
        JButton b4 = new JButton("Выход");
        String[] tables = {"Имя", "Логин", "Пароль"};
        String[][] data = new String[users.size()][3];
        int i = 0;
        for (User u : users){
            data[i][0] = u.getName();
            data[i][1] = u.getLogin();
            data[i][2] = u.getPassword();
            i++;
        }
        JTable t = new JTable(data, tables);
        t.setPreferredScrollableViewportSize(t.getPreferredSize());
        t.setFillsViewportHeight(true);
        JScrollPane sp = new JScrollPane(t);


        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = t1.getText();
                currentVoting = new Voting(title);
                currentElectors = new ArrayList<Elector>();
            }
        });

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentVoting == null){
                    JOptionPane.showMessageDialog(null, "Голосование ещё не создано");
                    return;
                }
                String name = t2.getText();
                if (currentVoting.find_Candidate(name) == null) {
                    currentVoting.add_Candidate(name);
                    return;
                }
                JOptionPane.showMessageDialog(null, "Это имя уже занято");
            }
        });

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentVoting == null){
                    JOptionPane.showMessageDialog(null, "Голосование ещё не создано");
                    return;
                }
                currentVoting.sort();
                StringBuilder s = new StringBuilder("Результат голосования \"" + currentVoting.getTitle() + "\"");
                for (int i = 0; i < currentVoting.getList().size(); i++){
                    Candidate candidate = currentVoting.getCandidat(i);
                    s.append("\n").append(i + 1).append(") ").append(candidate.getName()).append(" с ").append(candidate.getVoices()).append(" голосами");
                }
                JOptionPane.showMessageDialog(null, s.toString());
                currentVoting = null;
                currentElectors = null;
            }
        });

        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Menu();
            }
        });

        panel.add(t1);
        panel.add(b1);
        panel.add(t2);
        panel.add(b2);
        panel.add(b3);
        panel.add(b4);
        panel.add(sp);
        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    public static void User(){
        JFrame frame = new JFrame("Admin");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);

        JPanel panel = new JPanel();
        JButton b1 = new JButton("проголосовать");
        JButton b2 = new JButton("Выход");

        String[] cands = {"Голосование ещё не создано"};
        if (currentElectors != null){
            ArrayList<Candidate> names = currentVoting.getCandidats();
            cands = new String[names.size()];
            int i = 0;
            for (Candidate c : names){
                cands[i] = c.getName();
                i++;
            }
        }

        JComboBox com = new JComboBox(cands);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = 0;
                if (currentElectors == null){
                    JOptionPane.showMessageDialog(null, "Голосование ещё не создано");
                    return;
                }
                if (!is_currentElector(currentUser.getLogin(),currentUser.getPassword())) {
                    currentElectors.add(new Elector(currentUser.getName(), currentUser.getLogin(), currentUser.getPassword()));
                    index = currentElectors.size() - 1;
                }else{
                    for (int i = 0; i < currentElectors.size(); i++){
                        if (currentUser.getLogin().compareTo(currentElectors.get(i).getLogin()) == 0) {
                            index = i;
                            break;
                        }
                    }
                }
                if (!currentElectors.get(index).isVoted()) {

                    String name = Objects.requireNonNull(com.getSelectedItem()).toString();
                    Candidate candidate = currentVoting.find_Candidate(name);
                    if (candidate == null) {
                        JOptionPane.showMessageDialog(null, "Такого кандидата нет");
                        return;
                    }
                    candidate.addVoices();
                    currentElectors.get(index).vote();
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

    public static void main(String[] args) {
        users = new ArrayList<User>();
        Admin admin;
        admin = new Admin("admin", "admin", "admin");
        users.add(admin);

        Menu();
    }
}
