<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--If user is not logged in--%>
<c:if test="${sessionScope.user == null}">
    <nav class="navbar navbar-expand-lg bg-light">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp">
            Task Tracker
        </a>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link btn solidButton" href="createUserAccount">Create an Account</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link btn outlineButton" href="users/viewPlanner">Login</a>
                </li>
            </ul>
        </div>
    </nav>
</c:if>

<%--If user is logged in--%>
<c:if test="${sessionScope.user != null}">
    <nav class="navbar navbar-expand-lg bg-light">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp">
            Task Tracker
        </a>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link solidButton" href="${pageContext.request.contextPath}/users/viewPlanner">My Planner</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link solidButton" href="${pageContext.request.contextPath}/users/userSettings">My Settings</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link solidButton" href="${pageContext.request.contextPath}/users/faq">FAQ</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link outlineButton" href="${pageContext.request.contextPath}/logout">Log Out</a>
                </li>
            </ul>
        </div>
    </nav>
</c:if>