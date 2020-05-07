package com.tasktracker.controller;

import com.calendarific.HolidaysItem;
import com.tasktracker.entity.PageDates;
import com.tasktracker.entity.User;
import com.tasktracker.persistence.CalendarificAPI;
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
    //TODO: break into smaller methods
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

        //Uncomment the below line to test New Year's Day
        //LocalDate now = LocalDate.of(2020, 1, 1);

        session.setAttribute("now", now);

        //Get the user setting for the first day of the week
        TemporalField field = getStartOfWeekSetting(user);
        LocalDate firstDateOfWeek = now.with(field, 1);

        //Create PageDates entity to calculate the calendar dates for this page and place in the request
        PageDates pageDates = new PageDates(firstDateOfWeek, field);
        req.setAttribute("pageDates", pageDates);

        //If user wants to include holidays, get the holidays for the current year (GoToDate servlet will get holidays
        // for other years if the user navigates to other years)
        if (user.isIncludeHolidays()) {
           includeHolidays(firstDateOfWeek, now, field, session);
        }

        //Forward to viewPlanner
        RequestDispatcher dispatcher = req.getRequestDispatcher("/users/viewPlanner.jsp");
        dispatcher.forward(req, resp);
    }


    /**
     * Gets user's start of week setting.
     *
     * @param user the user
     * @return the start of week setting
     */
    public TemporalField getStartOfWeekSetting(User user) {
        TemporalField field = null;

        if (user.getWeekStart().equals("Sunday")) {
            field = WeekFields.of(Locale.US).dayOfWeek();
        } else if (user.getWeekStart().equals("Monday")) {
            field = WeekFields.of(Locale.UK).dayOfWeek();
        }
        return field;
    }


    /**
     * Get the relevant holidays from Calendarific
     *
     * @param firstDateOfWeek the first date of week
     * @param now             the now
     * @param fieldUS         the field us
     * @param session         the session
     */
    public void includeHolidays(LocalDate firstDateOfWeek, LocalDate now, TemporalField fieldUS, HttpSession session) {
        try {
            int startingYear = firstDateOfWeek.getYear();
            int endingYear = now.with(fieldUS, 7).getYear();

            //If the current week spans two years, get the holidays for both years
            if (startingYear != endingYear) {

                CalendarificAPI api = new CalendarificAPI();
                List <HolidaysItem> retrievedHolidays = api.getCalendarificResponse(Integer.toString(startingYear)).getResponse().getHolidays();
                retrievedHolidays.addAll(api.getCalendarificResponse(Integer.toString(endingYear)).getResponse().getHolidays());

                //Put the retrieved holidays into the session and set the ending year as the current year
                session.setAttribute("currentYear", endingYear);
                session.setAttribute("holidays", retrievedHolidays);

            } else {
                //If the current week spans just one year, get the holidays for just this year
                int currentYear = now.getYear();
                CalendarificAPI api = new CalendarificAPI();
                List <HolidaysItem> retrievedHolidays = api.getCalendarificResponse(Integer.toString(currentYear)).getResponse().getHolidays();

                //Put the current date and the retrieved holidays into the session
                session.setAttribute("currentYear", currentYear);
                session.setAttribute("holidays", retrievedHolidays);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
