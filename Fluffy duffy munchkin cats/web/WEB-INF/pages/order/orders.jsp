<%@ page import="java.util.Collection" %>
<%@ page import="org.softuni.fdmc.data.Cat" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ page import="java.util.List" %>
<%@ page import="org.softuni.fdmc.data.Order" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>FDMC </title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>
<body>
<div>
    <h1>All Orders</h1>
    <hr/>
    <%  List<Order> orders = (List<Order>)request.getServletContext().getAttribute("orders");
        if(orders.isEmpty()){%>
    <h2>There are no orders.</h2>
    <%} else {
        for (Order order: orders.stream().sorted((a,b) -> b.getMadeOn().compareTo(a.getMadeOn())).collect(Collectors.toList())) { %>
    <hr/>
        <h2>Client: <%=order.getUser().getUsername()%></h2>
        <h2>Cat: <%=order.getCat().getName()%></h2>
        <h2>Made on: <%=order.getMadeOn()%></h2>
    <hr/>
    <%}}%>
    <div>
        <a href="${pageContext.request.contextPath}/">Back To Home</a>
    </div>
</div>
</body>
</html>
