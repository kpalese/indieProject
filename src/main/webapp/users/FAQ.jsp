<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="title" value="FAQ - Task Tracker" />
<%@include file="../templates/head.jsp"%>

<html>
<body class="geometric">
<%@include file="../templates/navmenu.jsp"%>

<main>
    <div class="wrapper mainForm" id="faq">
        <h2>Frequently Asked Questions</h2>

        <h3 id="eventFAQ">What is an Event?</h3>
        <p>An Event has a date and a start time. It may or may not have an end time.</p>
        <p><em>Examples of events:</em> Work meetings, medical or personal appointments, social commitments</p><br/>

        <h3 id="taskFAQ">What is a Task?</h3>
        <p>A task has a date but does not have a time associated with it. A task can occur once, daily, or weekly.</p>
        <p><em>Examples of one-time tasks:</em> Pick up a prescription, accomplish a milestone in a project</p>
        <p><em>Examples of daily tasks:</em> Daily fitness goals, daily reminders to take a medication</p>
        <p><em>Examples of weekly tasks:</em> Watering a plant every Wednesday, meal prepping every Sunday, weekly cleaning tasks</p><br/>

        <h3 id="todoFAQ">What is a To Do item?</h3>
        <p>A to do item is something you need to accomplish, but it does not have a set deadline.</p>
        <p><em>Examples of to do items:</em> Return an item to a store, update your resume, stain the deck, send thank you notes</p><br/>

        <h3 id="recurTaskSingleInstance">Why can't I remove a single instance of a recurring task?</h3>
        <p>Placeholder</p><br/>

        <h3 id="futureRecurTask">Why can't I remove future recurring tasks?</h3>
        <p>Placeholder</p><br/>

        <h3>What might be included in future versions of Task Tracker?</h3>
        <p>Some goals for future versions include:</p>
        <ul>
            <li>All day events</li>
            <li>Recurring events</li>
            <li>View removed tasks and to do items</li>
            <li>Share your planner with another user (i.e. give someone else view access to your calendar)</li>
            <li>Password encryption</li>
            <li>Drag and drop a to do item to make it become a task for a particular day</li>
            <li>A habit tracker to log progress towards goals</li>
        </ul>

    </div>
</main>
</body>
</html>