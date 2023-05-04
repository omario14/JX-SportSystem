/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.dynamics.pidev.gui;

import com.dynamics.pidev.entites.Question;
import com.dynamics.pidev.entites.Reponse;
import com.dynamics.pidev.services.ServiceQuestion;
import com.dynamics.pidev.services.ServiceReponse;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author trabe
 */
public class AfficherReponseController implements Initializable {


    private ObservableList<Reponse> reponseList;
    private ServiceReponse resp;
    private ServiceQuestion quest;
    private ReponseHolder holderRep = ReponseHolder.getInstance();
    private  QuestHolder holder = QuestHolder.getInstance();
    private Question CurrentQuestion = holder.getQuest();
    @FXML
    private TableView<Reponse> tableReponse;
    @FXML
    private TableColumn<Reponse, String> datedecreationColumn;
    @FXML
    private TableColumn<Reponse, String> repColumn;
    @FXML
    private TableColumn<Reponse, Integer> interactionColumn1;
    @FXML
    private TableColumn<Reponse, Integer> userName;
    @FXML
    private TextField searchField;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        resp = new ServiceReponse();
        quest = new ServiceQuestion();
       
        try {
            
            reponseList = FXCollections.observableArrayList(resp.selectByQuestion(CurrentQuestion.getId()));
           
        } catch (SQLException ex) {
            Logger.getLogger(AfficherQuestionController.class.getName()).log(Level.SEVERE, null, ex);
        }

        datedecreationColumn.setCellValueFactory(new PropertyValueFactory<>("created_at"));
        repColumn.setCellValueFactory(new PropertyValueFactory<>("contenurep"));
        interactionColumn1.setCellValueFactory(new PropertyValueFactory<>("likes"));
        userName.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        tableReponse.setItems(reponseList);
        try {
            dynamicSearch();
        } catch (SQLException ex) {
            Logger.getLogger(AfficherQuestionController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void dynamicSearch() throws SQLException {
        resp = new ServiceReponse();
        ObservableList<Reponse> reponseList = FXCollections.observableArrayList();
        reponseList.addAll(resp.selectByQuestion(CurrentQuestion.getId()));

        FilteredList<Reponse> filteredData = new FilteredList<>(reponseList, b -> true);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(g -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (g.getCreated_at().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;

                } else if (g.getContenurep().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;

                } else {
                    return false;
                }

            });
        });
        SortedList<Reponse> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableReponse.comparatorProperty());
        tableReponse.setItems(sortedData);

    }
    
    @FXML
        void switchButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../gui/ModifierQuestion.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

   
}
