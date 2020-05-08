<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="title" value="Delete To Do Item - Task Tracker" />
<%@include file="../templates/head.jsp"%>

<html>
<body class="geometric">
<%@include file="../templates/navmenu.jsp"%>

<main>
    <div class="wrapper">

        <FORM ACTION="${pageContext.request.contextPath}/users/deleteTodoAction" METHOD="GET" id="deleteTodo" class="mainForm">
            <h2>Delete To Do Item</h2>

            <p class="alert alert-danger">Are you sure you want to delete this to do item?</p>
            <input type="hidden" name="id" value="${requestScope.todoToDelete.id}" />
            <fieldset disabled>
            <div class="form-group">
                <label for="todoName">Name of To Do Item: </label>
                <INPUT TYPE="TEXT" NAME="todoName" class="form-control" id="todoName" required="required" value="${requestScope.todoToDelete.name}">
            </div>
            <div class="form-group">
                <label for="notes">Notes:</label>
                <textarea class="form-control" NAME="notes" id="notes" rows="3">${requestScope.todoToDelete.notes}</textarea>
            </div>
            </fieldset>
            <%--TODO: try to go back to the planner page that the user was just on (not necessarily the page for today)--%>
            <a href="${pageContext.request.contextPath}/users/viewPlanner" class="btn btn-light">Back</a>
            <INPUT TYPE="SUBMIT" VALUE="Delete To Do Item" class="btn btn-primary">
        </FORM>
    </div>
</main>
</body>
</html>