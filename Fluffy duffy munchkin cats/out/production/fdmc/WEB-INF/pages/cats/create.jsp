<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>FDMC </title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>
<body>
<div >
    <h1 >Create a Cat!</h1>
    <form method="post">
        <div>
            <label for="name" >Name</label>
            <input type="text" id="name" name="name" placeholder="Enter name">
        </div>
        <div>
            <label for="breed" >Breed</label>
            <input type="text" id="breed" name="breed" placeholder="Enter breed">
        </div>
        <div>
            <label for="color" >Color</label>
            <input type="text" id="color" name="color" placeholder="Enter color">
        </div>
        <div>
            <label for="legs" >Number of legs</label>
            <input type="number" id="legs" name="legs" placeholder="Enter number of legs">
        </div>
        <button type="submit">Create cat</button>
    </form>
    <div>
        <a href="${pageContext.request.contextPath}/">Back To Home</a>
    </div>
</div>
</body>
</html>
