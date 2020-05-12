<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<c:set var="title" value="View Planner - Task Tracker" />
<%@include file="../templates/head.jsp"%>

<html>
<body class="geometric">
    <%@include file="../templates/navmenu.jsp"%>

    <%--    Set variable for pageDate object--%>
    <c:set var = "pageDates"
           value = "${requestScope.pageDates}"
           scope = "request" />

    <main>
        <div class="mainContainer col-sm-12 container-fluid text-wrap">

<%--            Includes the welcome user message, week of xx/xx/xxxx header with prev and next buttons, dropdown menu to jump to a date, user messages,--%>
<%--            and Events, Tasks, and To Do List category headings--%>
            <%@include file="headerContainerForViewPlanner.jsp"%>


            <div class="row w-100">

<%--                The container for events and tasks. Loops through the 7 days of the week.--%>
                <%@include file="eventsAndTasksContainerForViewPlanner.jsp"%>

<%--                The container for to do items--%>
                <%@include file="toDoContainerForViewPlanner.jsp"%>

            </div>
        </div>
    </main>
</body>
</html>
