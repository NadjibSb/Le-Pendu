package com.tp;

import com.tp.Exceptions.MotException;
import com.tp.Graphic.AlertBox;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Session {

    private ArrayList<Mot> mots = new ArrayList<>();

    public void newSession() {

        ArrayList<String> listeMot = new ArrayList<>();
        BufferedReader in = null;
        mots.clear();

        try {
            in = new BufferedReader(new FileReader("Liste_des_mots.txt"));

            //affecter tous les mots du fichier dans une liste
            int i = 0;
            String s;
            while ((s = in.readLine()) != null) {
                listeMot.add(i, s);
                i++;
            }
            //////////////////////////////////////////////////////
            // choisir dix mots aleatoire
            boolean[] choisi = new boolean[listeMot.size()];
            int j = 0;

            while (mots.size() != 10) {

                do {
                    j = ((int) (Math.random() * Math.random() * 95)) % choisi.length;
                } while (choisi[j]);

                choisi[j] = true;
                String[] motAvcIndicat = listeMot.get(j).split(";");

                System.out.println("le mot :" + motAvcIndicat[2] + " type d'indication : "
                        + Indication.find(motAvcIndicat[0]).getIndication() + "  :" + motAvcIndicat[1]);
                mots.add(new Mot(motAvcIndicat[2], Indication.find(motAvcIndicat[0]), motAvcIndicat[1])); //ajouter un mot (notre mot , Indication)
            }


        } catch (MotException e) {
            AlertBox.display("MotException", "Erreur dans le ficher des mots :\n il y a un mot qui a une longueur < 6");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Mot getMot(int i) {
        return mots.get(i);
    }

    public int getScore() {
        int score = 0;
        for (Mot mot : mots) {
            score += mot.calculerScore();
        }
        return score;
    }

}