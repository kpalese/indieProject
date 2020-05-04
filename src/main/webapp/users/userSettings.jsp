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

            <div class="form-group">
                <p>Do you want to include US national holidays on your calendar?</p>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="holidayOptions" id="yesHolidays" value="yesHolidays"
                    <c:if test = "${requestScope.includeHolidays}">
                           checked="checked"
                    </c:if>>
                    <label class="form-check-label" for="yesHolidays">Include US national holidays on my calendar</label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="holidayOptions" id="noHolidays" value="noHolidays"
                    <c:if test = "${not requestScope.includeHolidays}">
                           checked="checked"
                    </c:if>>
                    <label class="form-check-label" for="noHolidays">Do not include US national holidays on my calendar</label>
                </div>
            </div>



        <%--TODO: Setting for determining the first day of the week?--%>

            <a href="${pageContext.request.contextPath}/users/viewPlanner" class="btn btn-light">Back</a>
            <INPUT TYPE="SUBMIT" VALUE="Save" class="btn btn-primary">
        </FORM>

    </div>
</main>
</body>
</html>