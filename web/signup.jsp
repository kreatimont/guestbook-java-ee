<%@ page import="java.util.Objects" %><%--
  Created by IntelliJ IDEA.
  User: kreatimont
  Date: 04.01.2017
  Time: 11:57
  To change this template use File | Settings | File Templates.
--%>
<%--Ім’я, прізвище, дата народження, телефон, електронна пошта, країна, місто--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign Up</title>
    <link rel="stylesheet" href="css/bootstrap.css">
</head>
<body>
    <div class="container">
        <h1 class="well text-center">Registration</h1>
        <div class="col-lg-12 well">
            <div class="row">
                <form method="post" action="signup">
                    <div class="col-sm-12">
                        <div class="row">
                            <div class="col-sm-4 form-group">
                                <label>First Name</label>
                                <input name="name" type="text" placeholder="Enter First Name Here.." class="form-control"
                                required>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label>Surname</label>
                                <input name="surname" type="text" placeholder="Enter Last Name Here.." class="form-control"
                                required>
                            </div>
                            <div class="col-sm-4 form-group">
                                <label>Birthday</label>
                                <input name="bday" type="date" placeholder="Birthday" class="form-control"
                                       pattern="[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])"
                                       required>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-4 form-group">
                                <label>City</label>
                                <input name="city" type="text" placeholder="Enter City Name Here.." class="form-control">
                            </div>
                            <div class="col-sm-4 form-group">
                                <label for="countries">Country:</label>
                                <select name="country" id="countries" class="form-control">
                                    <option value="Ukraine">Ukraine</option>
                                    <option value="Russian Federation">Russian Federation</option>
                                    <option value="United States">United States</option>
                                    <option value="United Kingdom">United Kingdom</option>
                                    <option value="France">France</option>
                                    <option value="Germany">Germany</option>
                                    <option value="Poland">Poland</option>
                                    <option value="Chine">Chine</option>
                                    <option value="Japan">Japan</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label>Phone Number</label>
                            <input name="phone" type="tel"
                                   pattern="+[0-9]{6,}"
                                   placeholder="Enter Phone Number Here.." class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Email Address</label>
                            <input name="email" type="text" placeholder="Enter Email Address Here.." class="form-control"
                                    pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" required>
                        </div>
                        <div class="form-group">
                            <label>Password</label>
                            <input name="password" id="password" type="password" placeholder="Enter Password Here.." class="form-control"
                                   pattern=".{8,}" required>
                        </div>
                        <div class="form-group">
                            <label>Confirm password</label>
                            <input id="confirm_password" type="password" placeholder="Enter Password Here.." class="form-control"
                                    pattern=".{8,}" required>
                        </div>
                        <button type="submit" class="btn btn-lg btn-success">Sign Up</button>
                    </div>
                </form>
            </div>
        </div>
        <p style="color: red;">
            <%
                String status = (String)request.getAttribute("status");
                if(status != null) {
                    if(status.equals("email_exist")) {
                        out.print("User already exist");
                    } else if(status.equals("ex")) {
                        out.print("Database is not available");
                    }
                }
            %>
        </p>
    </div>
</body>

<script>
    var password = document.getElementById("password")
        , confirm_password = document.getElementById("confirm_password");

    function validatePassword(){
        if(password.value != confirm_password.value) {
            confirm_password.setCustomValidity("Passwords Don't Match");
        } else {
            confirm_password.setCustomValidity('');
        }
    }

    password.onchange = validatePassword;
    confirm_password.onkeyup = validatePassword;
</script>

</html>
