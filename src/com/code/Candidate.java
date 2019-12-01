package com.code;


public class Candidate {
    private String name;
    private int voices = 0;

    Candidate(){
        name = "";
    }

    Candidate(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setVoices(int voices) {
        this.voices = voices;
    }

    public int getVoices() {
        return voices;
    }

    public void addVoices(){
        voices++;
    }
}
