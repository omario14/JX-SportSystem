/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dynamics.pidev.entites;

/**
 *
 * @author trabe
 */
public class Reponse {
    private int id;
    private String rep;
    private String created_at;
    private int user_id;
    private int question_id;
    private int likes;

    public Reponse() {
    }

    public Reponse(int id, String contenurep, String created_at) {
        this.id = id;
        this.rep = contenurep;
        this.created_at = created_at;
    }
    public Reponse(int id,String contenurep, String created_at,int user_id) {
        this.id = id;
        this.rep = contenurep;
        this.created_at = created_at;
        this.user_id = user_id; 
    }
    public Reponse(String contenurep, String created_at,int user_id,int question_id) {
        this.rep = contenurep;
        this.created_at = created_at;
        this.user_id = user_id; 
        this.question_id = question_id;
    }

    public Reponse(String rep) {
        this.rep = rep;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContenurep() {
        return rep;
    }

    public void setContenurep(String contenurep) {
        this.rep = contenurep;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    
    
    @Override
    public String toString() {
        return "Reponse{" + "id=" + id + ", contenurep=" + rep + ", created_at=" + created_at + '}';
    }
    
    
}
