<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="to-do-container" class="col-sm-4">
    <div class="row w-100">
        <div class="card w-100">
            <ul class="list-group list-group-flush w-100">
                <c:forEach var="item" items="${sessionScope.user.todos}">
                    <li class="list-group-item">
                        <div class="row w-100">
                            <a href="${pageContext.request.contextPath}/users/deleteTodo?id=${item.id}&returnDate=${pageDates.getDateOfWeekFromString("1")}" class="col-sm-1"><i class="fas fa-minus-circle" data-toggle="tooltip" title="Delete item"></i></a>
                            <a href="${pageContext.request.contextPath}/users/editTodo?id=${item.id}&returnDate=${pageDates.getDateOfWeekFromString("1")}" class="col" data-toggle="tooltip" title="${item.notes}">
                                <button type="button" class="btn btn-light"> ${item.name}</button></a>
                        </div>
                    </li>
                </c:forEach>
                <li class="list-group-item"><a href="${pageContext.request.contextPath}/users/addTodo?returnDate=${pageDates.getDateOfWeekFromString("1")}" class="btn button-main">Add Item</a></li>
            </ul>
        </div>
    </div>
</div>