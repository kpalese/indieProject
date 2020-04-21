package com.tasktracker.controller;

import com.tasktracker.entity.User;
import com.tasktracker.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Local;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
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
        GenericDao userDao = new GenericDao(User.class);

        List<User> users = userDao.getByPropertyEqual("userName", req.getRemoteUser());
        User user = users.get(0);

        //TODO: Verify list of users is only 1?

        session.setAttribute("user", user);

        //Get current date
        LocalDateTime now = LocalDateTime.now();

        //Get date of first day of week
        TemporalField fieldUS = WeekFields.of(Locale.US).dayOfWeek();
        LocalDateTime firstDateOfWeek = now.with(fieldUS, 1);

        //Format date
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formattedFirstDateOfWeek = dtf.format(firstDateOfWeek);

        //Set first date of week attribute
        session.setAttribute("firstDateOfWeek", formattedFirstDateOfWeek);

        //Get last date of week and set attribute
        LocalDateTime lastDateOfWeek = now.with(fieldUS, 7);
        String formattedLastDateOfWeek = dtf.format(lastDateOfWeek);
        session.setAttribute("lastDateOfWeek", formattedLastDateOfWeek);

        //Set the days of the week
        setDaysOfWeek(firstDateOfWeek, session);

        //Set shorthand dates
        setShorthandDates(firstDateOfWeek, session);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/users/viewPlanner.jsp");
        dispatcher.forward(req, resp);
    }

    /**
     * //TODO: Comments!
     * @param firstDateOfWeek
     * @param session
     */
    public void setDaysOfWeek(LocalDateTime firstDateOfWeek, HttpSession session) {
        //Get the days of the week
        DayOfWeek firstDayOfWeek = firstDateOfWeek.getDayOfWeek();
        DayOfWeek secondDayOfWeek = firstDateOfWeek.plusDays(1).getDayOfWeek();

        //Set the days of the week as session attributes
        session.setAttribute("firstDayOfWeek", firstDayOfWeek);
        session.setAttribute("secondDayOfWeek", secondDayOfWeek);
    }


    /**
     * //TODO: Comments!
     *
     * @param firstDateOfWeek the first date of week
     * @param session         the session
     */
    public void setShorthandDates(LocalDateTime firstDateOfWeek, HttpSession session) {
        //Define a format for the shorthand dates
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd");

        //Get the shorthand dates of the week
        String shorthandFirstDateOfWeek = dtf.format(firstDateOfWeek);
        String shorthandSecondDateOfWeek = dtf.format(firstDateOfWeek.plusDays(1));

        //Set the shorthand dates of the week as session attributes
        session.setAttribute("shorthandFirstDateOfWeek", shorthandFirstDateOfWeek);
        session.setAttribute("shorthandSecondDateOfWeek", shorthandSecondDateOfWeek);
    }

}
