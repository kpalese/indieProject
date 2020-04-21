<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="title" value="View Planner - Task Tracker" />
<%@include file="../templates/head.jsp"%>

<html>
<body>
    <%@include file="../templates/header.jsp"%>
    <%@include file="../templates/navmenu.jsp"%>

    <main>
        <div class="mainContainer col-12 container-fluid">

            <div class="row">
                <div class="col-1">
                    <p class="inline">Welcome ${user.userName}!</p>
                </div>
                <div class="col-11">
                    <h2 class="inline offset-3"><i class="fas fa-angle-left"></i> Week of ${sessionScope.firstDateOfWeek} - ${sessionScope.lastDateOfWeek} <i class="fas fa-angle-right"></i></h2>
                </div>
            </div>

            <div id="plannerContainer col-7">
                <div class="row">
                    <div class="col-3 offset-1">
                        <p class="categoryHeading">Events</p>
                    </div>

                    <div class="col-3">
                        <p class="categoryHeading">Tasks</p>
                    </div>
                </div>

                <div class="row plannerRow">
                    <div class="col-1">
                        <h3>${sessionScope.firstDayOfWeek}<br/>${sessionScope.shorthandFirstDateOfWeek}</h3>
                    </div>

                    <div class="col-3">
                        <div class="card">
                            <ul class="list-group list-group-flush">
                                <li class="list-group-item">Cras justo odio</li>
                                <li class="list-group-item">Dapibus ac facilisis in</li>
                                <li class="list-group-item">Vestibulum at eros</li>
                            </ul>
                        </div>
                    </div>

                    <div class="col-3">
                        <div class="card">
                            <ul class="list-group list-group-flush">
                                <li class="list-group-item">Cras justo odio</li>
                                <li class="list-group-item">Dapibus ac facilisis in</li>
                                <li class="list-group-item">Vestibulum at eros</li>
                            </ul>
                        </div>
                    </div>
                </div>

                <div class="row plannerRow">
                    <div class="col-1">
                        <h3>${sessionScope.secondDayOfWeek}<br/>${sessionScope.shorthandSecondDateOfWeek}</h3>
                    </div>

                    <div class="col-3">
                        <p>Placeholder for events</p>
                    </div>

                    <div class="col-3">
                        <p>Placeholder for tasks</p>
                    </div>
                </div>
            </div>

            <div class="to-do-container col-5">

                <div class="row">
                    <p class="categoryHeading col-5">To Do List</p>
                </div>

                <div class="row">
                    <div class="card col-5">
                        <ul class="list-group list-group-flush">
                            <li class="list-group-item">Cras justo odio</li>
                            <li class="list-group-item">Dapibus ac facilisis in</li>
                            <li class="list-group-item">Vestibulum at eros</li>
                            <li class="list-group-item">Dapibus ac facilisis in</li>
                            <li class="list-group-item">Cras justo odio</li>
                            <li class="list-group-item">Dapibus ac facilisis in</li>
                            <li class="list-group-item">Vestibulum at eros</li>
                        </ul>
                    </div>
                </div>
            </div>

        </div>
    </main>
</body>
</html>
