package com.tp.Graphic;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class AlertBox {

    private static boolean answer;


    public static void display(String title, String message) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        window.setTitle(title);
        Label label = new Label("\n" + message);
        label.setAlignment(Pos.CENTER);
        Button bOK = new Button("OK");
        bOK.setPadding(new Insets(5, 40, 5, 40));
        bOK.setOnAction(event -> {
            window.close();
        });

        VBox vBox = new VBox(label, bOK);
        vBox.setSpacing(40);
        vBox.setAlignment(Pos.CENTER);

        window.setScene(new Scene(vBox, 350, 130));
        window.showAndWait();
    }

    public static Boolean choose(String title, String message){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        window.setTitle(title);
        Label label= new Label(message);
        label.setAlignment(Pos.CENTER);

        Button yesbutton = new Button("OUI");
        yesbutton.setPadding(new Insets(5,20,5,20));
        Button nobutton = new Button("NON");
        nobutton.setPadding(new Insets(5,20,5,20));

        yesbutton.setOnAction(event -> {
            answer=true;
            window.close();
        });

        nobutton.setOnAction(event -> {
            answer=false;
            window.close();
        });

        HBox hBox = new HBox(nobutton,yesbutton);
        hBox.setSpacing(20);
        hBox.setAlignment(Pos.CENTER);

        VBox vBox = new VBox(label,hBox);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);

        window.setScene(new Scene(vBox,300,100));
        window.showAndWait();

        return answer;
    }

}
