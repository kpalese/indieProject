<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="title" value="Edit Task - Task Tracker" />
<%@include file="../templates/head.jsp"%>

<html>
<body class="geometric">
<%@include file="../templates/navmenu.jsp"%>

<main>
    <div class="wrapper">

        <FORM ACTION="${pageContext.request.contextPath}/users/editTaskAction" METHOD="GET" id="editTask" class="mainForm">
            <h2>Edit Task</h2>
            <input type="hidden" name="id" value="${requestScope.taskToEdit.id}" />
            <input type="hidden" name="goToDate" value="${requestScope.goToDate}" />
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
                <textarea class="form-control" NAME="notes" id="notes" rows="3" maxlength="100">${requestScope.taskToEdit.notes}</textarea>
            </div>

            <a href="${pageContext.request.contextPath}/users/go?goToDate=${requestScope.goToDate}" class="btn btn-light">Back</a>
            <INPUT TYPE="SUBMIT" VALUE="Save Changes" class="btn button-main">
        </FORM>
    </div>
</main>
</body>
</html>