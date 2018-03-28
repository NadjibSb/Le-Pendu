package com.tp;

import com.tp.Exceptions.JoueurException;
import com.tp.Exceptions.PseudoException;

import java.io.*;
import java.util.ArrayList;


public class Joueur implements Serializable {

    private String pseudo;
    private ArrayList<Integer> scores = new ArrayList<>();
    private transient Session session = new Session();


    public Joueur() {
    }

    //////////////////////////////////////////////
    public void oldJoueur(String pseudo) throws JoueurException, PseudoException {

        FileInputStream fis = null;
        BufferedInputStream bis = null;
        ObjectInputStream ois = null;
        File file;

        if (pseudoValide(pseudo)) {
            if ((file = fileExiste(pseudo)) != null) {
                try {
                    fis = new FileInputStream("Joueurs/" + pseudo);
                    bis = new BufferedInputStream(fis);
                    ois = new ObjectInputStream(bis);
                    Object obj = ois.readObject();
                    this.pseudo = ((Joueur) obj).getPseudo();
                    this.scores = ((Joueur) obj).getScores();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();

                } finally {
                    try {
                        fis.close();
                        bis.close();
                        ois.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                throw new JoueurException(); //joueur existé
            }
        } else throw new PseudoException(); //pseudo non valide
        session.newSession();

    }

    public void newJoueur(String pseudo) throws PseudoException, JoueurException {
        File file;

        if (pseudoValide(pseudo)) {
            if ((file = fileExiste(pseudo)) == null) { //le pseudo n'existe pas dans le fichier
                this.pseudo = pseudo;
                this.enregistrer();

            } else {
                throw new JoueurException();   //il y a un autre joueur avec ce pseudo
            }

        } else {
            throw new PseudoException();  //pseudo non valide
        }
        session.newSession();
    }

    //////////////////////////////////////////////
    public void enregistrer() {
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream("Joueurs/" + pseudo);
            bos = new BufferedOutputStream(fos);
            oos = new ObjectOutputStream(bos);
            oos.writeObject(this);
            System.out.println(" joueur enregistre");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                oos.close();
                bos.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    //////////////////////////////////////////////
    private File fileExiste(String pseudo) {
        File f = new File("Joueurs");

        for (File file : f.listFiles()) {
            if (file.getName().equals(pseudo)) {
                return file;
            }
        }
        return null;
    }

    //////////////////////////////////////////////
    private boolean pseudoValide(String pseudo) {
        if (pseudo.toUpperCase().charAt(0) > 64 && pseudo.toUpperCase().charAt(0) < 91) return true;
        else return false;
    }

    //////////////////////////////////////////////
    public int bestScore() {
        int best = 0;
        for (int i : scores) {
            if (i > best) best = i;
        }
        return best;
    }


    //////////////////////////////////////////////
    public String getPseudo() {
        return pseudo;
    }

    public ArrayList<Integer> getScores() {
        return scores;
    }

    public Session getSession() {
        return session;
    }
}

