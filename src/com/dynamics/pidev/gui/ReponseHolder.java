
package com.dynamics.pidev.gui;

import com.dynamics.pidev.entites.Reponse;

/**
 *
 * @author MSI
 */
public final class ReponseHolder {
    private Reponse resp;
    private final static ReponseHolder INSTANCE =new ReponseHolder();

    public ReponseHolder() {
    }
    
    public static ReponseHolder getInstance(){
     return INSTANCE;   
    }
    
    
    
    public void setResp (Reponse s){
        this.resp =s;
    }
    
        public Reponse getResp (){
        return  this.resp;
    }
}
