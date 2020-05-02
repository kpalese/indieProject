<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="title" value="Remove Task - Task Tracker" />
<%@include file="../templates/head.jsp"%>

<html>
<body>
<%@include file="../templates/navmenu.jsp"%>

<main>
    <div class="wrapper">

        <FORM ACTION="${pageContext.request.contextPath}/users/removeTaskAction" METHOD="GET" id="removeTask" class="mainForm">
            <h2>Remove Task</h2>
            <div id="removeTaskInstance">
    <%--            Warning message for 'once' tasks--%>
                <c:if test="${requestScope.taskToRemove.frequency =='once'}">
                    <p class="alert alert-danger">Are you sure you want to remove this task?</p>
                </c:if>
    <%--            Warning message for recurring tasks--%>
                <c:if test="${requestScope.taskToRemove.frequency !='once'}">
                    <p class="alert alert-danger">This is a recurring task. Which task(s) do you want to remove?</p>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="instances" id="onlyThis" value="onlyThis">
                        <label class="form-check-label" for="onlyThis">Remove <b>this instance and all prior instances</b>.</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="instances" id="allInstances" value="allInstances">
                        <label class="form-check-label" for="allInstances">Remove <b>all instances</b> of this task. I'm no longer tracking it.</label>
                    </div>
                </c:if>
            </div>

            <input type="hidden" name="id" value="${requestScope.taskToRemove.id}" />
            <input type="hidden" name="removeDate" value="${requestScope.removeDate}" />
            <input type="hidden" name="frequency" value="${requestScope.taskToRemove.frequency}" />
            <fieldset disabled>
            <div class="form-group">
                <label for="taskName">Name of Task: </label>
                <INPUT TYPE="TEXT" NAME="taskName" class="form-control" id="taskName" required="required" value="${requestScope.taskToRemove.name}">
            </div>
            <div class="form-group">
                <label for="taskDate"><c:if test="${requestScope.taskToRemove.frequency !='once'}">Initial </c:if>Date: </label>
                <INPUT TYPE="DATE" NAME="taskDate" class="form-control" id="taskDate" required="required" value="${requestScope.taskToRemove.date}">
            </div>
            <div class="form-group">
                <label for="frequency">Frequency: </label>
                <select class="form-control" name="frequency" id="frequency">
                    <option <c:if test="${requestScope.taskToRemove.frequency =='once'}"> selected="selected" </c:if>>once</option>
                    <option <c:if test="${requestScope.taskToRemove.frequency =='daily'}"> selected="selected" </c:if>>daily</option>
                    <option <c:if test="${requestScope.taskToRemove.frequency =='weekly'}"> selected="selected" </c:if>>weekly</option>
                </select>
            </div>
            <div class="form-group">
                <label for="notes">Notes:</label>
                <textarea class="form-control" NAME="notes" id="notes" rows="3">${requestScope.taskToRemove.notes}</textarea>
            </div>
            </fieldset>

            <a href="${pageContext.request.contextPath}/users/go?goToDate=${requestScope.removeDate}" class="btn btn-light">Do Not Remove</a>
            <INPUT TYPE="SUBMIT" VALUE="Remove Task" class="btn btn-danger">
        </FORM>
    </div>
</main>
</body>
</html>