package com.mycompany.lab2;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class AppUser implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String password;
    
    public AppUser(String name, String password) {
        //this.id = id;
        this.name = name;
        //this.password = Integer.toHexString(password.hashCode());
        this.password = password;
    }
    
    public AppUser() {
        // default constructor
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPassword(String password) {
        //this.password = Integer.toHexString(password.hashCode());
        this.password = password;
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
        if (!(object instanceof AppUser)) {
            return false;
        }
        AppUser other = (AppUser) object;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "[" + id + " (" + name + ")]";
    }     
}
