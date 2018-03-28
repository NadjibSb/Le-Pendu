package com.tp.Cases;


public class MultiChance extends Case implements Malus {

    private final static int tentatives = 3;
    private final static int poid = 1;
    private int malus = 0;


    public MultiChance(char lettre) {
        super(lettre);
        setChances(tentatives);
    }

    @Override
    protected int getScore() {
        return poid;
    }

    public void addMalus() {
        malus += 2;
    }

    public int getMalus() {
        return malus;
    }
}
