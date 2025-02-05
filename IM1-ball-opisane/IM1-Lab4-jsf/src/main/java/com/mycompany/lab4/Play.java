package com.mycompany.lab4;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Date;

@Entity
public class Play implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String text;
    private Game game;
    
    public Play(String text, Game game) {
        this.text = text;
        this.game = game;
    }
    
    public Play() {
        // default constructor
    }

    public int getId() {
        return id;
    }
    public String getText() {
        return text;
    }     
    public Game getGame() {
        return game;
    }  
    public void setText(String text) {
        this.text = text;
    }
    public void setGame(Game game) {
        this.game = game;
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
        if (!(object instanceof Play)) {
            return false;
        }
        Play other = (Play) object;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "id: "+id;
    }     
}
