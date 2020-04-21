<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--If user is not logged in--%>
<c:if test="${sessionScope.user == null}">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="createUserAccount">Create an Account</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="users/viewPlanner">Login</a>
                </li>
            </ul>
        </div>
    </nav>
</c:if>

<%--If user is logged in--%>
<c:if test="${sessionScope.user != null}">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="#">Settings</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/logout">Log Out</a>
                </li>
            </ul>
        </div>
    </nav>
</c:if>