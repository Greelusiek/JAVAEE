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

@WebServlet("/check-cookie")
public class CheckCookieServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Cookie[] cookies = request.getCookies();
        boolean found = false;
        long currentTime = System.currentTimeMillis();

        out.println("<html><body>");
        
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("sessionCookie")) {
                    found = true;
                    long cookieTime = Long.parseLong(cookie.getValue());
                    long elapsedTime = (currentTime - cookieTime) / 1000;
                    long remainingTime = 30 - elapsedTime;

                    if (remainingTime > 0) {
                        out.println("<h2>Pozostaly czas do wygasniecia cookie: " + remainingTime + " sekund</h2>");
                    } else {
                        out.println("<h2>Cookie wygaslo!</h2>");
                    }
                    break;
                }
            }
        }

        if (!found) {
            out.println("<h2>Brak cookie! Ustaw je ponownie.</h2>");
        }

        out.println("<a href='set-cookie'>Ustaw cookie</a>");
        out.println("</body></html>");
    }
}
