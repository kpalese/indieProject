<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="title" value="Home - Task Tracker" />
<%@include file="templates/head.jsp"%>

<html>
<body>
    <%@include file="templates/header.jsp"%>
    <%@include file="templates/navmenu.jsp"%>

        <main>
            <div class="wrapper">
                <h2>Welcome to Task Tracker!</h2>

<%--                TODO: Message if user just logged out--%>

                <p>Text explaining Task Tracker will go here.</p>
            </div>
        </main>
</body>
</html>
