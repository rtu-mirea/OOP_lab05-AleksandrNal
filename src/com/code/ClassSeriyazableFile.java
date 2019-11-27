package com.code;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class ClassSeriyazableFile implements Serializable{

    private String file = "";

    ClassSeriyazableFile(String f){
        if (new File(f).exists()){
            file = f;
        }
    }

    public void record(User us) throws Exception{
        FileOutputStream out = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(us);
        oos.close();
    }

    public User read() throws Exception{
        FileInputStream in = new FileInputStream(file);
        ObjectInputStream oin = new ObjectInputStream(in);
        User us = new User();
        us = (User)oin.readObject();
        return us;
    }

    public void collection (ArrayList<User> users) throws Exception{
        FileOutputStream out = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(users);
    }

    public ArrayList readfile() throws Exception{
        FileInputStream in = new FileInputStream(file);
        ObjectInputStream oin = new ObjectInputStream(in);

        return (ArrayList) oin.readObject();
    }


}
