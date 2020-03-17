<%@include file="templates/head.jsp"%>
<c:set var="title" value="Login - Task Tracker" />
<html>
<body>

    <%@include file="templates/header.jsp"%>
    <%@include file="templates/navmenu.jsp"%>

    <main>
        <div class="wrapper">
            <FORM ACTION="j_security_check" METHOD="POST" id="loginForm">
                <div class="form-group">
                    <label for="j_username">User name: </label>
                    <INPUT TYPE="TEXT" NAME="j_username" class="form-control" id="j_username">
                </div>
                <div class="form-group">
                    <label for="j_password">Password: </label>
                    <INPUT TYPE="PASSWORD" NAME="j_password" class="form-control" id="j_password">
                </div>
                <INPUT TYPE="SUBMIT" VALUE="Log In" class="btn btn-primary">
            </FORM>
        </div>
    </main>

</body>
</html>
