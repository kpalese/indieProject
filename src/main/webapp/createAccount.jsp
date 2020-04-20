<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="title" value="Create Account - Task Tracker" />
<%@include file="templates/head.jsp"%>

<html>
<body>
<%@include file="templates/header.jsp"%>
<%@include file="templates/navmenu.jsp"%>

<main>
    <div class="wrapper">
        <h2>Create A New Task Tracker Account</h2>
        <FORM ACTION="createUserAccountAction" METHOD="POST" id="createAccountForm">
            <div class="form-group">
                <label for="userName">User name: </label>
                <INPUT TYPE="TEXT" NAME="userName" class="form-control" id="userName">
            </div>
            <div class="form-group">
                <label for="password">Password: </label>
                <INPUT TYPE="PASSWORD" NAME="password" class="form-control" id="password">
            </div>
            <div class="form-group">
                <label for="confirmPassword">Confirm Password: </label>
                <INPUT TYPE="PASSWORD" NAME="confirmPassword" class="form-control" id="confirmPassword">
            </div>
            <INPUT TYPE="SUBMIT" VALUE="Create Account" class="btn btn-primary">
        </FORM>
    </div>
</main>
</body>
</html>
