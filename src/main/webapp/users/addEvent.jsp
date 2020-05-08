<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="title" value="Add Event - Task Tracker" />
<%@include file="../templates/head.jsp"%>

<html>
<body class="geometric">
<%@include file="../templates/navmenu.jsp"%>

    <main>
        <div class="wrapper">

            <FORM ACTION="${pageContext.request.contextPath}/users/addEventAction" METHOD="GET" id="addEvent" class="mainForm">
                <h2>Add Event</h2>
                <div class="form-group">
                    <label for="eventName"><span class="required">*</span>Name of Event: </label>
                    <INPUT TYPE="TEXT" NAME="eventName" class="form-control" id="eventName" required="required">
                </div>
                <div class="form-group">
                    <label for="eventDate"><span class="required">*</span>Date: </label>
                    <INPUT TYPE="DATE" value="${requestScope.eventDate}" NAME="eventDate" class="form-control" id="eventDate" required="required">
                </div>
                <div class="form-group">
                    <label for="startTime"><span class="required">*</span>Start Time: </label>
                    <INPUT TYPE="TIME" NAME="startTime" class="form-control" id="startTime" required="required">
                </div>
                <div class="form-group">
                    <label for="endTime">End Time: </label>
                    <INPUT TYPE="TIME" NAME="endTime" class="form-control" id="endTime">
                </div>
                <div class="form-group">
                    <label for="notes">Notes:</label>
                    <textarea class="form-control" NAME="notes" id="notes" rows="3" maxlength="100"></textarea>
                </div>

                <a href="${pageContext.request.contextPath}/users/go?goToDate=${requestScope.eventDate}" class="btn btn-light">Back</a>
                <INPUT TYPE="SUBMIT" VALUE="Add Event" class="btn button-main">
            </FORM>
        </div>
    </main>
</body>
</html>