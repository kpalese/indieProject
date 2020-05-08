<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="title" value="Add Task - Task Tracker" />
<%@include file="../templates/head.jsp"%>

<html>
<body class="geometric">
<%@include file="../templates/navmenu.jsp"%>

<main>
    <div class="wrapper">

        <FORM ACTION="${pageContext.request.contextPath}/users/addTaskAction" METHOD="GET" id="addTask" class="mainForm">
            <h2>Add Task</h2>
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
                     <option>once</option>
                     <option>daily</option>
                     <option>weekly</option>
                </select>
            </div>
            <div class="form-group">
                <label for="notes">Notes:</label>
                <textarea class="form-control" NAME="notes" id="notes" rows="3" maxlength="100"></textarea>
            </div>

            <a href="${pageContext.request.contextPath}/users/go?goToDate=${requestScope.taskDate}" class="btn btn-light">Back</a>
            <INPUT TYPE="SUBMIT" VALUE="Add Task" class="btn button-main">
        </FORM>
    </div>
</main>
</body>
</html>