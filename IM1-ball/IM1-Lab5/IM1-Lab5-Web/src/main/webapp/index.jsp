<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList, java.util.Date, com.mycompany.lab5.ApplicationBean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Request</title>
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

    table {
        width: 80%;
        margin: 20px auto;
        border-collapse: collapse; /* Ensures consistent borders */
        text-align: left;
        background-color: #ffffff;
        border-radius: 8px;
        box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
        overflow: hidden;
    }

    thead tr {
        background-color: #f1f1f1;
        border-bottom: 2px solid #ddd;
    }

    th, td {
        padding: 10px; /* Proper spacing for cells */
        border-bottom: 1px solid #ddd; /* Consistent borders between rows */
    }

    tr:last-child td {
        border-bottom: none; /* Removes bottom border from last row */
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
    <h1>Request Log</h1>
    <p>Viewing ${applicationBean.getLimit()} items</p>
    <a href="index.jsp"  class="button log">Refresh</a>
    <p></p>
    <c:choose>
        <c:when test="${not empty applicationBean.getList()}">
            <table>
                <thead>
                    <tr>
                        <th>Request</th>
                        <th>Time</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="entry" items="${applicationBean.getList()}">
                        <tr>
                            <td>${entry.text}</td>
                            <td>${entry.time}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <p class="no-entries">No entries available.</p>
        </c:otherwise>
    </c:choose>
</body>
</html>
