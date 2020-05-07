package com.tasktracker.controller;

import com.calendarific.HolidaysItem;
import com.tasktracker.entity.PageDates;
import com.tasktracker.entity.User;
import com.tasktracker.persistence.CalendarificAPI;
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
    //TODO: break into smaller methods
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        //If the request is from the "jump to date" button, it will be an attribute
        //If the request is from a redirect after e.g. adding an event, it will be a parameter
        String goToDateString = "";
        if (req.getAttribute("goToDate") != null) {
            goToDateString = req.getAttribute("goToDate").toString();
        } else if (req.getParameter("goToDate") != null) {
            goToDateString = req.getParameter("goToDate");
        }

        //Get the date the user wants to navigate to
        LocalDate goToDate = LocalDate.parse(goToDateString);

        //Get the user setting for the first day of the week
        TemporalField field = getStartOfWeekSetting(session);
        LocalDate firstDateOfWeek = goToDate.with(field, 1);

        //Create PageDates entity to calculate the calendar dates for this page and place in the request
        PageDates newPageDates = new PageDates(firstDateOfWeek, field);
        req.setAttribute("pageDates", newPageDates);

        //TODO: Review further: I removed user from session and then re-added to force the user methods to be called again...is this the best way?
        //Get current user and add to session
        GenericDao userDao = new GenericDao(User.class);
        List<User> users = userDao.getByPropertyEqual("userName", req.getRemoteUser());
        User user = users.get(0);
        //TODO: Verify list of users is only 1?
        session.setAttribute("user", user);

        //Include holidays if user setting is to include them
        if (user.isIncludeHolidays()) {
            includeHolidays(firstDateOfWeek, goToDate, field, session);
        }

        //Forward to viewPlanner
        RequestDispatcher dispatcher = req.getRequestDispatcher("/users/viewPlanner.jsp");
        dispatcher.forward(req, resp);
    }

    /**
     * Gets user's start of week setting.
     *
     * @param session the HttpSession
     * @return the start of week setting
     */
    public TemporalField getStartOfWeekSetting(HttpSession session) {
        User user = (User)session.getAttribute("user");

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
     * @param goToDate        the go to date
     * @param fieldUS         the field us
     * @param session         the session
     */
    public void includeHolidays(LocalDate firstDateOfWeek, LocalDate goToDate, TemporalField fieldUS, HttpSession session) {
        try {
            int startingYear = firstDateOfWeek.getYear();
            int endingYear = goToDate.with(fieldUS, 7).getYear();
            int currentYear = goToDate.getYear();

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
                //Check if the current year's holidays are already in the session
                if (currentYear != (int)session.getAttribute("currentYear")) {
                    CalendarificAPI api = new CalendarificAPI();
                    List <HolidaysItem> retrievedHolidays = api.getCalendarificResponse(Integer.toString(currentYear)).getResponse().getHolidays();

                    //Put the current date and the retrieved holidays into the session
                    session.setAttribute("currentYear", currentYear);
                    session.setAttribute("holidays", retrievedHolidays);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
