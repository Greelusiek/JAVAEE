/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.usertablejsf;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ApplicationScoped
public class UserBean implements Serializable {
    private final List<User> users;

    public UserBean() {
        users = new ArrayList<>();
        users.add(new User(1, "Jan", "Kowalski", "jan.kowalski@example.com"));
        users.add(new User(2, "Anna", "Nowak", "anna.nowak@example.com"));
        users.add(new User(3, "Piotr", "Zieliński", "piotr.zielinski@example.com"));
        users.add(new User(4, "Jan", "Kowalski", "jan.kowalski@example.com")); // Powtórzenie użytkownika
    }

    public List<User> getUsers() {
        return users;
    }
}
