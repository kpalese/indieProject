package com.tasktracker.controller;

import com.tasktracker.entity.PageDates;
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
import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;

@WebServlet(
        urlPatterns = {"/users/prev"}
)
public class PreviousWeek extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Get the first date of the previous page and parse to LocalDate
        String prevPageFirstDateString = req.getParameter("firstDate");
        LocalDate prevPageFirstDate = LocalDate.parse(prevPageFirstDateString);

        //Calculate first date of the current week
        LocalDate firstDateOfWeek = prevPageFirstDate.minusDays(7);

        //Create PageDates entity to calculate the calendar dates for this page and place in the request
        PageDates pageDates = new PageDates(firstDateOfWeek);
        req.setAttribute("pageDates", pageDates);

        //Forward to viewPlanner
        RequestDispatcher dispatcher = req.getRequestDispatcher("/users/viewPlanner.jsp");
        dispatcher.forward(req, resp);
    }
}
