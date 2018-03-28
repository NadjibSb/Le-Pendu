package com.tp;

import com.tp.Cases.*;
import com.tp.Exceptions.MotException;

import java.util.ArrayList;


public class Mot {

    private String mot;
    private ArrayList<Case> cases = new ArrayList<Case>();
    private Indication indication;
    private String message;
    private int coeffMot = 0;


    public Mot(String mot, Indication indication, String message) throws MotException {

        if (mot.length() < 6) {
            throw new MotException();
        } else {
            this.mot = mot.toUpperCase();
            this.indication = indication;
            coeffMot = indication.getCoeff();
            this.message = message;
            int zeroChance_Proposition = 0;

            //Affecter chaque lettre dans une case
            for (int i = 0; i < this.mot.length(); i++) {

                int type;
                //make sure that nbr of zeroChance & Proposition are less than 3
                if (zeroChance_Proposition < 3 && i % 2 == 1) { //la deuxieme condition pour bien séparer les cases de zerochance
                    type = (int) ((Math.random() * 10) % 3);
                } else {
                    type = 1;
                }

                switch (type) {
                    case 0:
                        cases.add(new ZeroChance(this.mot.charAt(i)));
                        zeroChance_Proposition++;
                        break;
                    case 1:
                        cases.add(new MultiChance(this.mot.charAt(i)));
                        break;
                    case 2:
                        cases.add(new Proposition(this.mot.charAt(i)));
                        zeroChance_Proposition++;
                        break;
                }
            }
        }
    }

    public int calculerScore() {
        int malus = 0;
        int nbrCasesMalus = 0;
        int scoreMot = 0;

        for (int i = 0; i < cases.size(); i++) {
            scoreMot += cases.get(i).getScoreLetrre();
            if (cases.get(i) instanceof Malus) {
                malus += ((Malus) cases.get(i)).getMalus();
                nbrCasesMalus++;
            }
        }

        scoreMot = scoreMot * coeffMot;

        if (nbrCasesMalus > 5) {
            scoreMot -= malus;
        }

        return scoreMot;   // a verifier (si apres ou avant le malus)
    }

    public boolean motTrouve() {
        for (int i = 0; i < cases.size(); i++) {
            if (!cases.get(i).isTrouve()) return false;
        }
        return true;
    }

    public String getMot() {
        return mot;
    }

    public Case getCase(int i) {
        return cases.get(i);
    }

    public String getMessage() {
        return message;
    }

    public Indication getIndication() {
        return indication;
    }
}
