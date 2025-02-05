<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList, java.util.Date, com.mycompany.lab3.StampDao, java.util.List, com.mycompany.lab3.Stamp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    StampDao stampDao = (StampDao) application.getAttribute("stampDao");
    
    Boolean isListFresh = (Boolean) application.getAttribute("isListFresh");
    if (isListFresh == null) {
        isListFresh = false;
        // Assigned every time - primitive type 
        application.setAttribute("isListFresh", isListFresh); // What is accessed from html form  
    }
    ArrayList<Stamp> list = (ArrayList<Stamp>) application.getAttribute("list");
    if (list == null) {
        list = new ArrayList();
        // Assigned only once - reference
        application.setAttribute("list", list); // What is accessed from html form
        isListFresh = false;
    }
    Integer limit = (Integer) application.getAttribute("limit");
    if (limit == null) {
        limit = 10;
        application.setAttribute("limit", limit); // What is accessed from html form
    }
    
    if(isListFresh == false)
    {
        list.clear();
        try {
            stampDao.findAll(false, limit).forEach(list::add);
            System.out.println("Stamp table listed successfully.");
        } catch (Exception e) {
            System.err.println("Error listing Stamp table: " + e.getMessage());
        }
        isListFresh = true;
        application.setAttribute("isListFresh", isListFresh);
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Global Access Log</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            text-align: center;
            background-color: #f9f9f9;
            color: #333;
        }
        h1 {
            color: #2196F3;
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
        ul {
            list-style-type: none;
            padding: 0;
            max-width: 600px;
            margin: 0 auto;
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
        }
        li {
            padding: 15px;
            border-bottom: 1px solid #ddd;
        }
        li:last-child {
            border-bottom: none;
        }
        .no-entries {
            font-size: 1.2em;
            color: #888;
            margin-top: 20px;
        }
    </style>
    <script>
        setTimeout(function() {
            location.reload();
        }, 5000);
    </script>
</head>
<body>
    <h1>Global Access Log</h1>
    <p>LIMIT ${limit}</p>
    <a href="access.jsp"  class="button log">Refresh</a>
    <a href="counter.jsp" class="button inc">Counter</a>
    <p></p>
    <c:choose>
        <c:when test="${not empty list}">
            <ul>
                <c:forEach var="stamp" items="${list}">
                    <li><strong>IP:</strong> ${stamp.ip} <strong>TIME:</strong> ${stamp.time}</li>
                </c:forEach>
            </ul>
        </c:when>
        <c:otherwise> 
           <p class="no-entries">No entries available.</p>
        </c:otherwise>
    </c:choose>
</body>
</html>
