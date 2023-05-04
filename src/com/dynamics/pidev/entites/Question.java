/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dynamics.pidev.entites;



/**
 *
 * @author trabe
 */
public class Question {

    private int id;
    private String contenu;
    private String created_at;
    private int likes;
    private int user_id;
    
     
    
    
    public Question() {
    }

    public Question(int id, String contenu, String created_at) {
        this.id = id;
        this.contenu = contenu;
        this.created_at = created_at;
    }
     public Question(String contenu, String created_at,int likes) {
       
        this.contenu = contenu;
        this.created_at = created_at;
        this.likes = likes;
    }
      public Question(String contenu, String created_at,int likes,int user_id) {
       
        this.contenu = contenu;
        this.created_at = created_at;
        this.likes = likes;
        this.user_id = user_id;
    }

    public Question(String contenu) {
        this.contenu = contenu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    
    
    

    @Override
    public String toString() {
        return "Question{" + "id=" + id + ", contenu=" + contenu + ", created_at=" + created_at + '}';
    }

    public Object getQuestionText() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
    
}
