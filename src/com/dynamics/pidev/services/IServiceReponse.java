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
 */
public interface IServiceReponse<T> {
    
    void insertOne(T t) throws SQLException;
    
    void updateQuestion(T t,int id) throws SQLException;
    
    void deleteOne(T t) throws SQLException;
    void deleteOne(int id) throws SQLException;
    void deleteByQuestionId (int id) throws SQLException;
    void updateCount(int count, int reponseId);
    
    List<T> selectAll() throws SQLException;
    List<T> selectByQuestion(int id) throws SQLException;
    
}
