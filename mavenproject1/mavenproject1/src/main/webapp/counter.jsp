<%   
    Integer counter = (Integer)session.getAttribute("counter");
    if (counter == null) {
        counter = 0;
        session.setAttribute("counter", counter);            
    }
    String action = (String) request.getParameter("action");
    if (action != null && action.equals("increment")) {
        ++counter;
        session.setAttribute("counter", counter);
    }    
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Servlet Counter</title>
</head>
<body>
<h1>Current Counter value: ${counter} !</h1>
<form action="counter.jsp" method="post">
<button type="submit" name="action" value="increment">Increment</button>
</form>
</body>
</html>
