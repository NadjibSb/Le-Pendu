package com.tp.Graphic;

import com.tp.Cases.Proposition;
import com.tp.Exceptions.NbrTentativesException;
import com.tp.Joueur;
import com.tp.Mot;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Jeu {

    private static int nbrMotsRates= 0;

    public static void jouerSession(Stage window, Joueur joueur) {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color : #CCCCCC ");
        nbrMotsRates= 0;

        //closing request
        window.setOnCloseRequest(event -> {
            event.consume();
            if (AlertBox.choose("Confirmation", "Voulez vous vraiment quiter le jeu ?")) {
                //enregistrer le joueur
                joueur.enregistrer();

                window.close();
            }
        });

        /////////////////////////////////////////////  TOP  ///////////////////////////////////////
        /////////////////////MenuBar///////////////////////
        root.setTop(menuBar(window, joueur));
        //////////////////////////////////////////////////////////////////////////////////////////


        /////////////////////////////////////////////  RIGHT  ///////////////////////////////////////
        ///////////////////liste des mots/////////////////////
        root.setLeft(listMots());
        //////////////////////////////////////////////////////////////////////////////////////////


        /////////////////////////////////////////////  Center  ///////////////////////////////////////
        BorderPane centre = new BorderPane();

        /////////////////////info de joueur/////////////////////////////
        /////Pseudo
        Label tname = new Label("Pseudo");
        tname.setFont(Font.font("AR BERKLEY", 28));
        Label vname = new Label(joueur.getPseudo());
        vname.setStyle("-fx-text-fill : #444444");
        vname.setFont(Font.font("Algerian", 20));
        VBox name = new VBox(10, tname, vname);
        name.setAlignment(Pos.CENTER);

        /////Best score
        Label tbestscore = new Label("Best Score");
        tbestscore.setFont(Font.font("AR BERKLEY", 28));
        Label vbestscore = new Label(Integer.toString(joueur.bestScore()));
        vbestscore.setStyle("-fx-text-fill : #444444");
        vbestscore.setFont(Font.font("AR BERKLEY", 20));
        VBox bestscore = new VBox(10, tbestscore, vbestscore);
        bestscore.setAlignment(Pos.CENTER);

        ////Score
        Label tscoreActuel = new Label("Score");
        tscoreActuel.setFont(Font.font("AR BERKLEY", 28));
        Label vscoreActuel = new Label(Integer.toString(joueur.getSession().getScore()));
        vscoreActuel.setStyle("-fx-text-fill : #444444");
        vscoreActuel.setFont(Font.font("AR BERKLEY", 20));
        VBox scoreActuel = new VBox(10, tscoreActuel, vscoreActuel);
        scoreActuel.setAlignment(Pos.CENTER);


        HBox info = new HBox(name, bestscore, scoreActuel);
        info.setAlignment(Pos.TOP_CENTER);
        info.setSpacing(200);
        info.setPadding(new Insets(15, 5, 5, 10));
        centre.setTop(info);


        //////////////////l'image du pendu///////////////////////////
        ImageView pendu = new ImageView(new Image("img/pendu/0.png",250,250,false,false,false));
        centre.setCenter(pendu);

        /////////////////zone du mot joué/////////////////////////
        centre.setBottom(motJoue(window,root, joueur, 0));
        //////////////////////////////////////////////////////////////////

        root.setCenter(centre);
        ///////////////////////////////////////////////////////////////////////////////////////////////////


        window.setScene(new Scene(root, 1300, 690));
        window.setMinWidth(1100);
        window.setMinHeight(720);
        window.show();
    }

    private static Node menuBar(Stage window, Joueur joueur) {
        Menu info = new Menu("Options");
        Menu undo = new Menu("Undo");

        MenuItem scores = new MenuItem("Afficher mes scores");
        scores.setOnAction(event -> scores(joueur));


        MenuItem save = new MenuItem("Enregistrer mon score actuel");
        save.setOnAction(event -> joueur.getScores().add(joueur.getSession().getScore()));

        MenuItem help = new MenuItem("Help");
        help.setOnAction(event -> {
            help();
        });

        MenuItem acceuille = new MenuItem("revenir à la page d'acceuil");
        acceuille.setOnAction(event -> {
            if (AlertBox.choose("Confirmation", "Voulez vous vraiment quiter la session ?")) {
                //enregistrer le joueur
                joueur.enregistrer();

                Acceuille.pageAcceuille(window);
            }
        });

        MenuItem quiter = new MenuItem("Quiter");
        quiter.setOnAction(event -> {
            if (AlertBox.choose("Confirmation", "Voulez vous vraiment quiter le jeu ?")) {
                //enregistrer le joueur
                joueur.enregistrer();

                window.close();
            }
        });

        info.getItems().addAll(scores,save,new SeparatorMenuItem(),help);
        undo.getItems().addAll(acceuille,quiter);

        return new MenuBar(info,undo);
    }

    private static Node listMots() {

        VBox listMots = new VBox();
        for (int i = 0; i < 10; i++) {
            ImageView imageView = new ImageView(new Image("img/" + i + "/mot.png", 160, 40, true, true, true));
            listMots.getChildren().add(i, imageView);
        }
        listMots.setAlignment(Pos.CENTER);
        listMots.setSpacing(25);
        listMots.setPadding(new Insets(30, 30, 30, 30));

        return listMots;
    }

    private static Node motJoue(Stage window,BorderPane root, Joueur joueur, int numMot) {

        Mot mot = joueur.getSession().getMot(numMot);

        //Agrendir l'image du mot joué
        getImageMot(root, numMot).setImage(new Image("img/" + numMot + "/mot_enCour.png", 180, 60, true, true, true));

        //affichage score mot
        Label tscoreMot = new Label("Score Mot");
        tscoreMot.setFont(Font.font("AR BERKLEY", 17));
        Label vscoreMot = new Label("00");
        vscoreMot.setFont(Font.font("AR BERKLEY", 14));
        VBox scoreMot = new VBox( tscoreMot, vscoreMot);
        scoreMot.setAlignment(Pos.CENTER);

        //affichage d'indication
        Label indication = new Label(mot.getIndication().getIndication() + " : " + mot.getMessage());
        indication.setFont(Font.font("Baskerville Old Face", 15));

        //la liste des lettres
        HBox listLettres = new HBox();

        for (int i = 0; i < mot.getMot().length(); i++) {
//            Button bLettre = new Button(mot.getCase(i).getClass().getSimpleName());
            ImageView bLettre = new ImageView(new Image("img/button/"+mot.getCase(i).getClass().getSimpleName()+".png",80,80,false,false,false));
            Label tLettre = new Label(" ");
            VBox vbLettre = new VBox(tLettre, bLettre);
            vbLettre.setSpacing(10);
            vbLettre.setAlignment(Pos.CENTER);
            listLettres.getChildren().add(vbLettre);

            //event du chaque button de lettre
            int n = i;
            bLettre.setOnMouseClicked(event -> {
                //jouer tant que il a des chances et il n'a pas trouver la lettre
                while (mot.getCase(n).getChances() > 0 && !mot.getCase(n).isTrouve()) {
                    char c;
                    if (mot.getCase(n) instanceof Proposition)
                        c = Clavier.display(((Proposition) mot.getCase(n)).getPropositions());
                    else c = Clavier.displayAll();

                    try {
                        if (mot.getCase(n).validerLettre(c)) {
                            tLettre.setText(mot.getCase(n).getLettre() + "");
                        } else
                            AlertBox.display("Lettre ratée", "Il vous reste " + mot.getCase(n).getChances() + " chanses");

                    } catch (NbrTentativesException e) {
                        AlertBox.display("NbrTentativesException", "Vous avez consommés les chanses que vous avez pour cette lettre");
                    }

                    vscoreMot.setText(Integer.toString(mot.calculerScore()));
                }
                bLettre.setDisable(true);

                //si il a gagner le mot ou il a trompé dans une lettre (rater le mot)
                if (!mot.getCase(n).isTrouve() || mot.motTrouve()) {
                    if (mot.motTrouve()) {
                        //changer l'image du mot gagné
                        getImageMot(root, numMot).setImage(new Image("img/" + numMot + "/mot_reussi.png", 160, 40, true, true, true));
                    } else {
                        //changer l'image du mot raté
                        getImageMot(root, numMot).setImage(new Image("img/" + numMot + "/mot_rate.png", 160, 40, true, true, true));
                        nbrMotsRates++;
                        getImagePendu(root).setImage(new Image("img/pendu/"+nbrMotsRates+".png"));

                    }
                    //mise à jour du score
                    getScoreLabel(root).setText(Integer.toString(joueur.getSession().getScore()));

                    if (nbrMotsRates >=6){   //partie e
                        loser(window,joueur);

                    }else if (numMot < 9)  //passer au mot suivant
                        ((BorderPane) root.getCenter()).setBottom(motJoue(window,root, joueur, numMot + 1));

                    else {            //partie gagnée
                        winner(window,joueur);
                    }

                }
            });
        }
        listLettres.setAlignment(Pos.BOTTOM_CENTER);
        listLettres.setSpacing(10);

        VBox zoneMot = new VBox(20, scoreMot, indication, listLettres);
        zoneMot.setAlignment(Pos.TOP_CENTER);
        zoneMot.setPadding(new Insets(10, 5, 50, 5));
        return zoneMot;
    }


    public static void winner(Stage windowParent,Joueur joueur){

        //enregister le joueur et son score
        joueur.getScores().add(joueur.getSession().getScore());
        joueur.enregistrer();

        ///////////////////scene/////////////////////
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        window.setTitle("Partie Gagnée");

        Label label1 = new Label("Congratulations");
        label1.setFont(Font.font("AR BERKLEY", 100));
        label1.setStyle("-fx-text-fill: #DD1010");

        Label label2 = new Label("Tu as gagné cette partie");
        label2.setFont(Font.font("AR BERKLEY", 30));
        label2.setStyle("-fx-text-fill: #111199");

        Label label3 = new Label("\nVotre Score :");
        label3.setFont(Font.font("AR BERKLEY", 15));

        Label label4 = new Label(Integer.toString(joueur.getSession().getScore()));
        label4.setFont(Font.font("AR BERKLEY", 15));

        Button rejouer = new Button("Jouer une autre partie");
        rejouer.setPadding(new Insets(5,40,5,40));
        Button back = new Button("Retourner à la page d'acceuille");
        back.setPadding(new Insets(5,40,5,40));

        rejouer.setOnAction(event -> {
            joueur.getSession().newSession();
            Jeu.jouerSession(windowParent,joueur);
            window.close();
        });
        back.setOnAction(event -> {
            Acceuille.pageAcceuille(windowParent);
            window.close();
        });
        VBox labels = new VBox(label1,label2,label3,label4);
        labels.setSpacing(20);
        labels.setAlignment(Pos.CENTER);


        VBox buttons = new VBox(rejouer,back);
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(15);


        VBox vBox = new VBox(labels,buttons);
        vBox.setSpacing(100);
        vBox.setAlignment(Pos.CENTER);

        window.setScene(new Scene(vBox,700, 500));
        window.showAndWait();

    }

    public static void loser(Stage windowParent,Joueur joueur){

        //enregister le joueur et son score
        joueur.getScores().add(joueur.getSession().getScore());
        joueur.enregistrer();

        ///////////////////scene/////////////////////
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        window.setTitle("Partie Perdu");

        Label label1 = new Label("Vous avez perdu cette partie");
        label1.setFont(Font.font("AR BERKLEY", 25));

        ImageView pendu = new ImageView(new Image("img/pendu/6.png",300,300,false,false,false));

        Label label3 = new Label("\nVotre Score :");
        label3.setFont(Font.font("AR BERKLEY", 15));

        Label label4 = new Label(Integer.toString(joueur.getSession().getScore()));
        label4.setFont(Font.font("AR BERKLEY", 15));

        Button rejouer = new Button("Jouer une autre partie");
        rejouer.setPadding(new Insets(5,40,5,40));
        Button back = new Button("Retourner à la page d'acceuil");
        back.setPadding(new Insets(5,40,5,40));

        rejouer.setOnAction(event -> {
            joueur.getSession().newSession();
            Jeu.jouerSession(windowParent,joueur);
            window.close();
        });
        back.setOnAction(event -> {
            Acceuille.pageAcceuille(windowParent);
            window.close();
        });

        VBox labels = new VBox(label1,pendu,label3,label4);
        labels.setAlignment(Pos.CENTER);
        labels.setSpacing(10);


        VBox buttons = new VBox(rejouer,back);
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(10);


        VBox vBox = new VBox(labels,buttons);
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);

        window.setScene(new Scene(vBox,700, 510));
        window.showAndWait();

    }


    private static ImageView getImageMot(BorderPane root, int numMot) {
        return (ImageView) ((VBox) root.getLeft()).getChildren().get(numMot);
    }

    private static Label getScoreLabel(BorderPane root) {
        return ((Label) ((VBox) ((HBox) ((BorderPane) root.getCenter()).getTop()).getChildren().get(2)).getChildren().get(1));
    }

    private static ImageView getImagePendu(BorderPane root) {
        return ((ImageView) ((BorderPane) root.getCenter()).getCenter());
    }

    private static void scores(Joueur joueur){
        Stage window = new Stage();

        Label pseudo = new Label(joueur.getPseudo().toUpperCase());
        pseudo.setFont(Font.font("ROBOTO", 19));

        Label score = new Label("Best score :  "+joueur.bestScore()+"\n"+"Liste des scores : "+joueur.getScores().toString());
        score.setFont(Font.font("ROBOTO", 13));
        score.setLineSpacing(20);

        VBox vBox = new VBox(pseudo,score);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(70);

        window.setScene(new Scene(vBox, 400, 250));
        window.show();
    }


    private static void help(){
        Stage window = new Stage();

        Label help = new Label("Help");
        help.setFont(Font.font("ROBOTO", 19));

        Label tHelp = new Label( " Le joueur peut avoir un score négatif pour un mot donné.\n" +
                "Un raté dans une case implique le passage vers le mot suivant, et on garde son score.\n" +
                "Concernant la case multichance, on dit qu’on a raté si on donne la réponse fausse 3 fois.\n" +
                "Si on clique sur une case multichance et échoue une fois, on ne peut pas choisir une autre case\n" +
                "que si on consomme les chances ou on trouve la lettre.\n" +
                "Si le nombre des cases malus (multi-chances et propositions) est supérieure strictement à 5 (6 ou plus) .\n" +
                "on applique le malus sur un mot trouvé ou raté!\n" +
                "Voici quelques cas où on n’applique pas le malus (afin de vérifier vos programmes):\n" +
                "       Le mot contient 7 cases avec 3 zéroChances.\n" +
                "       Le mot contient 7 cases avec 2 zéroChances.\n" +
                "       Le mot contient 8 cases avec 3 zéroChances.\n");
        tHelp.setFont(Font.font("ROBOTO", 12));
        tHelp.setLineSpacing(10);

        VBox vBox = new VBox(help,tHelp);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(50);

        window.setScene(new Scene(vBox, 600, 400));
        window.show();
    }

}