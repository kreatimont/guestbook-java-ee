<%@ page import="io.kreatimont.task.model.User" %><%--
  Created by IntelliJ IDEA.
  User: kreatimont
  Date: 04.01.2017
  Time: 11:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="css/bootstrap.css">
</head>
<body>
    <%
//        User user;
//        user = (User) request.getSession().getAttribute("user");
        if(request.getSession(false) != null) {
            request.getSession().removeAttribute("user");
        }
        request.getSession().invalidate();
    %>
    <div style="width: 250px;
                height: 200px;
                position: absolute;
                top: 35%;
                left: 50%;
                margin: -125px 0 0 -125px;">
        <h2 style="margin-bottom: 36px">Guest book</h2>
        <form method="post" action="login">
            <div class="form-group">
                <label for="exampleInputEmail1">Email address</label>
                <input name="email" type="email" class="form-control" id="exampleInputEmail1" placeholder="Email" required>
            </div>
            <div class="form-group">
                <label for="exampleInputPassword1">Password</label>
                <input name="password" type="password" class="form-control" id="exampleInputPassword1" placeholder="Password"
                       pattern=".{8,}" required>
            </div>
            <button type="submit" class="btn btn-primary">Log in</button>
            <a href="sign_up.jsp" class="btn btn-success" role="button">Sign Up</a>
        </form>
        <p style="color: red;">
            <%
                if(request.getAttribute("status") != null) {
                    out.print("Login failed. Try again.");
                }
            %>
        </p>
    </div>
</body>
</html>
