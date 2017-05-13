<%@ page import="io.kreatimont.task.model.User" %>
<%@ page import="io.kreatimont.task.utils.Validator" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
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

    boolean isQuery = false;
    List<User> queryUser = new ArrayList<>();
    if (request.getSession().getAttribute("isQuery") != null && (boolean) request.getSession().getAttribute("isQuery")) {
        queryUser = (List<User>) request.getAttribute("queryUser");
        isQuery = true;
    }

//    User userFromRequest = (User) request.getAttribute("user");
//    User userFromSession = (User) request.getSession().getAttribute("user");
//
//    User user;
//    if(userFromRequest == null && userFromSession == null) {
//        request.setAttribute("status", "failed");
//        request.getRequestDispatcher("index.jsp").forward(request, response);
//        return;
//    } else if (userFromRequest != null) {
//        user = userFromRequest;
//    } else {
//        user = userFromSession;
//    }

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
    <div class="col-lg-12 well">
        <div class="row">
            <form onsubmit="handleUserData()">
                <div class="col-sm-12">
                    <div class="row">
                        <div class="col-sm-2 form-group">
                            <label>Birthday from</label>
                            <input name="bdayFrom" type="date" placeholder="Birthday from" class="form-control"
                                   pattern="[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])"
                                   value="<% if(isQuery) out.print((String)request.getAttribute("bdayFrom")); else out.print("1498-08-07"); %>"
                                   required>
                        </div>
                        <div class="col-sm-2 form-group">
                            <label>Birthday to</label>
                            <input name="bdayTo" type="date" placeholder="Birthday to" class="form-control"
                                   pattern="[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])"
                                   value="<% if(isQuery) out.print((String)request.getAttribute("bdayTo")); else out.print("2017-01-01"); %>"
                                   required>
                        </div>
                        <div class="col-sm-2 form-group">
                            <label>City</label>
                            <input name="withCity" type="text" placeholder="City name.." class="form-control"
                                   value="<% if(isQuery) out.print((String)request.getAttribute("withCity")); else out.print("LA"); %>"
                                   required>
                        </div>
                        <div class="col-sm-2 form-group">
                            <label>Country</label>
                            <input name="withCountry" type="text" placeholder="Contry name.." class="form-control"
                                   value="<% if(isQuery) out.print((String)request.getAttribute("withCountry")); else out.print("USA"); %>"
                                   required>
                        </div>
                        <div class="col-sm-2 form-group">
                            <label>Role</label>
                            <select name="withRole" id="roles" class="form-control">
                                <option value="user">user</option>
                                <option value="admin">admin</option>
                            </select>
                        </div>
                        <div class="col-sm-2 form-group">
                            <br>
                            <a class="btn btn-info" onclick="handleUserData()">Submit</a>
                            <%--<button type="submit" class="btn btn-info">Submit</button>--%>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <label>Users<% if (isQuery) {out.print("(query)");} %>:</label>
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

                List<User> allUsers;

                if (isQuery) {
                    allUsers = queryUser;
                } else {
                    allUsers = Validator.getAllUsers();
                }

                if(allUsers != null) {
                    for (int i = 0; i < allUsers.size(); i++) {
                        out.print("  <tr>");
                            out.print("\n\t\t<td scope=\"row\">" + (i+1) + "</td>");
                            out.print("\n\t\t<td >" + allUsers.get(i).getName() + "</td>");
                            out.print("\n\t\t<td >" + allUsers.get(i).getSurname() + "</td>");
                            out.print("\n\t\t<td >" + allUsers.get(i).getEmail() + "</td>");
                            out.print("\n\t\t<td >" + allUsers.get(i).getCity() + "</td>");
                            out.print("\n\t\t<td >" + allUsers.get(i).getCountry() + "</td>");
                            out.print("\n\t\t<td >" + allUsers.get(i).getBday() + "</td>");
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
<script>
    function handleUserData() {
        var formData = new FormData(document.querySelector('query'));

        <% isQuery = true; %>

        location.reload(true);
        return false;
    }
</script>
</body>
</html>
