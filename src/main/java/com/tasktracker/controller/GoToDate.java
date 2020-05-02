package com.tasktracker.controller;

import com.tasktracker.entity.PageDates;
import com.tasktracker.entity.User;
import com.tasktracker.persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;

@WebServlet(
        urlPatterns = {"/users/go"}
)
public class GoToDate extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //If the request is from the "jump to date" button, it will be an attribute
        //If the request is from a redirect after e.g. adding an event, it will be a parameter
        String goToDateString = "";
        if (req.getAttribute("goToDate") != null) {
            goToDateString = req.getAttribute("goToDate").toString();
        } else if (req.getParameter("goToDate") != null) {
            goToDateString = req.getParameter("goToDate");
        }

        //TODO: remove below
        System.out.println("From GoToDate servlet....goToDate is: " + goToDateString);

        //Get the date the user wants to navigate to
        LocalDate goToDate = LocalDate.parse(goToDateString);

        //Get the first date of the week the user wants to navigate to
        TemporalField fieldUS = WeekFields.of(Locale.US).dayOfWeek();
        //TODO: Add user setting for Sun or Mon start of week
        //TemporalField fieldUS = WeekFields.of(Locale.UK).dayOfWeek();
        LocalDate firstDateOfWeek = goToDate.with(fieldUS, 1);

        //Create PageDates entity to calculate the calendar dates for this page and place in the request
        PageDates newPageDates = new PageDates(firstDateOfWeek);
        req.setAttribute("pageDates", newPageDates);

        //TODO: Review further: I removed user from session and then re-added to force the user methods to be called again...is this the best way?
        //Get current user and add to session
        HttpSession session = req.getSession();
        GenericDao userDao = new GenericDao(User.class);
        List<User> users = userDao.getByPropertyEqual("userName", req.getRemoteUser());
        User user = users.get(0);
        //TODO: Verify list of users is only 1?
        session.setAttribute("user", user);

        //Forward to viewPlanner
        RequestDispatcher dispatcher = req.getRequestDispatcher("/users/viewPlanner.jsp");
        dispatcher.forward(req, resp);
    }
}
