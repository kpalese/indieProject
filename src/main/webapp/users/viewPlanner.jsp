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
                    <div class="col-sm-10">
                        <h2 class="inline offset-2"><i href="#" class="fas fa-angle-left">
                        </i> Week of ${pageDates.getLocalDateToMDDYYYY(pageDates.getDateOfWeek(1))} - ${pageDates.getLocalDateToMDDYYYY(pageDates.getDateOfWeek(7))} <i href="#" class="fas fa-angle-right"></i></h2>
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

                        <div class="plannerRow row w-100">
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
                                            <c:if test="${task.lastDateCompleted == pageDates.getDateOfWeekFromString(i)}">
                                            <li class="list-group-item">
                                                <div class="row">
                                                    <a href="${pageContext.request.contextPath}/users/deleteTask?id=${task.id}" class="col-sm-1"><i class="fas fa-minus-circle" data-toggle="tooltip" title="Delete task"></i></a>
                                                    <a class="col offset-1" data-toggle="tooltip" title="${task.notes}">
                                                        <button type="button" class="btn btn-light" disabled><s> ${task.name} <c:if test="${task.frequency !='once'}"> <span class="frequency">(${task.frequency})</span></c:if></s>
                                                            </button></a>
                                                </div>
                                            </li>
                                            </c:if>

                                            <c:if test="${task.lastDateCompleted != pageDates.getDateOfWeekFromString(i)}">
                                                <li class="list-group-item">
                                                    <div class="row">
                                                        <a href="${pageContext.request.contextPath}/users/deleteTask?id=${task.id}" class="col-sm-1"><i class="fas fa-minus-circle" data-toggle="tooltip" title="Delete task"></i></a>
                                                        <a href="${pageContext.request.contextPath}/users/completeTaskAction?id=${task.id}&date=${pageDates.getDateOfWeekFromString(i)}" class="col-sm-1"><i class="fas fa-check-square" data-toggle="tooltip" title="Complete task"></i></a>
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

<%--                    <div class="row">--%>
<%--                        <p class="categoryHeading">To Do List</p>--%>
<%--                    </div>--%>

                    <div class="row w-100">
                        <div class="card w-100">
                            <ul class="list-group list-group-flush w-100">
                                <li class="list-group-item">Cras justo odio</li>
                                <li class="list-group-item">Dapibus ac facilisis in</li>
                                <li class="list-group-item">Vestibulum at eros</li>
                                <li class="list-group-item">Dapibus ac facilisis in</li>
                                <li class="list-group-item">Cras justo odio</li>
                                <li class="list-group-item">Dapibus ac facilisis in</li>
                                <li class="list-group-item">Vestibulum at eros</li>
                                <li class="list-group-item"><a href="${pageContext.request.contextPath}/users/addToDoItem" class="btn btn-primary">Add Item</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </main>
</body>
</html>
