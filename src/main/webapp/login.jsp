<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="title" value="Login - Task Tracker" />
<%@include file="templates/head.jsp"%>

<html>
<body>

    <%@include file="templates/header.jsp"%>
    <%@include file="templates/navmenu.jsp"%>

    <main>
        <div class="wrapper">
            <FORM ACTION="j_security_check" METHOD="POST" class="mainForm">
                <div class="form-group">
                    <label for="j_username"><span class="required">*</span>User name: </label>
                    <INPUT TYPE="TEXT" NAME="j_username" class="form-control" id="j_username" required="required">
                </div>
                <div class="form-group">
                    <label for="j_password"><span class="required">*</span>Password: </label>
                    <INPUT TYPE="PASSWORD" NAME="j_password" class="form-control" id="j_password" required="required">
                </div>
                <INPUT TYPE="SUBMIT" VALUE="Log In" class="btn btn-primary">
            </FORM>
        </div>
    </main>

</body>
</html>
