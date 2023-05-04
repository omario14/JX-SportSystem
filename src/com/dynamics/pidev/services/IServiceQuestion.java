/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dynamics.pidev.services;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author FGH
 * @param <T>
 */
public interface IServiceQuestion<T> {
    void updateCount(int count,int id);
    void insertOne(T t) throws SQLException;
    
    void updateQuestion(T t,int id) throws SQLException;
    
    void deleteOne(T t) throws SQLException;
    void deleteOne(int id) throws SQLException;
    
    List<T> selectAll() throws SQLException;
    T selectById( int id) throws SQLException;
    String usernameById(int id);
    int countReponses(int id);
    List<T> selectByLikes() throws SQLException;
    
    
}
