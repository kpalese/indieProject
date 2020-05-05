<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="title" value="Create Account - Task Tracker" />
<%@include file="templates/head.jsp"%>

<html>
<body>
<%@include file="templates/navmenu.jsp"%>

<main>
    <div class="wrapper">

        <FORM ACTION="createUserAccountAction" METHOD="GET" id="createAccountForm" class="mainForm">
            <h2>Create a Task Tracker Account</h2>

<%--            If there's a message for the user, display it--%>
            <c:if test="${not empty requestScope.userMessage}">
                <div class="userMessage alert ${requestScope.messageClass}" role="alert">
                        ${requestScope.userMessage}
                </div>
            </c:if>
            <c:remove var="userMessage"/>
            <c:remove var="messageClass"/>

            <div class="form-group">
                <label for="userName"><span class="required">*</span>User name: </label>
                <INPUT TYPE="TEXT" NAME="userName" class="form-control" id="userName" required="required"
                   <c:if test="${not empty requestScope.userName}">value="${requestScope.userName}"</c:if>>
            </div>
            <div class="form-group">
                <label for="password"><span class="required">*</span>Password: </label>
                <INPUT TYPE="PASSWORD" NAME="password" class="form-control" id="password" required="required">
            </div>
            <div class="form-group">
                <label for="confirmPassword"><span class="required">*</span>Confirm Password: </label>
                <INPUT TYPE="PASSWORD" NAME="confirmPassword" class="form-control" id="confirmPassword" required="required">
            </div>
            <INPUT TYPE="SUBMIT" VALUE="Create Account" class="btn btn-primary">
        </FORM>
    </div>
</main>
</body>
</html>
