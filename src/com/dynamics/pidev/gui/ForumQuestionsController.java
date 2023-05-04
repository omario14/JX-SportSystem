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
import java.text.ParseException;
import java.util.ArrayList;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author omarb
 */
public class ForumQuestionsController implements Initializable {

    @FXML
    private GridPane questGrid;
    @FXML
    private TextField searchField;

    private List<Question> questions;
    private ObservableList<Question> questionList;
    private ServiceQuestion quest;
    

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        questions = new ArrayList<>(qstData());

        int columns = 0;
        int rows = 1;
        try {
            for (int i = 0; i < questions.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("Question.fxml"));

                VBox questBox = fxmlLoader.load();
                QuestionController questionController = fxmlLoader.getController();
                questionController.setData(questions.get(i));
                
                try {
                    dynamicSearch();
                } catch (SQLException ex) {
                    Logger.getLogger(AfficherQuestionController.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (columns == 1) {
                    columns = 0;
                    ++rows;
                }

                questGrid.add(questBox, columns++, rows);
                GridPane.setMargin(questBox, new Insets(10, 0, 3, 10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException ex) {
            Logger.getLogger(ForumQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private List<Question> qstData() {
        quest = new ServiceQuestion();
        try {
            questionList = FXCollections.observableArrayList(quest.selectAll());
        } catch (SQLException ex) {
            Logger.getLogger(ForumQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return questionList;
    }

    @FXML
    void addQuestionButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../gui/AddQuestion.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void dynamicSearch() throws SQLException {
        quest = new ServiceQuestion();
        ObservableList<Question> questionsList = FXCollections.observableArrayList();
        questionsList.addAll(quest.selectAll());

        FilteredList<Question> filteredData = new FilteredList<>(questionsList, b -> true);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            String searchKeywords = newValue.trim().toLowerCase();
            List<Question> filteredQuestions = new ArrayList<>();

            for (Question q : questions) {
                if (q.getContenu().toLowerCase().contains(searchKeywords)) {
                    filteredQuestions.add(q);
                }
            }

            questGrid.getChildren().clear();
            int columns = 0;
            int rows = 1;

            try {
                for (int i = 0; i < filteredQuestions.size(); i++) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("Question.fxml"));

                    VBox questBox = fxmlLoader.load();
                    QuestionController questionController = fxmlLoader.getController();
                    questionController.setData(filteredQuestions.get(i));

                    if (columns == 1) {
                        columns = 0;
                        ++rows;
                    }

                    questGrid.add(questBox, columns++, rows);
                    GridPane.setMargin(questBox, new Insets(10, 0, 3, 10));
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ParseException ex) {
                Logger.getLogger(ForumQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
    }

}
