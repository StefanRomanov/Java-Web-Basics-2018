<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>FDMC </title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>
<body>
<div>
    <h1>Welcome to Fluffy Duffy Munchkin Cats!</h1>
    <h3>Navigate through the application using the links below!</h3>
    <hr/>
    <% if(session.getAttribute("username") != null) { %>
    <div >
        <%if (session.getAttribute("role").equals("ADMIN")) {%>
        <a href="${pageContext.request.contextPath}/cats/create">Create cat</a>
        <br>
        <a href="${pageContext.request.contextPath}/orders/all">All orders</a>
        <br>
        <%}%>
        <a href="${pageContext.request.contextPath}/cats/all">All cats</a>
        <br>
        <a href="${pageContext.request.contextPath}/users/logout">Logout</a>
    </div>
    <% } else {%>
    <div >
        <a href="${pageContext.request.contextPath}/users/login">Login</a>
        <br>
        <a href="${pageContext.request.contextPath}/users/register">Register</a>
    </div>
    <% } %>
</div>
</body>
</html>
