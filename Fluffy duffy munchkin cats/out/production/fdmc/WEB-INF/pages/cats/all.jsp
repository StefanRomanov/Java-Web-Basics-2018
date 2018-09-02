<%@ page import="java.util.Collection" %>
<%@ page import="org.softuni.fdmc.data.Cat" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>FDMC </title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>
<body>
<div>
    <h1>All Cats</h1>
    <hr/>
    <%  Collection<Cat> cats = (Collection<Cat>)request.getAttribute("cats");
        if(cats.isEmpty()){%>
            <h2>There are no cats.
                <%if (session.getAttribute("role").equals("ADMIN")) {%>
                <a href=${pageContext.request.contextPath}/cats/create> Create some!</a></h2>
                <%}%>
        <%} else {
            for (Cat cat: cats.stream().sorted((a,b) -> b.getViews() - a.getViews()).collect(Collectors.toList())) { %>
                <h3>
                    <a href="${pageContext.request.contextPath}/cats/profile?name=<%=cat.getName()%>"><%=cat.getName()%></a>
                </h3>
        <%}}%>
    <div>
        <a href="${pageContext.request.contextPath}/">Back To Home</a>
    </div>
</div>
</body>
</html>