package com.tp;

import com.tp.Graphic.Acceuille;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Random;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage window) throws Exception {
        window.setTitle("Le Pendu");
        setUserAgentStylesheet(STYLESHEET_CASPIAN);

        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            System.out.println(r.nextInt());

        }

//        Joueur joueur= new Joueur();
//        joueur.oldJoueur("fay");
//        Jeu.loser(window,joueur);
//        Jeu.winner(window,joueur);

//        Jeu.jouerSession(window,joueur);


        Acceuille.pageAcceuille(window);

    }
}
