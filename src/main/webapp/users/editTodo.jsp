<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="title" value="Edit To Do Item - Task Tracker" />
<%@include file="../templates/head.jsp"%>

<html>
<body class="geometric">
<%@include file="../templates/navmenu.jsp"%>

<main>
    <div class="wrapper">

        <FORM ACTION="${pageContext.request.contextPath}/users/editTodoAction" METHOD="GET" id="editTodo" class="mainForm">
            <h2>Edit To Do Item</h2>
            <input type="hidden" name="id" value="${requestScope.todoToEdit.id}" />
            <input type="hidden" name="returnDate" value="${requestScope.returnDate}" />
            <div class="form-group">
                <label for="todoName"><span class="required">*</span>Name of To Do Item: </label>
                <INPUT TYPE="TEXT" NAME="todoName" class="form-control" id="todoName" required="required" value="${requestScope.todoToEdit.name}">
            </div>
            <div class="form-group">
                <label for="notes">Notes:</label>
                <textarea class="form-control" NAME="notes" id="notes" rows="3" maxlength="100">${requestScope.todoToEdit.notes}</textarea>
            </div>

            <a href="${pageContext.request.contextPath}/users/go?goToDate=${requestScope.returnDate}" class="btn btn-light">Back</a>
            <INPUT TYPE="SUBMIT" VALUE="Edit Item" class="btn button-main">
        </FORM>
    </div>
</main>
</body>
</html>