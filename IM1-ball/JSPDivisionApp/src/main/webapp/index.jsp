<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Podaj dwie liczby</title>
</head>
<body>
    <h2>Podaj dwie liczby:</h2>
    <form action="calculate.jsp" method="post">
        Liczba A: <input type="text" name="a" required /><br/>
        Liczba B: <input type="text" name="b" required /><br/>
        <input type="submit" value="Oblicz dzielenie" />
    </form>
</body>
</html>
