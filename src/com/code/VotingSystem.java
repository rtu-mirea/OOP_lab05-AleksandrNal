package com.code;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class VotingSystem {
    private static List<User> users;
    private static Voting currentVoting = null;
    private static User currentUser;
    private static List<Elector> currentElectors = null;

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
        JFrame frame = new JFrame("Authorization");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 100);

        JPanel panel = new JPanel();
        JButton b1 = new JButton("Регистрация");
        JButton b2 = new JButton("Вход");
        JButton b3 = new JButton("Выход");
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

        panel.add(b1);
        panel.add(b2);
        panel.add(b3);
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
                System.out.println("enter name login password");
                String name = t1.getText();
                String login = t2.getText();
                for (User user : users){
                    if (user.getLogin().compareTo(login) == 0){
                        System.out.println("this login already taken");
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
                System.out.println("enter login and password");
                String login = t1.getText();
                String password = t2.getText();
                for (User user : users){
                    if (user.enter(login, password)){
                        currentUser = user;
                    }
                }
                if (currentUser == null){
                    System.out.println("invalid");
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
        frame.setSize(300, 200);

        JPanel panel = new JPanel();
        JTextField t1 = new JTextField(10);
        JButton b1 = new JButton("Новое голосование");
        JTextField t2 = new JTextField(10);
        JButton b2 = new JButton("Добавить кандидата");
        JButton b3 = new JButton("Подвести итоги");
        JButton b4 = new JButton("Выход");

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
                    System.out.println("Voting is not created yet");
                    return;
                }
                String name = t2.getText();
                if (currentVoting.find_Candidate(name) == null) {
                    currentVoting.add_Candidate(name);
                    return;
                }
                System.out.println("this name is already taken");
            }
        });

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentVoting == null){
                    System.out.println("Voting is not created yet");
                    return;
                }
                currentVoting.sort();
                System.out.println("Results of voting \"" + currentVoting.getTitle() + "\"");
                for (int i = 0; i < currentVoting.getList().size(); i++){
                    Candidate candidate = currentVoting.getCandidat(i);
                    System.out.println(i+1 + ") " + candidate.getName() + " with " + candidate.getVoices() + " voices");
                }
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
        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    public static void User(){
        JFrame frame = new JFrame("Admin");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);

        JPanel panel = new JPanel();
        JTextField t1 = new JTextField(10);
        JButton b1 = new JButton("проголосовать");
        JButton b2 = new JButton("Выход");

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = 0;
                if (currentElectors == null){
                    System.out.println("there is no elections right now");
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
                    System.out.println("enter name of candidate");
                    String name = t1.getText();
                    Candidate candidate = currentVoting.find_Candidate(name);
                    if (candidate == null) {
                        System.out.println("invalid");
                        return;
                    }
                    candidate.addVoices();
                    currentElectors.get(index).vote();
                }else
                    System.out.println("Voted already");
            }
        });

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Menu();
            }
        });


        panel.add(t1);
        panel.add(b1);
        panel.add(b2);
        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        users = new ArrayList<User>();
        boolean upper = true;
        Scanner in = new Scanner(System.in);
        Admin admin;
        admin = new Admin("admin", "admin", "admin");
        users.add(admin);
        Menu();


        /* while (upper){
            System.out.println("1)sing up 2)sing in 3)exit");
            int check = 0;
            try {
                check = Integer.parseInt(in.next());
            }catch (Exception e){
                System.out.println("invalid");
                check = 0;
            }
            switch(check){
                case 1:
                    boolean repeat = false;
                    System.out.println("enter name login password");
                    String name = in.next();
                    String login = in.next();
                    for (User user : users){
                        if (user.getLogin().compareTo(login) == 0){
                            System.out.println("this login already taken");
                            repeat = true;
                            break;
                        }
                    }
                    if(repeat)
                        break;
                    String password = in.next();
                    users.add(addUser(name, login, password));
                    break;

                case 2:
                    currentUser = null;
                    System.out.println("enter login and password");
                    login = in.next();
                    password = in.next();
                    for (User user : users){
                        if (user.enter(login, password)){
                            currentUser = user;
                        }
                    }
                    if (currentUser == null){
                        System.out.println("invalid");
                        break;
                    }

                    boolean lower = true;
                    if (currentUser.getClass().getName().compareTo("com.code.Admin") == 0){
                        while(lower){
                            System.out.println("1)new voting 2)add candidate 3)result 4)sing out");
                            check = 0;
                            try {
                                check = Integer.parseInt(in.next());
                            }catch (Exception e){
                                System.out.println("invalid");
                                check = 0;
                            }
                            switch (check){
                                case 1:
                                    System.out.println("enter the title of voting");
                                    String title = in.next();
                                    currentVoting = new Voting(title);
                                    currentElectors = new ArrayList<Elector>();
                                    break;
                                case 2:
                                    if (currentVoting == null){
                                        System.out.println("Voting is not created yet");
                                        break;
                                    }
                                    System.out.println("enter candidates name");
                                    repeat = false;
                                    name = in.next();
                                    if (currentVoting.find_Candidate(name) == null) {
                                        currentVoting.add_Candidate(name);
                                        break;
                                    }
                                    System.out.println("this name is already taken");
                                    break;
                                case 3:
                                    if (currentVoting == null){
                                        System.out.println("Voting is not created yet");
                                        break;
                                    }
                                    currentVoting.sort();
                                    System.out.println("Results of voting \"" + currentVoting.getTitle() + "\"");
                                    for (int i = 0; i < currentVoting.getList().size(); i++){
                                        Candidate candidate = currentVoting.getCandidat(i);
                                        System.out.println(i+1 + ") " + candidate.getName() + " with " + candidate.getVoices() + " voices");
                                    }
                                    currentVoting = null;
                                    currentElectors = null;
                                    break;
                                case 4:
                                    lower = false;
                            }
                        }
                    }else{
                        while (lower) {
                            System.out.println("1)vote 2)sing out");
                            check = 0;
                            try {
                                check = Integer.parseInt(in.next());
                            }catch (Exception e){
                                System.out.println("invalid");
                                check = 0;
                            }
                            switch (check) {
                                case 1:
                                    int index = 0;
                                    if (currentElectors == null){
                                        System.out.println("there is no elections right now");
                                        break;
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
                                        System.out.println("enter name of candidate");
                                        name = in.next();
                                        Candidate candidate = currentVoting.find_Candidate(name);
                                        if (candidate == null) {
                                            System.out.println("invalid");
                                            break;
                                        }
                                        candidate.addVoices();
                                        currentElectors.get(index).vote();
                                    }else
                                        System.out.println("Voted already");
                                    break;

                                case 2:
                                    lower = false;
                                    break;
                            }
                        }
                    }
                    break;

                case 3:
                    upper = false;
                    break;
            }
        }

         */

    }
}
