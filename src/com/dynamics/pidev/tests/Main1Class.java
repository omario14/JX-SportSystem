/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dynamics.pidev.tests;


import com.dynamics.pidev.entites.Question;
import com.dynamics.pidev.entites.Reponse;
import com.dynamics.pidev.services.ServiceQuestion;
import com.dynamics.pidev.services.ServiceReponse;
import com.dynamics.pidev.workshop.utils.MyConnexion;
import java.sql.SQLException;

/**
 *
 * @author FGH
 */
public class Main1Class {
    
    public static void main(String[] args) {
        MyConnexion cn1 = MyConnexion.getInstance();
        MyConnexion cn2 = MyConnexion.getInstance();
        MyConnexion cn3 = MyConnexion.getInstance();
        MyConnexion cn4 = MyConnexion.getInstance();
        MyConnexion cn5 = MyConnexion.getInstance();
        
        System.out.println(cn1.hashCode());
        System.out.println(cn2.hashCode());
        System.out.println(cn3.hashCode());
        System.out.println(cn4.hashCode());
        System.out.println(cn5.hashCode());
        
       
         ServiceQuestion sp = new ServiceQuestion();
        
        Question q = new Question(57, "est ce qu'ilya des packs offre cet mois?","2023-03-17 03:30:25");
        
        ServiceReponse sr = new ServiceReponse();
        
        Reponse r = new Reponse (40, "oui ilya des packs offre a ne pas rater cet mois ","2023-03-17 03:30:35");
        
        try {
            sp.insertOne(q);
            
            System.out.println(sp.selectAll());
            System.out.println(sr.selectAll());
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        
    }
    
}
