/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.jsfnumberredirect;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.IOException;
import java.io.Serializable;

@Named
@SessionScoped
public class NumberBean implements Serializable {
    private int number;
    private boolean validAccess = false; // Kontrola dostępu do stron wynikowych

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void checkNumber() {
        validAccess = true; // Ustawienie dostępu tylko w przypadku przekierowania
        String redirectPage = (number % 2 == 0) ? "even.xhtml" : "odd.xhtml";
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(redirectPage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isValidAccess() {
        return validAccess;
    }

    public void validateAccess() {
        if (!validAccess) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("forbidden.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            validAccess = false; // RESETUJEMY DOSTĘP po przejściu na stronę wynikową!
        }
    }
}
