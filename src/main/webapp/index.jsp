<%@include file="templates/head.jsp"%>
<c:set var="title" value="Home - Task Tracker" />

<html>
<body>
    <%@include file="templates/header.jsp"%>
    <%@include file="templates/navmenu.jsp"%>

        <main>
            <div class="wrapper">
            <h2>Welcome to Task Tracker!</h2>

            <a href="users/viewPlanner">Click here to view your planner (for logged in users only - this will be removed/edited eventually!)</a><br/><br/>

                <a href="createUserAccount">Create a new account (will be moved eventually)</a><br/><br/>

            <a href="allUsers">Display All Users (for the sake of exercise 6...This will be removed eventually!)</a>
            </div>
        </main>
</body>
</html>
