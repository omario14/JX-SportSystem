/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dynamics.pidev.tests;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author trabe
 */
public class Front extends Application {

    @Override
    public void start(Stage primaryStage) {

        try {

            Parent root = FXMLLoader.load(getClass().getResource("../gui/ForumQuestions.fxml"));

            Pane pane = new Pane();
            pane.setStyle("-fx-background-color: red;"); // Définition de la couleur de fond en rouge

            // Changement de la couleur de fond en bleu au clic sur le panneau
            pane.setOnMouseClicked(event -> {
                pane.setStyle("-fx-background-color: blue;"); // Définition de la couleur de fond en bleu
            });

            Scene scene = new Scene(root);

            primaryStage.setTitle("Forums Question");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
