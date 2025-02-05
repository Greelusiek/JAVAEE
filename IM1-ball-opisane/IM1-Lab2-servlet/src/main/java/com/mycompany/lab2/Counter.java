package com.mycompany.lab2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/counter")
public class Counter extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        doHandle(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Integer counter = (Integer)session.getAttribute("counter");
        if (counter == null) counter = 0;
        ++counter;
        session.setAttribute("counter", counter);
        
        doHandle(request, response);
    }
    
    protected void doHandle(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Integer counter = (Integer)session.getAttribute("counter");
        if (counter == null) {
            counter = 0;
            session.setAttribute("counter", counter);            
        }
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Counter</title>");
        // out.println("<script>");
        // out.println("setTimeout(function(){location.reload();}, 5000);");
        // out.println("</script>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Current Counter value: " + counter + "!</h1>");
        out.println("<form action=\"counter\" method=\"post\">");
        out.println("<button type=\"submit\"> Increment</button>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
        }
    }    
}
