<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="headerContainer col-sm-12">
    <div class="row w-100">
        <div class="col-sm-2">
            <p class="inline-block" id="welcomeUser">Welcome ${sessionScope.user.userName}!</p>
        </div>
        <div class="col-sm-7">
            <h2 class="inline offset-2"><a href="${pageContext.request.contextPath}/users/go?goToDate=${pageDates.firstDateOfWeek.minusDays(7)}" data-toggle="tooltip" title="Previous week"><i class="fas fa-angle-left"></i></a>
                Week of ${pageDates.getLocalDateToMDDYYYY(pageDates.getDateOfWeek(1))} - ${pageDates.getLocalDateToMDDYYYY(pageDates.getDateOfWeek(7))}
                <a href="${pageContext.request.contextPath}/users/go?goToDate=${pageDates.firstDateOfWeek.plusDays(7)}" data-toggle="tooltip" title="Next week"><i class="fas fa-angle-right"></i></a></h2>
        </div>
        <div class="col-sm-3">
            <div class="dropdown show">
                <a class="btn btn-secondary dropdown-toggle" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Jump to date
                </a>
                <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/users/viewPlanner">Go to Today</a>
                    <a class="dropdown-item">
                        <FORM ACTION="${pageContext.request.contextPath}/users/go" METHOD="GET">
                            <label for="goToDate">Specific date:</label><br/><input type="date" NAME="goToDate" id="goToDate"><INPUT TYPE="SUBMIT" VALUE="Go" class="btn solidButton form-control" id="goToDateSubmit">
                        </FORM>
                    </a>
                </div>
            </div>
        </div>
    </div>
    <%--                If there's a message for the user, display it--%>
    <c:if test="${not empty sessionScope.userMessage}">
        <div class="row">
            <div class="col">
                <div class="userMessage alert ${sessionScope.messageClass}" role="alert">
                        ${sessionScope.userMessage}
                </div>
            </div>
        </div>
    </c:if>
    <c:remove var="userMessage"/>
    <c:remove var="messageClass"/>
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