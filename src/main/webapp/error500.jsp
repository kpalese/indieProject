<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="title" value="500 Error" />
<%@include file="templates/head.jsp"%>

<html>
<body class="geometric">
<%@include file="templates/navmenu.jsp"%>

<main>
    <div class="wrapper">
        <div class="userMessage alert alert-danger" role="alert">
            Oops! Something has gone wrong. Please try again.
        </div>
    </div>
</main>
</body>
</html>
