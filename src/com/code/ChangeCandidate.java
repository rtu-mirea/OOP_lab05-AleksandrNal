package com.code;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeCandidate {
    private JFrame frame;
    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JButton button1;
    private int num;

    public ChangeCandidate() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (VotingSystem.currentVoting == null){
                    JOptionPane.showMessageDialog(null, "Голосование ещё не создано");
                    return;
                }
                String name = textField1.getText();
                boolean repeat = false;
                for (Candidate candidate : VotingSystem.currentVoting.getCandidats()){
                    if (candidate.getName().compareTo(name) == 0 && VotingSystem.currentVoting.getCandidats().indexOf(candidate) != num){
                        JOptionPane.showMessageDialog(null, "Этот логин уже занят");
                        repeat = true;
                        break;
                    }
                }
                if(repeat)
                    return;
                int votes = 0;
                try {
                    votes = Integer.parseInt(textField2.getText());
                }catch (Exception e){
                    JOptionPane.showMessageDialog(null, "Ошибка ввода");
                    return;
                }
                Candidate candidate = new Candidate(name);
                candidate.setVoices(votes);
                VotingSystem.currentVoting.getCandidats().set(num, candidate);
                frame.dispose();
                new GUI().Admin();
            }
        });
    }

    public void on(int i) {
        num = i;
        Candidate candidate = VotingSystem.currentVoting.getCandidat(num);
        textField1.setText(candidate.getName());
        textField2.setText(Integer.toString(candidate.getVoices()));
        frame = new JFrame();
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
