/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.cookietimeoutapp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/set-cookie")
public class SetCookieServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        // Tworzymy cookie z czasem życia 30 sekund
        Cookie sessionCookie = new Cookie("sessionCookie", String.valueOf(System.currentTimeMillis()));
        sessionCookie.setMaxAge(30); // 30 sekund
        response.addCookie(sessionCookie);

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2>Cookie ustawione na 30 sekund!</h2>");
        out.println("<a href='check-cookie'>Sprawdź cookie</a>");
        out.println("</body></html>");
    }
}
