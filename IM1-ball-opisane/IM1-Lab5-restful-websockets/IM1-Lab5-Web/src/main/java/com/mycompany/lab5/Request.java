package com.mycompany.lab5;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Request implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String  text;
    private String  time;
    
    public Request(String text, String time) {
        //this.id = id;
        this.text = text;
        this.time = time;
    }
    
    public Request() {
        // default constructor
    }

    public int getId() {
        return id;
    }
    public String getText() {
        return text;
    }
    public String getTime() {
        return time;
    }
    public void setText(String text) {
        this.text = text;
    }
    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash ^= id.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Request)) {
            return false;
        }
        Request other = (Request) object;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "id: "+id+" text: "+text+" time: "+time;
    }     
}
