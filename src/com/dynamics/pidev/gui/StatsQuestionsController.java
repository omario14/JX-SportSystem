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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author omarb
 */
public class StatsQuestionsController implements Initializable {

    @FXML
    private BorderPane borderPane;

    private ServiceQuestion quest;
    private QuestHolder holder = QuestHolder.getInstance();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            pieChart(null);
        } catch (SQLException ex) {
            Logger.getLogger(StatsQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    @FXML
    private void pieChart(ActionEvent event) throws SQLException {
        quest = new ServiceQuestion();

        // create Data
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                quest.selectByLikes().stream()
                        .map(d -> new PieChart.Data("Question Id : " + String.valueOf(d.getId()), d.getLikes()))
                        .collect(Collectors.toList())
        );

        PieChart pieChart = new PieChart(pieChartData);
        pieChart.setTitle("Questions les plus interagies");
        pieChart.setClockwise(true);
        pieChart.setLabelLineLength(50);
        pieChart.setLabelsVisible(true);
        pieChart.setStartAngle(180);

        //add bar chart to borderPane
        borderPane.setCenter(pieChart);
        for (PieChart.Data data : pieChart.getData()) {
             data.getNode().setOnMouseEntered(ev -> {
        borderPane.setCursor(Cursor.HAND);
    });
    data.getNode().setOnMouseExited(ev -> {
        borderPane.setCursor(Cursor.DEFAULT);
    });
            Node node = data.getNode();
            Tooltip tooltip = new Tooltip();
            // set the style of the tooltip
            tooltip.setStyle("-fx-font-size: 16px;");
            
            tooltip.setText(String.format("%d interactions", (int) data.getPieValue()));
            Tooltip.install(node, tooltip);
            
            data.getNode().setOnMouseClicked(e -> {
                try {
                    afficherQuestion(e, Integer.parseInt(data.getName().substring(14) ));
                } catch (SQLException ex) {
                    Logger.getLogger(StatsQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(StatsQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }
    }

    @FXML
    void afficherQuestion(MouseEvent event, int id) throws SQLException, IOException {
        quest = new ServiceQuestion();
        Question selectedQuestion = quest.selectById(id);
       
        if (selectedQuestion != null) {
            holder.setQuest(selectedQuestion);
            Parent root = FXMLLoader.load(getClass().getResource("../gui/ModifierQuestion.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            //loadUsers();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Pas de Question séléctionnée");
            alert.setContentText("S'il vous plait de séléctionner une question");
            alert.showAndWait();
        }

    }

}
