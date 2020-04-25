<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="title" value="Add Task - Task Tracker" />
<%@include file="../templates/head.jsp"%>

<html>
<body>
<%@include file="../templates/navmenu.jsp"%>

<main>
    <div class="wrapper">
        <h2>Add Task</h2>

        <FORM ACTION="${pageContext.request.contextPath}/users/addTaskAction" METHOD="GET" id="addTask" class="mainForm">
            <div class="form-group">
                <label for="taskName"><span class="required">*</span>Name of Task: </label>
                <INPUT TYPE="TEXT" NAME="taskName" class="form-control" id="taskName" required="required">
            </div>
            <div class="form-group">
                <label for="taskDate"><span class="required">*</span>Date: </label>
                <INPUT TYPE="DATE" value="${requestScope.taskDate}" NAME="taskDate" class="form-control" id="taskDate" required="required">
            </div>
            <div class="form-group">
                <label for="frequency"><span class="required">*</span>Frequency: </label>
                 <select class="form-control" name="frequency" id="frequency">
                     <option>Once</option>
                     <option>Daily</option>
                     <option>Weekly</option>
                </select>
            </div>
            <div class="form-group">
                <label for="notes">Notes:</label>
                <textarea class="form-control" NAME="notes" id="notes" rows="3"></textarea>
            </div>

            <%--TODO: try to go back to the planner page that the user was just on (not necessarily the page for today)--%>
            <a href="${pageContext.request.contextPath}/users/viewPlanner" class="btn btn-light">Back</a>
            <INPUT TYPE="SUBMIT" VALUE="Add Task" class="btn btn-primary">
        </FORM>
    </div>
</main>
</body>
</html>