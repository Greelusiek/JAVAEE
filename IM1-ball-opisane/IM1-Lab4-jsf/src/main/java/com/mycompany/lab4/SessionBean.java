package com.mycompany.lab4;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.List;

@Named(value = "sessionBean")
@SessionScoped
public class SessionBean implements Serializable {
    
    @Inject
    private ApplicationBean applicationBean;

    private String username = null;

    public SessionBean() {
    }
    
    public void run() {
    }
    
    public void associate() { /*leave empty*/
    }
       
    @PostConstruct
    public void init() {
    }        
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }  
    
    public void login() {
    } 
    
    public void join() {
        if(!isGameRunning() && isLoggedIn()) {
            applicationBean.usersAdd(username);
            applicationBean.pushSend();
        }
    }
    
    public void next() {
        if (isUserTurn()) {
            applicationBean.playsAdd(username + " played " +  applicationBean.getSymbols().get(applicationBean.getUserCard().get(username)));
            applicationBean.nextTurn();
            applicationBean.pushSend();
        }
    }     

    public Boolean isUserTurn() {
        if (isGameRunning() && isLoggedIn() && isPlaying()) {
            return applicationBean.getTurn().equals(username);
        }
        return false;
    } 
    
    public Boolean isGameRunning() {
        return applicationBean.isGameRunning();
    }
    
    public List<Play> getPlays() {
        return applicationBean.getPlays();
    }
    
    public Boolean isLoggedIn() {
        return username != null;
    } 
    
    public Boolean isPlaying() {
        
        /* -------------------------------------------- *//**x/
        for( String u : applicationBean.getUsers()) {
            if (u.equals(username)) return true;
        }
        /* -------------------------------------------- *//**/
        
        return applicationBean.getUsers().contains(username);
    }
}
