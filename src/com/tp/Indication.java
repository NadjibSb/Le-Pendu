package com.tp;


public enum Indication {

    DEFINITION("DEFINITION", 3),
    SYNONYME("SYNONYME", 2),
    ANTONYME("ANTONYME", 1);

    private String indication;
    private int coeff;


    Indication(String indication, int coeff) {
        this.indication = indication;
        this.coeff = coeff;
    }

    public static Indication find(String indication) {
        if (indication.equals(DEFINITION.getIndication())) return DEFINITION;
        else if (indication.equals(SYNONYME.getIndication())) return SYNONYME;
        else if (indication.equals(ANTONYME.getIndication())) return ANTONYME;
        return null;
    }

    public String getIndication() {
        return indication;
    }

    public void setIndication(String indication) {
        this.indication = indication;
    }

    public int getCoeff() {
        return coeff;
    }

    public void setCoeff(int coeff) {
        this.coeff = coeff;
    }
}
