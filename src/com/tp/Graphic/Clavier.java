package com.tp.Graphic;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Collection;


public class Clavier {

    private static char lettre;

    public static char displayAll() {

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Clavier");

        Label label = new Label("\nChoisissez une lettre :");

        //////////////////clavier///////////////////////
        HBox hBox1 = new HBox();
        hBox1.setSpacing(10);
        hBox1.setAlignment(Pos.CENTER);
        HBox hBox2 = new HBox();
        hBox2.setSpacing(10);
        hBox2.setAlignment(Pos.CENTER);
        HBox hBox3 = new HBox();
        hBox3.setSpacing(10);
        hBox3.setAlignment(Pos.CENTER);
        HBox h;
        for (int i = 65; i <= 90; i++) {
            h = hBox1;
            if (i > 84) h = hBox3;
            else if (i > 74) h = hBox2;
            Button b = new Button(((char) i) + "");
            h.getChildren().add(b);
            char c = (char) i;
            b.setOnAction(event -> {
                lettre = c;
                window.close();
            });
        }
        VBox clav = new VBox(hBox1, hBox2, hBox3);
        clav.setSpacing(10);
        /////////////////////////////////////////////////

        VBox vBox = new VBox(label, clav);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(30);

        window.setScene(new Scene(vBox, 400, 200));
        window.showAndWait();

        return lettre;
    }


    public static char display(Collection<Character> caract) {

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Clavier");

        Label label = new Label("Choisissez une lettre :");

        //////////////////clavier///////////////////////
        HBox hBox1 = new HBox();
        hBox1.setSpacing(20);
        hBox1.setAlignment(Pos.CENTER);

        for (char c : caract) {
            Button b = new Button(c + "");
            hBox1.getChildren().add(b);
            b.setOnAction(event -> {
                lettre = c;
                window.close();
            });
        }

        VBox vBox = new VBox(label, hBox1);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(30);

        window.setScene(new Scene(vBox, 350, 150));
        window.showAndWait();

        return lettre;
    }
}
