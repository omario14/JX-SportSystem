/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.dynamics.pidev.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import com.dynamics.pidev.entites.Question;
import com.dynamics.pidev.services.ServiceQuestion;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author omarb
 */
public class QuestionController implements Initializable {

    @FXML
    private Button upButton,downButton;
    @FXML
    private Text questContenu;
    @FXML
    private Label userName;
    @FXML
    private Label likeCount;
    @FXML
    private Label createdAt;
    @FXML
    private Button btnReply;
    @FXML
    private Label replyCount;

    private ServiceQuestion quest;
     private  QuestHolder holderQuest = QuestHolder.getInstance();
     private  QuestHolder holder = QuestHolder.getInstance();
    private Question CurrentQuestion = holder.getQuest();
    private Question currentQuestion;

    public void setData(Question question) throws ParseException {
        quest = new ServiceQuestion();
        String name = quest.usernameById(question.getUser_id());
        currentQuestion=question;
        
        questContenu.setText(question.getContenu());
        createdAt.setText(timeAgoCalculate(question.getCreated_at()));
        likeCount.setText(String.valueOf(question.getLikes()));
        userName.setText(String.valueOf(name));
        replyCount.setText(String.valueOf(quest.countReponses(question.getId())));
 
    }

    private String timeAgoCalculate(String d) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date postDateTime = format.parse(d);

        Date currentDateTime = new Date();
       
        long seconds = TimeUnit.MILLISECONDS.toSeconds(currentDateTime.getTime() - postDateTime.getTime());

        if (seconds < 60) {
            return seconds + " secondes ago";
        } else if (seconds < 3600) {
            long minutes = seconds / 60;
            
            return minutes + " minutes ago";
        } else if (seconds < 86400) {
            long hours = seconds / 3600;
            
            return hours + " heures ago";
        } else {
            long days = seconds / 86400;
            
            return days + " jours ago";
        }

    }

    @FXML
    void ratePostUp() {    
        quest = new ServiceQuestion();
        quest.updateCount(Integer.parseInt(likeCount.getText())+1, currentQuestion.getId());
        likeCount.setText(String.valueOf(Integer.parseInt(likeCount.getText())+1));

    }
    @FXML
     void ratePostDown() {
           
        quest = new ServiceQuestion();
        quest.updateCount(Integer.parseInt(likeCount.getText())-1, currentQuestion.getId());
        likeCount.setText(String.valueOf(Integer.parseInt(likeCount.getText())-1));
    }
     
     @FXML
        void afficherReponse(ActionEvent event) throws IOException {
            
        holder.setQuest(currentQuestion);
        Parent root = FXMLLoader.load(getClass().getResource("../gui/AddReponse.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        
    }
}
