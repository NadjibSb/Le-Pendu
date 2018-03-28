package com.tp.Graphic;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class Acceuille {

    public static void pageAcceuille(Stage window) {

        /////////////////////////button///////////////////////////////
        Button bInscription = new Button("Inscription");
        Button bConnexion = new Button("Connexion");

        bInscription.setPadding(new Insets(5, 20, 5, 20));
        bInscription.setTextFill(Color.BLACK);
        bConnexion.setPadding(new Insets(5, 20, 5, 20));
        bConnexion.setTextFill(Color.BLACK);

        bInscription.setOnAction(event -> {
            System.out.println("Inscription");
            Connexion.newAccount(window);
        });
        bConnexion.setOnAction(event -> {
            System.out.println("Connexion");
            Connexion.oldAccount(window);

        });

        ///////////////////////Scene///////////////////////////////////
        ImageView imageView = new ImageView(new Image("img/logo.png", 250, 250, true, true, true));

        Label pendu = new Label("Le Pendu");
        pendu.setFont(Font.font("AR BERKLEY", 90));

        HBox hBoxPendu = new HBox(imageView,pendu);
        hBoxPendu.setAlignment(Pos.CENTER);
        hBoxPendu.setSpacing(30);

        HBox hBox = new HBox(bInscription, bConnexion);

        hBox.setSpacing(100);
        hBox.setAlignment(Pos.CENTER);

        VBox vBox = new VBox(hBoxPendu, hBox);
        vBox.setSpacing(70);
        vBox.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();
        root.setCenter(vBox);


        window.setScene(new Scene(root, 800, 500));
        window.setMinHeight(400);
        window.setMinWidth(400);

        window.setHeight(500);
        window.setWidth(800);

        window.show();
    }
}
