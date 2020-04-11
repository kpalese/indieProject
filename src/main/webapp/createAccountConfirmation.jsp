<%@include file="templates/head.jsp"%>
<c:set var="title" value="Success! Account Created - Task Tracker" />

<html>
<body>
<%@include file="templates/header.jsp"%>
<%@include file="templates/navmenu.jsp"%>

<main>
    <div class="wrapper">
        <h2>Success!</h2>
        <p>Your account was successfully created. Do you want to <a href="users/viewPlanner">sign in</a>?</p>
<%--     TODO: Success/failure message--%>
    </div>
</main>
</body>
</html>
