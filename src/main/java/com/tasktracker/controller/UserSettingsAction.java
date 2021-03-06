package com.tasktracker.controller;

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

/**
 * Updates the user settings in the database and then forwards the user to their planner
 * @author kpalese
 */

@WebServlet(
        urlPatterns = {"/users/userSettingsAction"}
)
public class UserSettingsAction extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Get user
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");

        //Get include holidays setting from the form
        Boolean includeHolidays = null;
        if (req.getParameter("holidayOptions").equals("yesHolidays")) {
            includeHolidays = true;
        } else if(req.getParameter("holidayOptions").equals("noHolidays")) {
            includeHolidays = false;
        }

        //Get start of week setting from the form
        String weekStart = req.getParameter("weekStart");

        //Update user settings
        GenericDao userDao = new GenericDao(User.class);
        user.setIncludeHolidays(includeHolidays);
        user.setWeekStart(weekStart);
        userDao.saveOrUpdate(user);

        //Set a message that the user setting were updated
        session.setAttribute("userMessage", "Your user settings have been updated!");
        session.setAttribute("messageClass", "alert-success");

        //Forward to viewPlanner
        RequestDispatcher dispatcher = req.getRequestDispatcher("/users/viewPlanner");
        dispatcher.forward(req, resp);
    }
}
