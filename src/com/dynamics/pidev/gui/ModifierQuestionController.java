/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.dynamics.pidev.gui;

import com.dynamics.pidev.entites.Question;
import com.dynamics.pidev.services.ServiceQuestion;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author omarb
 */
public class ModifierQuestionController implements Initializable {

    @FXML
    private DatePicker dateCreationInput;
    @FXML
    private TextArea contenuInput;
    @FXML
    private Text likesField;

    private ServiceQuestion questionService;
    
    private  QuestHolder holder = QuestHolder.getInstance();
    private Question CurrentQuestion = holder.getQuest();

    /**
     * Initializes the controller class.
     */
    public void initData(Question quest) {
        CurrentQuestion = quest;
        holder.setQuest(CurrentQuestion);
        
        LocalDateTime createdConverted = LocalDateTime.parse(CurrentQuestion.getCreated_at(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        dateCreationInput.setValue(createdConverted.toLocalDate());
        contenuInput.setText(CurrentQuestion.getContenu());
        likesField.setText(String.valueOf(CurrentQuestion.getLikes())+" interactions");

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        this.initData(CurrentQuestion);

    }
    
    @FXML
    public void modifierQuestion(ActionEvent event) {

        try {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String createdAt = dateCreationInput.getValue().format(formatter);
            String contenus = contenuInput.getText();

            if (createdAt.isEmpty() || contenus.isEmpty()) {
                Alert al = new Alert(Alert.AlertType.WARNING);
                al.setTitle("Erreur");
                al.setContentText("Veuillez remplir tous les champs !");
                al.show();
                return;
            }
            if (contenus.length() < 3 || contenus.length() > 200) {
                Alert al = new Alert(Alert.AlertType.WARNING);
                al.setTitle("Erreur de données");
                al.setContentText("Le champ 'Contenus' doit contenir entre 3 et 200 caractères !");
                al.show();
                return;
            }

            CurrentQuestion.setCreated_at(createdAt);
            CurrentQuestion.setContenu(contenus);

            ServiceQuestion service = new ServiceQuestion();
            service.updateQuestion(CurrentQuestion, CurrentQuestion.getId());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Modification de Question");
            alert.setHeaderText(null);
            alert.setContentText("La Question a été modifiée avec succès !");
            alert.showAndWait();
            Parent root = FXMLLoader.load(getClass().getResource("../gui/AfficherQuestion.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Une erreur est survenue lors de la modification de la Question !");
            alert.showAndWait();
        }
    }

    
    @FXML
        void switchButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../gui/AfficherQuestion.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    
    
    
    @FXML
    public void afficherReponses(ActionEvent event) throws IOException{
        
        holder.setQuest(CurrentQuestion);
        Parent root = FXMLLoader.load(getClass().getResource("../gui/AfficherReponse.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
}
