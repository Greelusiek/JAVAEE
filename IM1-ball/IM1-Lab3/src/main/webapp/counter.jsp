<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList, java.util.Date, com.mycompany.lab3.StampDao, java.util.List, com.mycompany.lab3.Stamp" %>
<%   
    
    StampDao stampDao = (StampDao) application.getAttribute("stampDao");
    
    Integer counter = (Integer) session.getAttribute("counter");
    if (counter == null) {
        counter = 0;
    }

    // Increment counter for every request
    {
        counter++;
        
        session.setAttribute("counter", counter); // What is accessed from html form
        
        try {
            List<Stamp> stamps = new ArrayList<>();
            stamps.add(new Stamp(request.getRemoteAddr(), new Date().toString()));
            stampDao.saveMultiple(stamps);
            System.out.println("Stamps added.");
        } catch (Exception e) {
            System.err.println("Error adding stamps: " + e.getMessage());
        }
        application.setAttribute("isListFresh", false);       
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Counter</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            text-align: center;
            background-color: #f9f9f9;
            color: #333;
        }
        h1 {
            color: #4CAF50;
        }
        p {
            font-size: 1.2em;
        }
        .button {
            display: inline-block;
            margin: 10px;
            padding: 10px 20px;
            font-size: 1em;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s;
        }
        .button.log {
            background-color: #2196F3;
        }        
        .button.log:hover {
            background-color: #1E88E5;
        }
        .button.inc {
            background-color: #4CAF50;
        }
        .button.inc:hover {
            background-color: #45a049;
        }
    </style>
    <script>
    </script>
</head>
<body>
    <h1>Counter</h1>
    <p>Your current counter value: ${counter} </p>
    <a href="counter.jsp" class="button inc">Increment Counter</a>
    <a href="access.jsp" class="button log">Global Access Log</a>
    <p></p>
</body>
</html>