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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author trabe
 */
public class AddReponseController implements Initializable {

    @FXML
    private TextArea tfReponse;
    @FXML
    private GridPane reponseGrid;
    @FXML
    private TextField searchFieldResp;
    
    private List<Reponse> reponses;
    private ObservableList<Reponse> reponsesList;
    private ServiceReponse resp;
    private ServiceQuestion quest;
    private final QuestHolder holder = QuestHolder.getInstance();
    private Question CurrentQuestion = holder.getQuest();
    
    private Question questionHol = new Question();

    /**
     * Initializes the controller class.
     */
     @Override
    public void initialize(URL url, ResourceBundle rb) {
         
      this.setInit();
      
      questionHol = CurrentQuestion;
    }
    
   private void setInit(){
         
        reponses = new ArrayList<>(qstData());

        int columns = 0;
        int rows = 1;
        try {
            for (int i = 0; i < reponses.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("Reponse.fxml"));

                VBox questBox = fxmlLoader.load();
                ReponseController reponseController = fxmlLoader.getController();
                reponseController.setData(reponses.get(i));
                try {
                    dynamicSearch();
                } catch (SQLException ex) {
                    Logger.getLogger(AfficherReponseController.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (columns == 1) {
                    columns = 0;
                    ++rows;
                }

                reponseGrid.add(questBox, columns++, rows);
                GridPane.setMargin(questBox, new Insets(10, 0, 3, 10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException ex) {
            Logger.getLogger(AddReponseController.class.getName()).log(Level.SEVERE, null, ex);
        }
   }

    private List<Reponse> qstData() {
        resp = new ServiceReponse();
        try {
             
            reponsesList = FXCollections.observableArrayList(resp.selectByQuestion(CurrentQuestion.getId()));
            
        } catch (SQLException ex) {
            Logger.getLogger(AddReponseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reponsesList;
    } 
    
    
    
    /**************Ajouter reponse*******************/
           @FXML
    void clickMe(ActionEvent event) {
    
        if (tfReponse.getText().isEmpty()) {
            Alert al = new Alert(Alert.AlertType.WARNING);
            al.setTitle("Erreur de donnee");
            al.setContentText("Veuillez verifier les données !");
            al.show();
        }else{
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String strDate = formatter.format(date);
           
            Reponse r = new Reponse( tfReponse.getText(), strDate, 29,questionHol.getId());
           
            ServiceReponse sp = new ServiceReponse();
            
            try {
                sp.insertOne1(r);
                tfReponse.clear();
               this.setInit();
            } catch (SQLException ex) {
                Alert al = new Alert(Alert.AlertType.ERROR);
                al.setTitle("Erreur de donnee");
                al.setContentText(ex.getMessage());
                al.show();
            }
            
      
        }
    }
     private void dynamicSearch() throws SQLException {
        resp = new ServiceReponse();
        ObservableList<Reponse> responseList = FXCollections.observableArrayList();
        responseList.addAll(resp.selectByQuestion(CurrentQuestion.getId()));

        FilteredList<Reponse> filteredData = new FilteredList<>(responseList, b -> true);

        searchFieldResp.textProperty().addListener((observable, oldValue, newValue) -> {
            String searchKeywords = newValue.trim().toLowerCase();
            List<Reponse> filteredResponses = new ArrayList<>();

            for (Reponse q : reponses) {
                if (q.getContenurep().toLowerCase().contains(searchKeywords)) {
                    filteredResponses.add(q);
                }
            }

            reponseGrid.getChildren().clear();
            int columns = 0;
            int rows = 1;

            try {
                for (int i = 0; i < filteredResponses.size(); i++) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("Reponse.fxml"));

                    VBox questBox = fxmlLoader.load();
                    ReponseController reponseController = fxmlLoader.getController();
                    reponseController.setData(filteredResponses.get(i));

                    if (columns == 1) {
                        columns = 0;
                        ++rows;
                    }

                    reponseGrid.add(questBox, columns++, rows);
                    GridPane.setMargin(questBox, new Insets(10, 0, 3, 10));
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ParseException ex) {
                Logger.getLogger(AddReponseController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
    }
     
     @FXML
        void backButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../gui/ForumQuestions.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
