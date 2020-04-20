<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="title" value="View Planner - Task Tracker" />
<%@include file="../templates/head.jsp"%>

<html>
<body>
    <%@include file="../templates/header.jsp"%>
    <%@include file="../templates/navmenu.jsp"%>

    <main>
        <div id="plannerContainer">
            <div class="container-fluid">
                <h2>Placeholder for xx/xxxx - xx/xxxx</h2>
            </div>

            <p>TESTING: Welcome user ${user.userName}!</p>
            <div class="row">
                <div class="col-1">
                    <h3>Monday<br/>x/xx</h3>
                </div>

                <div class="col-3">
                    <p>Placeholder for events</p>
                </div>

                <div class="col-3">
                    <p>Placeholder for tasks</p>
                </div>

                <div class="col-5">
                    <p>Placeholder for to-do list</p>
                </div>
            </div>
        </div>
    </main>
</body>
</html>
