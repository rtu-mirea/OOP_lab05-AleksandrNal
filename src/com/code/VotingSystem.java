package com.code;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;


public class VotingSystem extends JFrame{
    public static ArrayList<User> users;
    public static Voting currentVoting = null;
    public static User currentUser;
    public static ArrayList<Elector> currentElectors = null;

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

    public static void main(String[] args) {
        users = new ArrayList<User>();
        Admin admin;
        admin = new Admin("admin", "admin", "admin");
        users.add(admin);
        new GUI().Menu();

    }
}
