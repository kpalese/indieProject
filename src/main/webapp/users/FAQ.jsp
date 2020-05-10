<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="title" value="FAQ - Task Tracker" />
<%@include file="../templates/head.jsp"%>

<html>
<body class="geometric">
<%@include file="../templates/navmenu.jsp"%>

<main>
    <div class="wrapper mainForm">
        <h2>Frequently Asked Questions</h2>

        <h3>What is considered an Event?</h3>
        <p>An Event has a date and a start time. It may or may not have an end time.</p>
        <p>Examples: Work meetings, medical or personal appointments, social commitments</p><br/>

        <h3>What is considered a Task?</h3>
        <p>A task has a date but does not have a time associated with it. A task can occur once, daily, or weekly.</p>
        <p>Examples of one-time tasks: Pick up a prescription, accomplish a milestone in a project</p>
        <p>Examples of daily tasks: Daily fitness goals, daily reminders to take a medication</p>
        <p>Examples of weekly tasks: Watering a plant every Wednesday, meal prepping every Sunday, weekly cleaning tasks</p><br/>

        <h3>What is considered a To Do item?</h3>
        <p>A to do item is something you need to accomplish, but it does not have a set deadline.</p>
        <p>Examples of to do items: Return an item to a store, update your resume, stain the deck, send thank you notes</p>

        <h3>Why can't I remove a single instance of a recurring task?</h3>
        <p>Placeholder</p><br/>

        <h3>Why can't I remove future recurring tasks?</h3>
        <p>Placeholder</p><br/>

        <h3>What might be included in future version of Task Tracker?</h3>
        <p>Placeholder...all day events, recurring events</p>

    </div>
</main>
</body>
</html>