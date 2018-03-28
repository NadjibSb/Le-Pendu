package com.tp.Graphic;

import com.tp.Exceptions.JoueurException;
import com.tp.Exceptions.PseudoException;
import com.tp.Joueur;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Connexion {


    public static void oldAccount(Stage window) {

        /////////////////////Scene/////////////////////////////
        Label label = new Label("Entrez votre Pseudo : ");

        TextField textField = new TextField();
        textField.setPromptText("Pseudo");

        HBox hBox = new HBox(label, textField);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(30);

        ////////////////////button///////////////////////////////
        Button bConnexion = new Button("Se connecter");
        bConnexion.setPadding(new Insets(5, 50, 5, 50));

        bConnexion.setOnAction(event -> {
            Joueur joueur = new Joueur();
            try {
                joueur.oldJoueur(textField.getText());
                Jeu.jouerSession(window, joueur);
            } catch (JoueurException e) {
                textField.clear();
                AlertBox.display("JoueurException", "vous n'etes pas inscris");
            } catch (PseudoException e) {
                textField.clear();
                AlertBox.display("PseudoException", "Pseudo non valide");
            }
        });

        VBox vBox = new VBox(hBox, bConnexion);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(30);


        BorderPane root = new BorderPane();
        root.setCenter(vBox);
        root.setTop(menuBar(window));

        window.setScene(new Scene(root, 800, 500));
        window.show();
    }

    public static void newAccount(Stage window) {

        /////////////////////Scene/////////////////////////////
        Label label = new Label("Entrez votre Pseudo : ");

        TextField textField = new TextField();
        textField.setPromptText("Pseudo");

        HBox hBox = new HBox(label, textField);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(30);

        ////////////////////button///////////////////////////////
        Button bInscription = new Button("S'inscrire");
        bInscription.setPadding(new Insets(5, 50, 5, 50));

        bInscription.setOnAction(event -> {
            Joueur joueur = new Joueur();
            try {
                joueur.newJoueur(textField.getText());
                Jeu.jouerSession(window, joueur);
            } catch (JoueurException e) {
                textField.clear();
                AlertBox.display("JoueurException", "ce  Pseudo existe déjà");
            } catch (PseudoException e) {
                textField.clear();
                AlertBox.display("PseudoException", "Pseudo non valide");
            }
        });


        VBox vBox = new VBox(hBox, bInscription);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(30);

        BorderPane root = new BorderPane();
        root.setTop(menuBar(window));
        root.setCenter(vBox);

        window.setScene(new Scene(root, 800, 500));
        window.show();
    }

    private static Node menuBar(Stage window) {
        Menu menu = new Menu("Undo");
        MenuItem undo = new MenuItem("revenir à la page d'acceuil");
        undo.setOnAction(event -> {
            Acceuille.pageAcceuille(window);
        });
        menu.getItems().add(undo);

        return new MenuBar(menu);
    }
}
