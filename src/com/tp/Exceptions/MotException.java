package com.tp.Exceptions;


public class MotException extends Exception {

    public MotException() {
        System.out.println("Erreur de mot à entré : la taille du mot n'est pas supérieure à six");
    }
}
