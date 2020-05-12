<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="title" value="Add To Do Item - Task Tracker" />
<%@include file="../templates/head.jsp"%>

<html>
<body class="geometric">
<%@include file="../templates/navmenu.jsp"%>

<main>
    <div class="wrapper">

        <FORM ACTION="${pageContext.request.contextPath}/users/addTodoAction" METHOD="GET" id="addTodo" class="mainForm">
            <h2>Add To Do Item <a href="${pageContext.request.contextPath}/users/faq#todoFAQ" target="_blank"><i class="fas fa-info-circle" data-toggle="tooltip" title="Open To Do Item FAQ in a new tab"></i></a></h2>
            <input type="hidden" name="returnDate" value="${requestScope.returnDate}" />
            <div class="form-group">
                <label for="todoName"><span class="required">*</span>Name of To Do Item: </label>
                <INPUT TYPE="TEXT" NAME="todoName" class="form-control" id="todoName" required="required">
            </div>
            <div class="form-group">
                <label for="notes">Notes:</label>
                <textarea class="form-control" NAME="notes" id="notes" rows="3" maxlength="100"></textarea>
            </div>

            <a href="${pageContext.request.contextPath}/users/go?goToDate=${requestScope.returnDate}" class="btn btn-light">Back</a>
            <INPUT TYPE="SUBMIT" VALUE="Add Item" class="btn button-main">
        </FORM>
    </div>
</main>
</body>
</html>