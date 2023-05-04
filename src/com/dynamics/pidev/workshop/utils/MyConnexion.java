/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dynamics.pidev.workshop.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author FGH
 */
public class MyConnexion {
    
    private final String url = "jdbc:mysql://localhost:3306/hello?useSSL=false";
    private final String login = "root";
    private final String password = "";
    
    private Connection cnx;
    
    private static MyConnexion instance;

    private MyConnexion() {
        
        try {
                  Class.forName("com.mysql.jdbc.Driver");

                  cnx = DriverManager.getConnection(url, login, password);
            System.out.println("Connexion etablie !");
        } catch (SQLException ex) {
            System.out.println("Connexion non etablie !");
   
            System.err.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MyConnexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static MyConnexion getInstance(){
        if (instance == null) 
            instance = new MyConnexion();
        
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }
    
    
}
