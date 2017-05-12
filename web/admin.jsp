<%@ page import="io.kreatimont.task.model.User" %>
<%@ page import="io.kreatimont.task.utils.Validator" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin page</title>
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
<div class="container-fluid">
    <div class="header clearfix">
        <nav>
            <ul class="nav nav-pills pull-right">
                <li role="presentation" class="active"><a href="#">Console</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
            </ul>
        </nav>
        <h3 class="text-muted">Guest book (admin console)</h3>
    </div>

    <form>
        <label>Write query</label>
        <input name="name" type="text" placeholder="Enter First Name Here.." class="form-control" required>
    </form>

    <label>Users:</label>
    <table class="table">
        <thead>
            <tr>
                <th >#</th>
                <th >First Name</th>
                <th >Last Name</th>
                <th >Email</th>
                <th >City</th>
                <th >Country</th>
                <th >Birthday</th>
                <th >Role</th>
            </tr>
        </thead>
        <tbody>
            <%

                List<User> allUsers = Validator.getAllUsers();
                if(allUsers != null) {
                    for (int i = 0; i < allUsers.size(); i++) {
                        out.print("  <tr>");
                            out.print("\n\t\t<td scope=\"row\">" + (i+1) + "</td>");
                            out.print("\n\t\t<td >" + allUsers.get(i).getName() + "</td>");
                            out.print("\n\t\t<td >" + allUsers.get(i).getSurname() + "</td>");
                            out.print("\n\t\t<td >" + allUsers.get(i).getEmail() + "</td>");
                            out.print("\n\t\t<td >" + allUsers.get(i).getCity() + "</td>");
                            out.print("\n\t\t<td >" + allUsers.get(i).getCountry() + "</td>");
                            out.print("\n\t\t<td >" + allUsers.get(i).getBday().toString() + "</td>");
                            out.print("\n\t\t<td >" + allUsers.get(i).getRole() + "</td>");
                        out.print("\n  </tr>\n");
                    }
                } else {
                }

            %>
        </tbody>
    </table>

    <footer class="footer">
        <p>&copy; 2017 (kreatimont) SoftGroup task.</p>
    </footer>

</div>
</body>
</html>
