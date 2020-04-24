<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="title" value="View Planner - Task Tracker" />
<%@include file="../templates/head.jsp"%>

<html>
<body>
    <%@include file="../templates/header.jsp"%>
    <%@include file="../templates/navmenu.jsp"%>

    <%--    Set variable for pageDate object--%>
    <c:set var = "pageDates"
           value = "${requestScope.pageDates}"
           scope = "request" />

    <main>
        <div class="mainContainer col-12 container-fluid text-wrap">

            <div class="headerContainer col-12">
                <div class="row w-100">
                    <div class="col-2">
                        <p class="inline">Welcome ${user.userName}!</p>
                    </div>
                    <div class="col-10">
                        <h2 class="inline offset-2"><i class="fas fa-angle-left">
                        </i> Week of ${pageDates.getLocalDateToMDDYYYY(pageDates.getDateOfWeek(1))} - ${pageDates.getLocalDateToMDDYYYY(pageDates.getDateOfWeek(7))} <i class="fas fa-angle-right"></i></h2>
                    </div>
                </div>
            </div>

            <div class="row w-100">

                <div id="plannerContainer" class="container col-8">
                    <div class="row w-100">
                        <div class="col offset-2">
                            <p class="categoryHeading">Events</p>
                        </div>

                        <div class="col">
                            <p class="categoryHeading">Tasks</p>
                        </div>
                    </div>

                    <div class="plannerRow row w-100">
                        <div class="col-2">
                            <h3>${pageDates.getDayOfWeek(pageDates.getDateOfWeek(1))}<br/>${pageDates.getLocalDateToMDD(pageDates.getDateOfWeek(1))}</h3>
                        </div>

                        <div class="col">
                            <div class="card w-100">
                                <ul class="list-group list-group-flush w-100">

                                    <c:forEach var="event" items="${sessionScope.user.getEventsByDate(pageDates.getDateOfWeek(1))}">
                                        <li class="list-group-item"><a href="${pageContext.request.contextPath}/users/editEvent?id=${event.id}">
                                            <button type="button" class="btn btn-light" data-toggle="tooltip" title="${event.notes}">
                                                <a href="${pageContext.request.contextPath}/users/deleteEvent?id=${event.id}"><i class="fas fa-minus-circle" data-toggle="tooltip" title="Delete event"></i></a>
                                                ${event.formattedStartTime}
                                                <c:if test="${event.formattedEndTime!=null}"> &ndash; ${event.formattedEndTime}</c:if>
                                                ${event.name}</button></a></li>
                                    </c:forEach>

                                    <li class="list-group-item"><a href="${pageContext.request.contextPath}/users/addEvent?eventDate=${pageDates.getDateOfWeek(1)}"
                                       class="btn btn-primary">Add Event</a></li>
                                </ul>
                            </div>
                        </div>

                        <div class="col">
                            <div class="card w-100">
                                <ul class="list-group list-group-flush w-100">
                                    <li class="list-group-item w-100">Cras justo odio</li>
                                    <li class="list-group-item">Dapibus ac facilisis in</li>
                                    <li class="list-group-item">Vestibulum at eros</li>
                                    <li class="list-group-item"><a href="${pageContext.request.contextPath}/users/addTask" class="btn btn-primary">Add Task</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>

                    <div class="plannerRow row w-100">
                        <div class="col-2">
                            <h3>${pageDates.getDayOfWeek(pageDates.getDateOfWeek(2))}<br/>${pageDates.getLocalDateToMDD(pageDates.getDateOfWeek(2))}</h3>
                        </div>

                        <div class="col">
                            <p>Placeholder for events</p>
                        </div>

                        <div class="col">
                            <p>Placeholder for tasks</p>
                        </div>
                    </div>

                    <p>Fake text</p>
                    <p>Fake text</p>
                    <p>Fake text</p>
                    <p>Fake text</p>
                    <p>Fake text</p>
                    <p>Fake text</p>
                    <p>Fake text</p>
                    <p>Fake text</p>
                    <p>Fake text</p>
                    <p>Fake text</p>
                    <p>Fake text</p>
                    <p>Fake text</p>
                    <p>Fake text</p>
                    <p>Fake text</p>
                    <p>Fake text</p>
                    <p>Fake text</p>
                    <p>Fake text</p>
                    <p>Fake text</p>
                    <p>Fake text</p>
                    <p>Fake text</p>
                    <p>Fake text</p>
                    <p>Fake text</p>

                </div>

                <div id="to-do-container" class="col-4">

                    <div class="row">
                        <p class="categoryHeading">To Do List</p>
                    </div>

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
