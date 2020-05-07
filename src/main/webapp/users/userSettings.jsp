<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="title" value="User Settings - Task Tracker" />
<%@include file="../templates/head.jsp"%>

<html>
<body>
<%@include file="../templates/navmenu.jsp"%>

<main>
    <div class="wrapper">

        <FORM ACTION="${pageContext.request.contextPath}/users/userSettingsAction" METHOD="GET" id="userSettings">
            <h2>User Settings</h2>

                <div class="form-group fieldset">
                    <legend>Include US national holidays on your calendar?</legend>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="holidayOptions" id="yesHolidays" value="yesHolidays"
                        <c:if test = "${sessionScope.user.includeHolidays}">
                               checked="checked"
                        </c:if>>
                        <label class="form-check-label" for="yesHolidays">Include US national holidays on my calendar</label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="holidayOptions" id="noHolidays" value="noHolidays"
                        <c:if test = "${not sessionScope.user.includeHolidays}">
                               checked="checked"
                        </c:if>>
                        <label class="form-check-label" for="noHolidays">Do not include US national holidays on my calendar</label>
                    </div>
                </div><br/>

                <div class="form-group fieldset">
                    <legend>Start the week on Sunday or Monday?</legend>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="weekStart" id="sunStart" value="Sunday"
                        <c:if test = "${sessionScope.user.weekStart.equals('Sunday')}">
                               checked="checked"
                        </c:if>>
                        <label class="form-check-label" for="sunStart">Begin the week on Sunday</label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="weekStart" id="monStart" value="Monday"
                        <c:if test = "${sessionScope.user.weekStart.equals('Monday')}">
                               checked="checked"
                        </c:if>>
                        <label class="form-check-label" for="monStart">Begin the week on Monday</label>
                    </div>
                </div><br/>

            <a href="${pageContext.request.contextPath}/users/viewPlanner" class="btn btn-light">Back</a>
            <INPUT TYPE="SUBMIT" VALUE="Save" class="btn btn-primary">
        </FORM>
    </div>
</main>
</body>
</html>