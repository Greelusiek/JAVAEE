package com.mycompany.lab2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/welcome")
public class WelcomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false); // checking for a session without creating a new one

        if (
                session == null ||                              // no session
                session.getAttribute("username") == null ||     // no user
                session.getAttribute("loginTime") == null ||    // no loginTime
                (System.currentTimeMillis() - (Long) session.getAttribute("loginTime")) > 30000 // login expired
            ) {
            response.sendRedirect("timeout.html"); // Redirect to timeout page
            return;
        }
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Welcome</title>");
        out.println("<style>");
        out.println("body{font-family: Arial, sans-serif;background-color: #f4f4f4;color: #333;text-align: center;padding: 50px;}");
        out.println("h1{color: #27ae60;margin-bottom: 20px;}");
        out.println("p{font-size: 18px;margin: 15px 0;}");
        out.println(".welcome-message{border: 2px solid #27ae60;border-radius: 10px;padding: 20px;background-color: #ffffff;display: inline-block;box-shadow: 0 4px 10px rgba(0,0,0,0.1);}");
        out.println("</style>");
        out.println("<script>");
        out.println("setTimeout(function(){location.reload();}, 5000);");
        out.println("</script>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class=\"welcome-message\">");
        out.println("<h1>Welcome, " + session.getAttribute("username") + "!</h1>");
        out.println("<p>You have access to this page for 30 seconds after logging in.</p>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
        }
    }
}
