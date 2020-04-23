<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="title" value="View Planner - Task Tracker" />
<%@include file="../templates/head.jsp"%>

<html>
<body>
    <%@include file="../templates/header.jsp"%>
    <%@include file="../templates/navmenu.jsp"%>

    <main>
        <div class="mainContainer col-12 container-fluid text-wrap">

            <div class="headerContainer col-12">
                <div class="row w-100">
                    <div class="col-2">
                        <p class="inline">Welcome ${user.userName}!</p>
                    </div>
                    <div class="col-10">
                        <h2 class="inline offset-2"><i class="fas fa-angle-left"></i> Week of ${sessionScope.firstDateOfWeek} - ${sessionScope.lastDateOfWeek} <i class="fas fa-angle-right"></i></h2>
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
                            <h3>${sessionScope.firstDayOfWeek}<br/>${sessionScope.shorthandFirstDateOfWeek}</h3>
                        </div>

                        <div class="col">
                            <div class="card w-100">
                                <ul class="list-group list-group-flush w-100">

                                    <c:forEach var="event" items="${sessionScope.eventsDay1}">
                                        <li class="list-group-item">${event.formattedStartTime}
                                            <c:if test="${event.formattedEndTime!=null}"> &ndash; ${event.formattedEndTime}</c:if> ${event.name}</li>
                                    </c:forEach>

                                    <li class="list-group-item"><a href="<%=request.getContextPath()%>/users/addEvent?eventDate=${sessionScope.firstDateOfWeek}"
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
                                    <li class="list-group-item"><a href="<%=request.getContextPath()%>/users/addTask" class="btn btn-primary">Add Task</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>

                    <div class="plannerRow row w-100">
                        <div class="col-2">
                            <h3>${sessionScope.secondDayOfWeek}<br/>${sessionScope.shorthandSecondDateOfWeek}</h3>
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
                                <li class="list-group-item"><a href="<%=request.getContextPath()%>/users/addToDoItem" class="btn btn-primary">Add Item</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </main>
</body>
</html>
