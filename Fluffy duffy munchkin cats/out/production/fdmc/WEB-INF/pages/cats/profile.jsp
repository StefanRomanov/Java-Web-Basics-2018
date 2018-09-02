<%@ page import="org.softuni.fdmc.data.Cat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>FDMC </title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>
<body>
<div>
    <%if(request.getAttribute("cat") == null){%>
    <h1>Cat, with name - ${catName} was not found.</h1>
    <%} else {
        %><h1>Cat - ${cat.getName()}</h1>
           <hr/>
            <h2>Breed: ${cat.getBreed()}</h2>
            <h2>Color: ${cat.getColor()}</h2>
            <h2>Number of legs: ${cat.getLegs()}</h2>
            <h2>Creator: ${cat.getCreator().getUsername()}</h2>
            <h2>Views: ${cat.getViews()}</h2>
            <hr/>
    <%}%>
    <%
    if (session.getAttribute("role").equals("USER")) {%>
    <div>
        <form method="post" action="${pageContext.request.contextPath}/makeOrder?name=${cat.getName()}">
            <button type="submit">Order</button>
        </form>
    </div>
    <%}%>
    <hr />
    <div>
        <a href="${pageContext.request.contextPath}/cats/all">Back</a>
    </div>
</div>
</body>
</html>
