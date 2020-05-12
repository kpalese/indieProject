<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="title" value="404 Error - Page Not Found" />
<%@include file="templates/head.jsp"%>

<html>
<body class="geometric">
<%@include file="templates/navmenu.jsp"%>

<main>
    <div class="wrapper">
        <div class="userMessage alert alert-danger" role="alert">
            Oops! We can't find that page. Please try again.
        </div>
    </div>
</main>
</body>
</html>
