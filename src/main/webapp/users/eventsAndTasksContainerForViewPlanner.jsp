<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="plannerContainer" class="container col-sm-8">

    <%--Display a row of events and tasks for each of the 7 days in the week--%>
    <c:forEach var="i" begin="1" end="7" step="1" >

        <div class="plannerRow row w-100 <c:if test="${i % 2 == 0}">shadedBackground</c:if><c:if test="${i % 2 != 0}">darkShadedBackground</c:if>">
            <div class="col-sm-2">
                <h3>${pageDates.getDayOfWeek(pageDates.getDateOfWeekFromString(i))}<br/>${pageDates.getLocalDateToMDD(pageDates.getDateOfWeekFromString(i))}</h3>
            </div>

            <div class="col">
                <div class="card w-100">
                    <ul class="list-group list-group-flush w-100">
                            <%--                                    If user settings are to display holidays, first display any holidays for this date--%>
                        <c:if test="${sessionScope.user.includeHolidays}">
                            <c:forEach var="holiday" items="${sessionScope.holidays}">
                                <c:if test="${holiday.dateToLocalDate().equals(pageDates.getDateOfWeekFromString(i))}">
                                    <li class="list-group-item">
                                        <div class="row holiday" data-toggle="tooltip" title="${holiday.description}">
                                                ${holiday.name}
                                        </div>
                                    </li>
                                </c:if>
                            </c:forEach>
                        </c:if>
                            <%--                                        Display user's non-holiday events--%>
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
                                                       class="btn button-main">Add Event</a></li>
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
                                        <a href="${pageContext.request.contextPath}/users/removeTask?id=${task.id}&removeDate=${pageDates.getDateOfWeekFromString(i)}" class="col-sm-1"><i class="fas fa-minus-circle" data-toggle="tooltip" title="Remove task"></i></a>
                                        <a href="${pageContext.request.contextPath}/users/editTask?id=${task.id}&goToDate=${pageDates.getDateOfWeekFromString(i)}" class="col" data-toggle="tooltip" title="${task.notes}">
                                            <button type="button" class="btn btn-light"> ${task.name} <c:if test="${task.frequency !='once'}"> <span class="frequency">(${task.frequency})</span></c:if>
                                            </button></a>
                                    </div>
                                </li>
                            </c:if>
                            <%--Don't let user remove future recurring tasks--%>
                            <c:if test="${task.frequency !='once'}">
                                <li class="list-group-item">
                                    <div class="row">
                                        <c:if test="${pageDates.getDateOfWeekFromString(i).isBefore(sessionScope.now) || pageDates.getDateOfWeekFromString(i).equals(sessionScope.now)}">
                                            <a href="${pageContext.request.contextPath}/users/removeTask?id=${task.id}&removeDate=${pageDates.getDateOfWeekFromString(i)}" class="col-sm-1"><i class="fas fa-minus-circle" data-toggle="tooltip" title="Remove task"></i></a>
                                        </c:if>
                                        <a href="${pageContext.request.contextPath}/users/editTask?id=${task.id}&goToDate=${pageDates.getDateOfWeekFromString(i)}" class="col" data-toggle="tooltip" title="${task.notes}">
                                            <button type="button" class="btn btn-light"> ${task.name} <c:if test="${task.frequency !='once'}"> <span class="frequency">(${task.frequency})</span></c:if>
                                            </button></a>
                                    </div>
                                </li>
                            </c:if>
                        </c:forEach>
                        <li class="list-group-item"><a href="${pageContext.request.contextPath}/users/addTask?taskDate=${pageDates.getDateOfWeekFromString(i)}" class="btn button-main">Add Task</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </c:forEach>
    <%-- Ends the loop for events and tasks for each of the seven days of the week--%>
</div>