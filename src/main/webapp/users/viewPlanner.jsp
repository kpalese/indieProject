<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<c:set var="title" value="View Planner - Task Tracker" />
<%@include file="../templates/head.jsp"%>

<html>
<body>
    <%@include file="../templates/navmenu.jsp"%>

    <%--    Set variable for pageDate object--%>
    <c:set var = "pageDates"
           value = "${requestScope.pageDates}"
           scope = "request" />

    <main>
        <div class="mainContainer col-sm-12 container-fluid text-wrap">

            <div class="headerContainer col-sm-12">
                <div class="row w-100">
                    <div class="col-sm-2">
                        <p class="inline-block" id="welcomeUser">Welcome ${user.userName}!</p>
                    </div>
                    <div class="col-sm-7">
                        <h2 class="inline offset-2"><a href="${pageContext.request.contextPath}/users/prev?firstDate=${pageDates.firstDateOfWeek}" data-toggle="tooltip" title="Previous week"><i class="fas fa-angle-left"></i></a>
                            Week of ${pageDates.getLocalDateToMDDYYYY(pageDates.getDateOfWeek(1))} - ${pageDates.getLocalDateToMDDYYYY(pageDates.getDateOfWeek(7))}
                            <a href="${pageContext.request.contextPath}/users/next?firstDate=${pageDates.firstDateOfWeek}" data-toggle="tooltip" title="Next week"><i class="fas fa-angle-right"></i></a></h2>
                    </div>
                    <div class="col-sm-3">
                        <div class="dropdown show w-100">
                            <a class="btn btn-secondary dropdown-toggle" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Jump to date
                            </a>
                            <div class="dropdown-menu w-100" aria-labelledby="dropdownMenuLink">
                                <a class="dropdown-item" href="${pageContext.request.contextPath}/users/viewPlanner">Go to Today</a>
                                <p class="dropdown-item">
                                    <FORM ACTION="${pageContext.request.contextPath}/users/go" METHOD="GET" class="form-inline">
                                        <label for="goToDate">Specific date</label><input type="date" NAME="goToDate" id="goToDate"><INPUT TYPE="SUBMIT" VALUE="Go" class="btn btn-primary form-control">
                                    </FORM>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-8">
                        <div class="row">
                            <div class="col offset-2 noLRPadding">
                                <p class="categoryHeading">Events</p>
                            </div>

                            <div class="col noLRPadding">
                                <p class="categoryHeading">Tasks</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-4 noLRPadding">
                            <p class="categoryHeading">To Do List</p>
                    </div>
                </div>
            </div>

            <div class="row w-100">

                <div id="plannerContainer" class="container col-sm-8">

                    <%--Display a row of events and tasks for each of the 7 days in the week--%>
                    <c:forEach var="i" begin="1" end="7" step="1" >

                        <div class="plannerRow row w-100 <c:if test="${i % 2 == 0}">shadedBackground</c:if>">
                            <div class="col-sm-2">
                                <h3>${pageDates.getDayOfWeek(pageDates.getDateOfWeekFromString(i))}<br/>${pageDates.getLocalDateToMDD(pageDates.getDateOfWeekFromString(i))}</h3>
                            </div>

                            <div class="col">
                                <div class="card w-100">
                                    <ul class="list-group list-group-flush w-100">
                                        <c:forEach var="event" items="${sessionScope.user.getEventsByDate(pageDates.getDateOfWeekFromString(i))}">
                                            <li class="list-group-item">
                                                <div class="row">
                                                    <a href="${pageContext.request.contextPath}/users/deleteEvent?id=${event.id}" class="col-sm-1"><i class="fas fa-minus-circle" data-toggle="tooltip" title="Delete event"></i></a>
                                                    <a href="${pageContext.request.contextPath}/users/editEvent?id=${event.id}" class="col" data-toggle="tooltip" title="${event.notes}">
                                                    <button type="button" class="btn btn-light"> ${event.formattedStartTime}
                                                        <c:if test="${event.formattedEndTime!=null}"> &ndash; ${event.formattedEndTime}</c:if>
                                                            ${event.name}</button></a>
                                                </div>
                                            </li>
                                        </c:forEach>
                                        <li class="list-group-item"><a href="${pageContext.request.contextPath}/users/addEvent?eventDate=${pageDates.getDateOfWeekFromString(i)}"
                                           class="btn btn-primary">Add Event</a></li>
                                    </ul>
                                </div>
                            </div>

                            <div class="col">
                                <div class="card w-100">
                                    <ul class="list-group list-group-flush w-100">
                                        <c:forEach var="task" items="${sessionScope.user.getTasksByDate(pageDates.getDateOfWeekFromString(i))}">
                                            <c:if test="${task.frequency =='once'}">
                                                <li class="list-group-item">
                                                    <div class="row">
                                                        <a href="${pageContext.request.contextPath}/users/removeTask?id=${task.id}&date=${pageDates.getDateOfWeekFromString(i)}" class="col-sm-1"><i class="fas fa-minus-circle" data-toggle="tooltip" title="Remove task"></i></a>
                                                        <a href="${pageContext.request.contextPath}/users/editTask?id=${task.id}" class="col" data-toggle="tooltip" title="${task.notes}">
                                                            <button type="button" class="btn btn-light"> ${task.name} <c:if test="${task.frequency !='once'}"> <span class="frequency">(${task.frequency})</span></c:if>
                                                            </button></a>
                                                    </div>
                                                </li>
                                            </c:if>
                                            <%--Don't let user remove future recurring tasks--%>
                                            <c:if test="${task.frequency !='once'}">
                                                <li class="list-group-item">
                                                    <div class="row">
                                                        <c:if test="${pageDates.getDateOfWeekFromString(i).isBefore(sessionScope.now) || pageDates.getDateOfWeekFromString(i).equals(now)}">
                                                            <a href="${pageContext.request.contextPath}/users/removeTask?id=${task.id}&date=${pageDates.getDateOfWeekFromString(i)}" class="col-sm-1"><i class="fas fa-minus-circle" data-toggle="tooltip" title="Remove task"></i></a>
                                                        </c:if>
                                                            <a href="${pageContext.request.contextPath}/users/editTask?id=${task.id}" class="col" data-toggle="tooltip" title="${task.notes}">
                                                            <button type="button" class="btn btn-light"> ${task.name} <c:if test="${task.frequency !='once'}"> <span class="frequency">(${task.frequency})</span></c:if>
                                                            </button></a>
                                                    </div>
                                                </li>
                                            </c:if>
                                        </c:forEach>
                                        <li class="list-group-item"><a href="${pageContext.request.contextPath}/users/addTask?taskDate=${pageDates.getDateOfWeekFromString(i)}" class="btn btn-primary">Add Task</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    <%-- Ends the loop for events and tasks for each of the seven days of the week--%>
                </div>

                <div id="to-do-container" class="col-sm-4">
                    <div class="row w-100">
                        <div class="card w-100">
                            <ul class="list-group list-group-flush w-100">
                                <c:forEach var="item" items="${sessionScope.user.todos}">
                                    <li class="list-group-item">
                                        <div class="row w-100">
                                            <a href="${pageContext.request.contextPath}/users/deleteTodo?id=${item.id}" class="col-sm-1"><i class="fas fa-minus-circle" data-toggle="tooltip" title="Delete item"></i></a>
                                            <a href="${pageContext.request.contextPath}/users/editTodo?id=${item.id}" class="col" data-toggle="tooltip" title="${item.notes}">
                                                <button type="button" class="btn btn-light"> ${item.name}</button></a>
                                        </div>
                                    </li>
                                </c:forEach>
                                <li class="list-group-item"><a href="${pageContext.request.contextPath}/users/addTodo" class="btn btn-primary">Add Item</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </main>
</body>
</html>
