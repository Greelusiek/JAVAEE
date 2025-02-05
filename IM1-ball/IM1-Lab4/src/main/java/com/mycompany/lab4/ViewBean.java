package com.mycompany.lab4;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named(value = "viewBean")
@ViewScoped
public class ViewBean implements Serializable {
    
    @Inject
    private SessionBean sessionBean;

    private String username = null;
    
    private final List<Play> plays = new ArrayList();

    public ViewBean() {
    }
    
    public void run() {
    }
    
    public void associate() { /*leave empty*/
    }
       
    @PostConstruct
    public void init() {
    }        
    
    public String getUsername() {
        username = sessionBean.getUsername();
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
        sessionBean.setUsername(username);
        
    }  
    
    public void login() {
    } 
    
    public void join() {
        sessionBean.join();
    }
    
    public void next() {
        sessionBean.next();
    }     

    public Boolean isUserTurn() {
        return sessionBean.isUserTurn();
    } 
    
    public Boolean isGameRunning() {
        return sessionBean.isGameRunning();
    }
    
    public List<Play> getPlays() {
        plays.clear();
        plays.addAll(sessionBean.getPlays());
        return plays;
    }
    
    public Boolean isLoggedIn() {
        return sessionBean.isLoggedIn();
    } 
    
    public Boolean isPlaying() {       
        return sessionBean.isPlaying();
    }
}
