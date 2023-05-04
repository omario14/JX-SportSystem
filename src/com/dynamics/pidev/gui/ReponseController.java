/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.dynamics.pidev.gui;
import com.dynamics.pidev.entites.Reponse;
import com.dynamics.pidev.services.ServiceQuestion;
import com.dynamics.pidev.services.ServiceReponse;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author omarb
 */
public class ReponseController implements Initializable {

    @FXML
    private Text reponseContenu;
    @FXML
    private Label userName;
    @FXML
    private Label createdAt;
    @FXML
    private Button upButton;
    @FXML
    private Label likeCount;
    @FXML
    private Button downButton;
    private ServiceQuestion quest;
    private ServiceReponse rep;
    private final ReponseHolder holder =new ReponseHolder() ;
 private Reponse currentReponse;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

   
    public void setData(Reponse reponse) throws ParseException {
        
        rep = new ServiceReponse();
        quest = new ServiceQuestion();
        String name = quest.usernameById(reponse.getUser_id());
        currentReponse=reponse;
        reponseContenu.setText(reponse.getContenurep());
        createdAt.setText(timeAgoCalculate(reponse.getCreated_at()));
        userName.setText(String.valueOf(name));
        likeCount.setText(String.valueOf(reponse.getLikes()));
        
        
        
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
        rep = new ServiceReponse();
        rep.updateCount(Integer.parseInt(likeCount.getText())+1, currentReponse.getId());
        likeCount.setText(String.valueOf(Integer.parseInt(likeCount.getText())+1));
        

    }
    @FXML
     void ratePostDown() {
           
        rep = new ServiceReponse();
        rep.updateCount(Integer.parseInt(likeCount.getText())-1, currentReponse.getId());
        likeCount.setText(String.valueOf(Integer.parseInt(likeCount.getText())-1));
    }

   

    
}
