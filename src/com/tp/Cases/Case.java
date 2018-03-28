package com.tp.Cases;

import com.tp.Exceptions.NbrTentativesException;


public abstract class Case {

    private char lettre;
    private int chances = 0;
    private int scoreLetrre = 0;
    private boolean trouve = false;


    public Case() {
    }

    public Case(char lettre) {
        this.lettre = lettre;
    }


    protected abstract int getScore();


    public boolean validerLettre(char ch) throws NbrTentativesException {

        if (chances > 0) {
            if (ch == lettre) {
                trouve = true;
                scoreLetrre = getScore();
                return true;

            } else {
                trouve = false;
                if (this instanceof Malus) ((Malus) this).addMalus();
                chances--;
                return false;
            }
        } else throw new NbrTentativesException();

    }

    public char getLettre() {
        return lettre;
    }

    public int getChances() {
        return chances;
    }

    public void setChances(int chances) {
        this.chances = chances;
    }

    public boolean isTrouve() {
        return trouve;
    }

    public int getScoreLetrre() {
        return scoreLetrre;
    }

}
