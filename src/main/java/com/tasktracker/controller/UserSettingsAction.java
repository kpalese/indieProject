package com.tasktracker.controller;

import com.tasktracker.entity.Task;
import com.tasktracker.entity.User;
import com.tasktracker.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;

/**
 * //TODO: COMMENT HERE
 * @author kpalese
 */

@WebServlet(
        urlPatterns = {"/users/userSettingsAction"}
)
public class UserSettingsAction extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Get user
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");

        //Get include holidays setting from the form
        Boolean includeHolidays = null;
        if (req.getParameter("includeHolidays").equals("yesHolidays")) {
            includeHolidays = true;
        } else if(req.getParameter("includeHolidays").equals("noHolidays")) {
            includeHolidays = false;
        }

        //Update user settings
        GenericDao userDao = new GenericDao(User.class);
        user.setIncludeHolidays(includeHolidays);
        userDao.saveOrUpdate(user);

        //TODO: Message that settings were successfully updated?

        resp.sendRedirect(req.getContextPath() + "/users/viewPlanner");
    }
}
