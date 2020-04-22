<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="title" value="Add Event - Task Tracker" />
<%@include file="../templates/head.jsp"%>

<html>
<body>
<%@include file="../templates/header.jsp"%>
<%@include file="../templates/navmenu.jsp"%>

    <main>
        <h2>Add </h2>

        EVENT DATE TEST: ${sessionScope.eventDate}

        <div class="wrapper">
            <FORM ACTION="" METHOD="POST" id="addEvent">
                <div class="form-group">
                    <label for="eventName"><span class="required">*</span>Name of Event: </label>
                    <INPUT TYPE="TEXT" NAME="eventName" class="form-control" id="eventName">
                </div>
                <div class="form-group">
                    <label for="eventDate"><span class="required">*</span>Date: </label>
                    <INPUT TYPE="DATE" NAME="eventDate" class="form-control" id="eventDate">
                </div>
                <div class="form-group">
                    <label for="startTime"><span class="required">*</span>Start Time: </label>
                    <INPUT TYPE="TIME" NAME="startTime" class="form-control" id="startTime">
                </div>
                <div class="form-group">
                    <label for="endTime">End Time: </label>
                    <INPUT TYPE="TIME" NAME="endTime" class="form-control" id="endTime">
                </div>
                <div class="form-group">
                    <label for="notes">Notes</label>
                    <textarea class="form-control" id="notes" rows="3"></textarea>
                </div>

<%--TODO: try to go back to the planner page that the user was just on (not necessarily the page for today)--%>
                <a href="<%=request.getContextPath()%>/users/viewPlanner" class="btn btn-light">Back</a>
                <INPUT TYPE="SUBMIT" VALUE="Add Event" class="btn btn-primary">
            </FORM>
        </div>
    </main>
</body>
</html>