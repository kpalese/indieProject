<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="title" value="User Settings - Task Tracker" />
<%@include file="../templates/head.jsp"%>

<html>
<body>
<%@include file="../templates/navmenu.jsp"%>

<main>
    <div class="wrapper">


        <FORM ACTION="${pageContext.request.contextPath}/users/userSettingsAction" METHOD="GET" id="userSettings" class="mainForm">
            <h2>User Settings</h2>

<%--            TODO: I think I'm just going to automatically forward everyone's tasks because that it the default and
                   I don't see why anyone would want to delete them just because it's a new week--%>
<%--            <div class="form-group">--%>
<%--                <legend>Automatically forward your incomplete tasks to the next week?</legend>--%>
<%--                <div class="form-check">--%>
<%--                    <input class="form-check-input" type="radio" name="autoForwardOptions" id="yesAutoForward" value="yesAutoForward"--%>
<%--                    <c:if test = "${requestScope.autoForward}">--%>
<%--                           checked="checked"--%>
<%--                    </c:if>>--%>
<%--                    <label class="form-check-label" for="yesAutoForward">Yes, automatically forward my incomplete tasks to the next week</label>--%>
<%--                </div>--%>
<%--                <div class="form-check">--%>
<%--                    <input class="form-check-input" type="radio" name="autoForwardOptions" id="noAutoForward" value="noAutoForward"--%>
<%--                    <c:if test = "${not requestScope.autoForward}">--%>
<%--                           checked="checked"--%>
<%--                    </c:if>>--%>
<%--                    <label class="form-check-label" for="noAutoForward">No, do not forward my incomplete tasks to the next week</label>--%>
<%--                </div>--%>
<%--            </div><br/>--%>

        <%--TODO: Setting for determining the first day of the week?--%>

            <a href="${pageContext.request.contextPath}/users/viewPlanner" class="btn btn-light">Back</a>
            <INPUT TYPE="SUBMIT" VALUE="Save" class="btn btn-primary">
        </FORM>

    </div>
</main>
</body>
</html>