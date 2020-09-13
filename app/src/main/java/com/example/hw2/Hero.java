package com.example.hw2;

import java.util.ArrayList;

public class Hero {
    private String name;
    public ArrayList<topTen> scores;

    public Hero() {
//        scores = new ArrayList<>();
    }

    public Hero(String name, int numOfMoves) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Hero setName(String name) {
        this.name = name;
        return this;
    }

    public ArrayList<topTen> getScores() {
        return scores;
    }

    public Hero setScores(ArrayList<topTen> scores) {
        this.scores = scores;
        return this;
    }

}