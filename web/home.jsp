<%@ page import="io.kreatimont.task.model.User" %>
<%--
  Created by IntelliJ IDEA.
  User: kreatimont
  Date: 04.01.2017
  Time: 14:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/jumbotron-narrow.css" rel="stylesheet">
</head>
<body>
    <%

        if(request.getSession() == null) {
            request.setAttribute("status", "failed");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        } else if (request.getSession().getAttribute("user") == null) {
            request.setAttribute("status", "failed");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }

        User userFromRequest = (User) request.getAttribute("user");
        User userFromSession = (User) request.getSession().getAttribute("user");

        User user;
        if(userFromRequest == null && userFromSession == null) {
            request.setAttribute("status", "failed");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        } else if (userFromRequest != null) {
            user = userFromRequest;
        } else {
            user = userFromSession;
        }

    %>
    <div class="container">
        <div class="header clearfix">
            <nav>
                <ul class="nav nav-pills pull-right">
                    <li role="presentation" class="active"><a href="#">Home</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/html/about.html">About</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
                </ul>
            </nav>
            <h3 class="text-muted">Guest book</h3>
        </div>

        <div class="row marketing">
            <div class="col-lg-6">
                <h4>Name</h4>
                <p><%=user.getName()%></p>

                <h4>Country</h4>
                <p><%=user.getCountry()%></p>

                <h4>Email</h4>
                <p><%=user.getEmail()%></p>
            </div>

            <div class="col-lg-6">
                <h4>Surname</h4>
                <p><%=user.getSurname()%></p>

                <h4>City</h4>
                <p><%=user.getCity()%></p>

                <h4>Phone</h4>
                <p><%=user.getPhone()%></p>
            </div>
        </div>

        <footer class="footer">
            <p>&copy; 2017 (kreatimont) SoftGroup task.</p>
        </footer>

    </div>
</body>
</html>
