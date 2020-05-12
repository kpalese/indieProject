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
        <p>A task has a specific date but can occur at any time on that date. A task can occur once, daily, or weekly.</p>
        <p><em>Examples of one-time tasks:</em> Pick up a prescription, accomplish a milestone in a project</p>
        <p><em>Examples of daily tasks:</em> Daily fitness goals, daily reminders to take a medication</p>
        <p><em>Examples of weekly tasks:</em> Watering a plant every Wednesday, meal prepping every Sunday, weekly cleaning tasks</p><br/>

        <h3 id="todoFAQ">What is a To Do item?</h3>
        <p>A to do item is something you need to accomplish, but it does not have a set deadline.</p>
        <p><em>Examples of to do items:</em> Return an item to a store, update your resume, stain the deck, send thank you notes</p><br/>

        <h3 id="recurTaskSingleInstance">Why can't I remove a single instance of a recurring task?</h3>
        <p>Recurring tasks are meant to be actions or goals that you must regularly accomplish. When you remove an instance
            of a recurring task, you're essentially saying <em>I'm up-to-date as of this point in time!</em> Therefore, when you
            remove an instance of your recurring task, all past instances are also removed.</p><br/>

        <h3 id="futureRecurTask">Why can't I remove future recurring tasks?</h3>
        <p>Recurring tasks are meant to be things that you must regularly accomplish. You're never exactly <b>done</b> with them
            because you will need to complete them again the next day or the next week. Therefore, you can only complete
            a recurring task in the present or the past.</p>
        <p>For example, a weekly task for Wednesdays cannot be completed the week before or the day before. Your goal is to do the
            task <b>every Wednesday</b>. However, you can complete that Wednesday task on Wednesday or Thursday or Friday, or
            anytime after your goal date.</p>
        <p>If you want to complete a task "early", you should probably make the task a to do item instead. To do items do
            not have associated dates.</p>
        <p>You can always remove future <b>single occurrence</b> tasks.</p><br/>

        <h3>What might be included in future versions of Task Tracker?</h3>
        <p>Some goals for future versions include:</p>
        <ul>
            <li>All day events</li>
            <li>Recurring events</li>
            <li>View removed tasks and to do items</li>
            <li>Share your planner with another user (i.e. give someone else view access to your calendar)</li>
            <li>Password encryption</li>
            <li>Drag and drop a to do item to make it become a task for a particular day</li>
        </ul>
    </div>
</main>
</body>
</html>