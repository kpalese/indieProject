<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="title" value="Edit Task - Task Tracker" />
<%@include file="../templates/head.jsp"%>

<html>
<body>
<%@include file="../templates/navmenu.jsp"%>

<main>
    <div class="wrapper">
        <h2>Edit Task</h2>

        <FORM ACTION="${pageContext.request.contextPath}/users/editTaskAction" METHOD="GET" id="editTask" class="mainForm">
            <input type="hidden" name="id" value="${requestScope.taskToEdit.id}" />
            <div class="form-group">
                <label for="taskName"><span class="required">*</span>Name of Task: </label>
                <INPUT TYPE="TEXT" NAME="taskName" class="form-control" id="taskName" required="required" value="${requestScope.taskToEdit.name}">
            </div>
            <div class="form-group">
                <label for="taskDate"><span class="required">*</span>Date: </label>
                <INPUT TYPE="DATE" NAME="taskDate" class="form-control" id="taskDate" required="required" value="${requestScope.taskToEdit.date}">
            </div>
            <div class="form-group">
                <label for="frequency"><span class="required">*</span>Frequency: </label>
                <select class="form-control" name="frequency" id="frequency">
                    <option <c:if test="${requestScope.taskToEdit.frequency =='once'}"> selected="selected" </c:if>>once</option>
                    <option <c:if test="${requestScope.taskToEdit.frequency =='daily'}"> selected="selected" </c:if>>daily</option>
                    <option <c:if test="${requestScope.taskToEdit.frequency =='weekly'}"> selected="selected" </c:if>>weekly</option>
                </select>
            </div>
            <div class="form-group">
                <label for="notes">Notes:</label>
                <textarea class="form-control" NAME="notes" id="notes" rows="3">${requestScope.taskToEdit.notes}</textarea>
            </div>

            <%--TODO: try to go back to the planner page that the user was just on (not necessarily the page for today)--%>
            <a href="${pageContext.request.contextPath}/users/viewPlanner" class="btn btn-light">Back</a>
            <INPUT TYPE="SUBMIT" VALUE="Edit Task" class="btn btn-primary">
        </FORM>
    </div>
</main>
</body>
</html>