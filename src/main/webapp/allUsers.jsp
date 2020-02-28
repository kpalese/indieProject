<%@include file="head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>

<%@include file="header.jsp"%>
<%@include file="navmenu.jsp"%>

<main>
    <h2>All Current Users</h2>

    <table id="userTable" class="display" cellspacing="0" width="100%">
        <tr><th>ID</th><th>User Name</th></tr>

        <tbody>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.userId}</td>
                <td>${user.userName}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>


</main>

</body>
</html>