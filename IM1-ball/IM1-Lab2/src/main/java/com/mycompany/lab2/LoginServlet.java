package com.mycompany.lab2;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Inject
    private AppUserDao userDAO;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("login.html"); // Redirect to login page (login.html)
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        String action = request.getParameter("action"); // Get the button name/value

        HttpSession session = request.getSession();
        Long lastFailedLoginTime = (Long) session.getAttribute("lastFailedLoginTime");
        long currentTime = System.currentTimeMillis();

        // Check if the user needs to wait // May be needed
        /*x*x/
        if (lastFailedLoginTime != null) {
            long timeSinceLastFailedLogin = currentTime - lastFailedLoginTime;
            if (timeSinceLastFailedLogin < 10000) { // 10 seconds
                response.sendRedirect("failed"); // Redirect to failed login page
                return;
            }
        }
        //*/
        
        List<AppUser> users = (List<AppUser>) getServletContext().getAttribute("users");
        AppUser user = findUserByUsername(users, username);
        
        
        if (action.equals("login")) { // Login button
            if (user != null && password.equals(user.getPassword())) {
                session.setAttribute("username", username);
                session.setAttribute("loginTime", currentTime); // Store login time
                session.removeAttribute("lastFailedLoginTime");
                response.sendRedirect("login.html"); // Stay on login page // You can redirect from here
            } else {
                // Failed login,
                // set the failed login attempt time to block login temporarily
                session.setAttribute("lastFailedLoginTime", currentTime);
                response.sendRedirect("failed");
            }
        }
        
        if (action.equals("redirect")) { // Redirect button
            response.sendRedirect("welcome"); // Redirect to welcome page
        }
    }
    
    private static AppUser findUserByUsername(List<AppUser> users, String username) {
        if (users == null) return null;
        for (AppUser user : users) {
            if (user.getName().equals(username)) {
                return user; // Return the found user
            }
        }
        return null; // Return null if not found
    }
    
    @Override
    public void init() throws ServletException {
        super.init();
        
        // addRecords(); // DB and Application
        // updateList(); // DB and Application
        UsersInit(); // Application only (no DB)
    }    
    
    /*x*x/
    private void addRecords() { // TODELETE
        try {
            List<AppUser> users = new ArrayList<>();
            users.add(new AppUser("admin", "admin"));
            users.add(new AppUser("user1", "user1"));
            users.add(new AppUser("user2", "user2"));
            users.add(new AppUser("user3", "user3"));
            users.add(new AppUser("user4", "user4"));
            userDAO.saveMultiple(users);
            System.out.println("Default users added successfully.");
        } catch (Exception e) {
            System.err.println("Error adding users: " + e.getMessage());
        }
    }
    //*/
    
    /*x*x/
    private void updateList() { // TODELETE
        List<AppUser> users = new ArrayList<>();
        try {
            userDAO.findAll()
                .forEach(users::add);
            getServletContext().setAttribute("users", users);
            System.out.println("User list updated successfully.");
        } catch (Exception e) {
            System.err.println("Error updating user list: " + e.getMessage());
        }
    }
    //*/

    private void UsersInit() {
        List<AppUser> users = new ArrayList<>();
        users.add(new AppUser("admin", "admin"));
        users.add(new AppUser("user1", "user1"));
        users.add(new AppUser("user2", "user2"));
        users.add(new AppUser("user3", "user3"));
        users.add(new AppUser("user4", "user4"));
        getServletContext().setAttribute("users", users);
    } 
}
