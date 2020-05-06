<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="title" value="Add To Do Item - Task Tracker" />
<%@include file="../templates/head.jsp"%>

<html>
<body>
<%@include file="../templates/navmenu.jsp"%>

<main>
    <div class="wrapper">

        <FORM ACTION="${pageContext.request.contextPath}/users/addTodoAction" METHOD="GET" id="addTodo" class="mainForm">
            <h2>Add To Do Item</h2>
            <div class="form-group">
                <label for="todoName"><span class="required">*</span>Name of To Do Item: </label>
                <INPUT TYPE="TEXT" NAME="todoName" class="form-control" id="todoName" required="required">
            </div>
            <div class="form-group">
                <label for="notes">Notes:</label>
                <textarea class="form-control" NAME="notes" id="notes" rows="3" maxlength="100"></textarea>
            </div>

            <%--TODO: try to go back to the planner page that the user was just on (not necessarily the page for today)--%>
            <a href="${pageContext.request.contextPath}/users/viewPlanner" class="btn btn-light">Back</a>
            <INPUT TYPE="SUBMIT" VALUE="Add To Do Item" class="btn btn-primary">
        </FORM>
    </div>
</main>
</body>
</html>