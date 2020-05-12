<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="title" value="Home - Task Tracker" />
<%@include file="templates/head.jsp"%>

<html>
<body class="geometric">
    <%@include file="templates/navmenu.jsp"%>

        <main>
            <div class="wrapper" id="homePage">
                <div class="jumbotron">
                    <h1 class="display-4">Welcome to Task Tracker!</h1>
                    <p class="lead">Task Tracker will help you manage your events, tasks, and to do list.</p>
                    <hr class="my-4">
                    <p>Get started now.</p>
                    <p class="lead">
                        <a class="btn button-main btn-lg" href="${pageContext.request.contextPath}/createUserAccount" role="button">Create an Account</a>
                    </p>
                </div>
            </div>
        </main>
</body>
</html>
