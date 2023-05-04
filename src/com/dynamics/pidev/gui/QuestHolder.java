
package com.dynamics.pidev.gui;

import com.dynamics.pidev.entites.Question;

/**
 *
 * @author MSI
 */
public final class QuestHolder {
    private Question quest;
    private final static QuestHolder INSTANCE =new QuestHolder();

    public QuestHolder() {
    }
    
    public static QuestHolder getInstance(){
     return INSTANCE;   
    }
    
    
    
    public void setQuest (Question s){
        this.quest =s;
    }
    
        public Question getQuest (){
        return  this.quest;
    }
}
