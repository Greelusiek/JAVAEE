/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.jsfnumbergenerator;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.Random;

@Named
@SessionScoped
public class NumberBean implements Serializable {
    private Integer userNumber;
    private Integer generatedNumber;
    private boolean validAccess = false; // Kontrola dostępu

    public Integer getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(Integer userNumber) {
        this.userNumber = userNumber;
    }

    public Integer getGeneratedNumber() {
        return generatedNumber;
    }

    public void generateRandomNumber() {
        if (userNumber != null) {
            Random rand = new Random();
            generatedNumber = userNumber + rand.nextInt(10) + 1; // Losowa liczba większa od podanej
            validAccess = true; // Dostęp do strony wynikowej dozwolony
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("result.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void validateAccess() {
        if (!validAccess) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("forbidden.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            validAccess = false; // Reset dostępu po wyświetleniu strony
        }
    }
}
