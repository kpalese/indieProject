package com.tasktracker.controller;

import com.tasktracker.entity.PageDates;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Locale;

@WebServlet(
        urlPatterns = {"/users/go"}
)
public class GoToDate extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Get the date the user wants to navigate to
        String goToDateString = req.getParameter("goToDate");
        LocalDate goToDate = LocalDate.parse(goToDateString);

        //Get the first date of the week the user wants to navigate to
        TemporalField fieldUS = WeekFields.of(Locale.US).dayOfWeek();
        //TODO: Add user setting for Sun or Mon start of week
        //TemporalField fieldUS = WeekFields.of(Locale.UK).dayOfWeek();
        LocalDate firstDateOfWeek = goToDate.with(fieldUS, 1);

        //Create PageDates entity to calculate the calendar dates for this page and place in the request
        PageDates pageDates = new PageDates(firstDateOfWeek);
        req.setAttribute("pageDates", pageDates);

        //Forward to viewPlanner
        RequestDispatcher dispatcher = req.getRequestDispatcher("/users/viewPlanner.jsp");
        dispatcher.forward(req, resp);
    }
}
