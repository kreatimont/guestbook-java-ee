<%@ page import="io.kreatimont.task.model.User" %>
<%@ page import="io.kreatimont.task.utils.DatabaseManager" %>
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

    HttpServletRequest req = request;

    String bdayFrom = req.getParameter("bdayFrom");
    String bdayTo = req.getParameter("bdayTo");
    String withCity = req.getParameter("withCity");
    String withCountry = req.getParameter("withCountry");
    String withRole = req.getParameter("withRole");

    String query = req.getParameter("isQuery");
    boolean isQuery = false;

    if(query != null && query.equals("true")) {
        isQuery = true;
    }

    List<User> userList;

    if(isQuery) {
        userList = DatabaseManager.getUsersWith(bdayFrom,bdayTo,withCountry,withCity,withRole);
    } else {
        userList = DatabaseManager.getAllUsers();
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
    <div class="col-lg-12 well">
        <div class="row">
            <form method="get" action="/admin.jsp">
                <div class="col-sm-12">
                    <div class="row">
                        <div class="col-sm-2 form-group">
                            <label>Birthday from</label>
                            <input name="bdayFrom" type="date" placeholder="Birthday from" class="form-control"
                                   pattern="[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])"
                                   value="<% out.print(isQuery ? bdayFrom : "1547-09-09"); %>"
                                   required>
                        </div>
                        <div class="col-sm-2 form-group">
                            <label>Birthday to</label>
                            <input name="bdayTo" type="date" placeholder="Birthday to" class="form-control"
                                   pattern="[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])"
                                   value="<% out.print(isQuery ? bdayTo : "2017-09-09"); %>"
                                   required>
                        </div>
                        <div class="col-sm-2 form-group">
                            <label>City</label>
                            <input name="withCity" type="text" placeholder="City name.." class="form-control"
                                   value="<% out.print(isQuery ? withCity : "Chicago"); %>"
                            >
                        </div>
                        <div class="col-sm-2 form-group">
                            <label>Country</label>
                            <input name="withCountry" type="text" placeholder="Contry name.." class="form-control"
                                   value="<% out.print(isQuery ? withCountry : "United States"); %>"
                            >
                        </div>
                        <div class="col-sm-2 form-group">
                            <label>Role</label>
                            <%
                                if (isQuery && withRole.equals("admin")) {
                                    out.print("<select name=\"withRole\" id=\"roles\" class=\"form-control\">\n" +
                                            "                                <option value=\"admin\">admin</option>\n" +
                                            "                                <option value=\"user\">user</option>\n" +
                                            "                            </select>");
                                } else {
                                    out.print("<select name=\"withRole\" id=\"roles\" class=\"form-control\">\n" +
                                            "                                <option value=\"user\">user</option>\n" +
                                            "                                <option value=\"admin\">admin</option>\n" +
                                            "                            </select>");
                                }

                            %>
                        </div>
                        <div class="col-sm-1 form-group">
                            <label>Is query?</label>
                            <br>
                            <input type="checkbox"
                                   name="isQuery"
                                   checked="checked"
                                   value="true" />
                        </div>
                        <div class="col-sm-1 form-group">
                            <br>
                            <button type="submit" class="btn btn-info">Submit</button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
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

                List<User> allUsers = userList;
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
    <br>
    <br>
    <p>sql: (<% if(DatabaseManager.queries.size() > 0) out.print(DatabaseManager.queries.get(DatabaseManager.queries.size() - 1)); %>):</p>
    <footer class="footer">
        <p>&copy; 2017 (kreatimont) SoftGroup task.</p>
    </footer>
</div>
<script>
    function handleUserData() {
        var formData = new FormData(document.querySelector('query'));

        return false;
    }
</script>
</body>
</html>
