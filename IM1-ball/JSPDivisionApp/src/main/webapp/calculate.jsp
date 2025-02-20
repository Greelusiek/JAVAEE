<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Wynik dzielenia</title>
</head>
<body>
    <%
        // Pobieranie danych z formularza
        String aStr = request.getParameter("a");
        String bStr = request.getParameter("b");

        try {
            double a = Double.parseDouble(aStr);
            double b = Double.parseDouble(bStr);

            if (b == 0) {
                // Przekierowanie na stronę błędu, jeśli dzielenie przez 0
                response.sendRedirect("error.jsp");
            } else {
                double wynik = a / b;
    %>
                <h2>Wynik: <%= wynik %></h2>
                <br/>
                <a href="index.jsp">Powrót</a>
    <%
            }
        } catch (NumberFormatException e) {
    %>
            <h2>Błąd: Wprowadź poprawne liczby!</h2>
            <br/>
            <a href="index.jsp">Powrót</a>
    <%
        }
    %>
</body>
</html>
