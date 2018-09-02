<html>
<head>
    <title>FDMC </title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>
<body>
<div >
    <h1 >Login</h1>
    <form method="post" action="${pageContext.request.contextPath}/users/login">
        <div>
            <label for="name" >Username</label>
            <input type="text" id="name" name="username" placeholder="Username">
        </div>
        <div>
            <label for="breed" >Password</label>
            <input type="password" id="breed" name="password" placeholder="Password">
        </div>
        <button type="submit">Login</button>
    </form>
    <div>
        <a href="${pageContext.request.contextPath}/">Back To Home</a>
    </div>
</div>
</body>
</html>
