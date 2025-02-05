package com.mycompany.lab4;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Date;

@Entity
public class Game implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Date date;
    
    public Game() {
        this.date = new Date();
    }

    public int getId() {
        return id;
    }     
    public Date getDate() {
        return date;
    }  
    public void setDate(Date date) {
        this.date = date;
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
        if (!(object instanceof Game)) {
            return false;
        }
        Game other = (Game) object;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "id: "+id;
    }     
}
