package com.mycompany.lab3;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Stamp implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String ip;
    private String time;
    
    public Stamp(String ip, String time) {
        //this.id = id;
        this.ip = ip;
        this.time = time;
    }
    
    public Stamp() {
        // default constructor
    }

    public int getId() {
        return id;
    }
    public String getIp() {
        return ip;
    }
    public String getTime() {
        return time;
    }
    public void setIp(String ip) {
        this.ip = ip;
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
        if (!(object instanceof Stamp)) {
            return false;
        }
        Stamp other = (Stamp) object;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "id: "+id+" ip: "+ip+" time: "+time;
    }     
}
