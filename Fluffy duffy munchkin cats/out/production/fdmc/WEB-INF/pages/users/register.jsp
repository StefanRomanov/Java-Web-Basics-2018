<html>
<head>
    <title>FDMC </title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>
<body>
<div >
    <h1 >Register</h1>
    <form method="post" action="${pageContext.request.contextPath}/users/register">
        <div>
            <label for="name" >Username</label>
            <input type="text" id="name" name="username" placeholder="Username">
        </div>
        <div>
            <label for="breed" >Password</label>
            <input type="password" id="breed" name="password" placeholder="Password">
        </div>
        <div>
            <label for="color" >Confirm password</label>
            <input type="password" id="color" name="repPassword" placeholder="Confirm password">
        </div>
        <div >
            <label for="role" >Select role</label>
            <select id="role" name="role">
                <option value="USER">User</option>
                <option value="ADMIN">Admin</option>
            </select>
        </div>
        <button type="submit">Register</button>
    </form>
    <div>
        <a href="${pageContext.request.contextPath}/">Back To Home</a>
    </div>
</div>
</body>
</html>
