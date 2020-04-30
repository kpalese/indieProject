<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="title" value="Delete Task - Task Tracker" />
<%@include file="../templates/head.jsp"%>

<html>
<body>
<%@include file="../templates/navmenu.jsp"%>

<main>
    <div class="wrapper">
        <h2>Delete Task</h2>

        <p class="warningMessage">Are you sure you want to delete this task?</p>
        <p class="warningMessage">If this is a recurring task, all past and future instances will also be deleted.</p>

        <FORM ACTION="${pageContext.request.contextPath}/users/deleteTaskAction" METHOD="GET" id="deleteTask" class="mainForm">
            <input type="hidden" name="id" value="${requestScope.taskToDelete.id}" />
            <fieldset disabled>
            <div class="form-group">
                <label for="taskName">Name of Task: </label>
                <INPUT TYPE="TEXT" NAME="taskName" class="form-control" id="taskName" required="required" value="${requestScope.taskToDelete.name}">
            </div>
            <div class="form-group">
                <label for="taskDate"><c:if test="${requestScope.taskToDelete.frequency !='once'}">Initial </c:if>Date: </label>
                <INPUT TYPE="DATE" NAME="taskDate" class="form-control" id="taskDate" required="required" value="${requestScope.taskToDelete.date}">
            </div>
            <div class="form-group">
                <label for="frequency">Frequency: </label>
                <select class="form-control" name="frequency" id="frequency">
                    <option <c:if test="${requestScope.taskToDelete.frequency =='once'}"> selected="selected" </c:if>>once</option>
                    <option <c:if test="${requestScope.taskToDelete.frequency =='daily'}"> selected="selected" </c:if>>daily</option>
                    <option <c:if test="${requestScope.taskToDelete.frequency =='weekly'}"> selected="selected" </c:if>>weekly</option>
                </select>
            </div>
            <div class="form-group">
                <label for="notes">Notes:</label>
                <textarea class="form-control" NAME="notes" id="notes" rows="3">${requestScope.taskToDelete.notes}</textarea>
            </div>
            </fieldset>

            <%--TODO: try to go back to the planner page that the user was just on (not necessarily the page for today)--%>
            <a href="${pageContext.request.contextPath}/users/viewPlanner" class="btn btn-light">Back</a>
            <INPUT TYPE="SUBMIT" VALUE="Delete Task" class="btn btn-primary">
        </FORM>
    </div>
</main>
</body>
</html>