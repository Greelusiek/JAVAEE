/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.mycompany.mavenproject1;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author OODAX
 */
@Named(value = "sessionBean")
@SessionScoped
public class SessionBean implements Serializable {
    
    private int Counter = 0;
    
    public int getCounter() {
        return Counter;
    }
    public void incCounter() {
        ++Counter;
    }
//    private void associate() {
//    }
    public SessionBean() {
    }
}
