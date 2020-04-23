package com.tasktracker.controller;

import com.tasktracker.entity.Event;
import com.tasktracker.entity.PageDates;
import com.tasktracker.entity.User;
import com.tasktracker.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * //TODO: COMMENT HERE
 * @author kpalese
 */

@WebServlet(
        urlPatterns = {"/users/viewPlanner"}
)
public class ViewPlanner extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("****User is: " + req.getRemoteUser() + " and is user is " + req.isUserInRole("user"));

        HttpSession session = req.getSession();

        //Get current user
        GenericDao userDao = new GenericDao(User.class);
        List<User> users = userDao.getByPropertyEqual("userName", req.getRemoteUser());
        User user = users.get(0);
        //TODO: Verify list of users is only 1?
        session.setAttribute("user", user);

        //Get current date and then first date of the week
        LocalDate now = LocalDate.now();
        TemporalField fieldUS = WeekFields.of(Locale.US).dayOfWeek();
        LocalDate firstDateOfWeek = now.with(fieldUS, 1);

        //Create PageDates entity to calculate the calendar dates for this page and place in the session
        PageDates pageDates = new PageDates(firstDateOfWeek);
        req.setAttribute("pageDates", pageDates);



//        //Get date of first day of week, format it, and set as attribute
//        TemporalField fieldUS = WeekFields.of(Locale.US).dayOfWeek();
//        LocalDate firstDateOfWeek = now.with(fieldUS, 1);
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
//        String formattedFirstDateOfWeek = dtf.format(firstDateOfWeek);
//        session.setAttribute("firstDateOfWeek", formattedFirstDateOfWeek);
//
//        //Get last date of week and set attribute
//        LocalDate lastDateOfWeek = now.with(fieldUS, 7);
//        String formattedLastDateOfWeek = dtf.format(lastDateOfWeek);
//        session.setAttribute("lastDateOfWeek", formattedLastDateOfWeek);


        getEvents(firstDateOfWeek, session);

        //Forward to viewPlanner
        RequestDispatcher dispatcher = req.getRequestDispatcher("/users/viewPlanner.jsp");
        dispatcher.forward(req, resp);
    }


    /**
     *
     */
    public void getEvents(LocalDate firstDateOfWeek, HttpSession session) {
        GenericDao eventDao = new GenericDao(Event.class);

        //Get the events for each day of the week
        List<Event> eventsDay1 = eventDao.getByPropertyEqual("date", firstDateOfWeek);
        List<Event> eventsDay2 = eventDao.getByPropertyEqual("date", firstDateOfWeek.plusDays(1));

        //Sort the events by start time
        Collections.sort(eventsDay1);
        Collections.sort(eventsDay2);


        //Put the events into the session
        session.setAttribute("eventsDay1", eventsDay1);
        session.setAttribute("eventsDay2", eventsDay2);
    }

}
