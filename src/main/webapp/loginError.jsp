<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="title" value="Login Error - Task Tracker" />
<%@include file="templates/head.jsp"%>

<html>
<body>
<%@include file="templates/navmenu.jsp"%>

    <main>
        <div class="wrapper">
            <div class="userMessage alert alert-danger" role="alert">
                Login failed...please try again
            </div>
        </div>
    </main>
</body>
</html>
