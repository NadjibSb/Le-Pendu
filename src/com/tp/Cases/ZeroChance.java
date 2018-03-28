package com.tp.Cases;


public class ZeroChance extends Case {

    private final static int tentatives = 1;
    private final static int poid = 3;


    public ZeroChance(char lettre) {
        super(lettre);
        setChances(tentatives);
    }

    @Override
    protected int getScore() {
        return poid;
    }


}
