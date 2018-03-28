package com.tp.Cases;

import java.util.ArrayList;
import java.util.Collections;


public class Proposition extends ZeroChance implements Malus {

    private final int poid = 2;
    private ArrayList<Character> propositions = new ArrayList<>();
    private int malus = 0;


    public Proposition(char lettre) {
        super(lettre);

        propositions.add(lettre);
        //remplire les propositions aleatoirement
        for (int i = 3; i > 0; i--) {
            int j = (int) (Math.random() * 100) % 26 + 1;
            if (lettre >= 89) j = j - j % 2;
            else if (lettre <= 66) j = j - j % 2 + 1;
            switch (j % 2) {
                case 0:
                    while (lettre - j < 65) j /= 2;
                    propositions.add((char) (lettre - j));
                    break;
                case 1:
                    while (lettre + j > 90) j /= 2;
                    propositions.add((char) (lettre + j));
                    break;
            }
        }
        Collections.sort(propositions);
    }

    @Override
    protected int getScore() {
        return poid;
    }

    public ArrayList<Character> getPropositions() {
        return propositions;
    }

    public void addMalus() {
        malus++;
    }

    public int getMalus() {
        return malus;
    }
}
