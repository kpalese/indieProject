package com.tasktracker.controller;

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
import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.*;

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
        logger.info("****User is: " + req.getRemoteUser() + " and user role is " + req.isUserInRole("user"));

        HttpSession session = req.getSession();

        //Get current user and add to session
        GenericDao userDao = new GenericDao(User.class);
        List<User> users = userDao.getByPropertyEqual("userName", req.getRemoteUser());
        User user = users.get(0);
        //TODO: Verify list of users is only 1?
        session.setAttribute("user", user);

        //Get current date and first date of the week
        LocalDate now = LocalDate.now();
        TemporalField fieldUS = WeekFields.of(Locale.US).dayOfWeek();
        //TODO: Add user setting for Sun or Mon start of week
        //TemporalField fieldUS = WeekFields.of(Locale.UK).dayOfWeek();
        LocalDate firstDateOfWeek = now.with(fieldUS, 1);

        //Create PageDates entity to calculate the calendar dates for this page and place in the request
        PageDates pageDates = new PageDates(firstDateOfWeek);
        req.setAttribute("pageDates", pageDates);

        //Forward to viewPlanner
        RequestDispatcher dispatcher = req.getRequestDispatcher("/users/viewPlanner.jsp");
        dispatcher.forward(req, resp);
    }
}
