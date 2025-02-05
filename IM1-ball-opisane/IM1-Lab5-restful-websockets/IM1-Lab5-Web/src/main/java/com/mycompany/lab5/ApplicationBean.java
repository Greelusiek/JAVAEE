package com.mycompany.lab5;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.ArrayList;


@Named(value = "applicationBean")
@ApplicationScoped
public class ApplicationBean {
    
    @Inject
    private RequestDao requestDao;
    
    private Boolean isListFresh = false;
    private final ArrayList<Request> list = new ArrayList();
    private final int limit = 20;

    public ApplicationBean() {      
    } 
    
    public void run() {
    }
    
    public void associate() { /*leave empty*/
    }
       
    @PostConstruct
    public void init() {
    }    

    public void listAdd(String text, String date) {
        try {
            requestDao.save(new Request(text, date));
            System.out.println("Request added.");
        } catch (Exception e) {
            System.err.println("Error adding stamp: " + e.getMessage());
        }
        isListFresh = false; 
    }
    
    public ArrayList<Request> getList() {
        if(isListFresh == false)
        {
            list.clear();
            try {
                requestDao.findAll(false, limit).forEach(list::add);
                System.out.println("Request table listed successfully.");
            } catch (Exception e) {
                System.err.println("Error listing Request table: " + e.getMessage());
            }
            isListFresh = true;
        }
        return list;
    }
    
    public int getLimit() {
        return limit;
    }    
}
