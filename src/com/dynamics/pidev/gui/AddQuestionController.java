/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.dynamics.pidev.gui;

import com.dynamics.pidev.entites.Question;
import com.dynamics.pidev.services.ServiceQuestion;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author trabe
 */
public class AddQuestionController implements Initializable {

    @FXML
    private TextArea tfQuestion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    void clickMe(ActionEvent event) throws IOException {
        System.out.println("on envoyer taped..");

        if (tfQuestion.getText().isEmpty()) {
            Alert al = new Alert(Alert.AlertType.WARNING);
            al.setTitle("Erreur de donnee");
            al.setContentText("Veuillez verifier les données !");
            al.show();
        } else {
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String strDate = formatter.format(date);
            
            /*********** TODO : Replace userID (32) By GetCurrentUser ************/
            Question p = new Question(tfQuestion.getText(), strDate, 0,32);
            ServiceQuestion sp = new ServiceQuestion();

            try {
                sp.insertOne1(p);
                Parent root = FXMLLoader.load(getClass().getResource("../gui/ForumQuestions.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (SQLException ex) {
                Alert al = new Alert(Alert.AlertType.ERROR);
                al.setTitle("Erreur de donnee");
                al.setContentText(ex.getMessage());
                al.show();
            }

        }
    }
    //@FXML
    //private void AfficherPerson(ActionEvent event) {

    //try {
    //FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherPersonsFXML.fxml"));
    //Parent root = loader.load();
    // AfficherQuestionController afPFXC = loader.getController();
    // List<Question> list = new ServiceQuestion().selectAll();
    // String s = list.toString();
    // afPFXC.setListView(s);
    //Step 1 = Par scene
//            tfNom.getScene().setRoot(root);
    //Step 2 = Par Stage
    // Stage st = new Stage();
    //st.setTitle("");
    // st.setScene(new Scene(root));
    //st.show();
    // } catch (IOException ex) {
    // System.err.println(ex.getMessage());
    // } catch (SQLException ex) {
    // System.err.println(ex.getMessage());
    // }catch(Exception ex){
    // ex.printStackTrace();
    //  }
    // }
    
    @FXML
        void backButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../gui/ForumQuestions.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}
