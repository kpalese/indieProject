<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="title" value="Delete Event - Task Tracker" />
<%@include file="../templates/head.jsp"%>

<html>
<body class="geometric">
<%@include file="../templates/navmenu.jsp"%>

<main>
    <div class="wrapper">

        <FORM ACTION="${pageContext.request.contextPath}/users/deleteEventAction" METHOD="GET" id="deleteEvent" class="mainForm">
            <h2>Delete Event?</h2>

            <p class="alert alert-danger">Are you sure you want to delete this event?</p>
            <input type="hidden" name="id" value="${requestScope.eventToDelete.id}" />
            <fieldset disabled>
            <div class="form-group">
                <label for="eventName">Name of Event: </label>
                <INPUT TYPE="TEXT" NAME="eventName" class="form-control" id="eventName" required="required" value="${requestScope.eventToDelete.name}">
            </div>
            <div class="form-group">
                <label for="eventDate">Date: </label>
                <INPUT TYPE="DATE" NAME="eventDate" class="form-control" id="eventDate" required="required" value="${requestScope.eventToDelete.date}">
            </div>
            <div class="form-group">
                <label for="startTime">Start Time: </label>
                <INPUT TYPE="TIME" NAME="startTime" class="form-control" id="startTime" required="required" value="${requestScope.eventToDelete.startTime}">
            </div>
            <div class="form-group">
                <label for="endTime">End Time: </label>
                <INPUT TYPE="TIME" NAME="endTime" class="form-control" id="endTime" value="${requestScope.eventToDelete.endTime}">
            </div>
            <div class="form-group">
                <label for="notes">Notes:</label>
                <textarea class="form-control" NAME="notes" id="notes" rows="3">${requestScope.eventToDelete.notes}</textarea>
            </div>
            </fieldset>

            <a href="${pageContext.request.contextPath}/users/go?goToDate=${requestScope.eventToDelete.date}" class="btn btn-light">Do Not Delete</a>
            <INPUT TYPE="SUBMIT" VALUE="Delete Event" class="btn btn-danger">
        </FORM>
    </div>
</main>
</body>
</html>