package com.tasktracker.controller;

import com.tasktracker.entity.User;
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

/**
 * //TODO: COMMENT HERE
 * @author kpalese
 */

@WebServlet(
        urlPatterns = {"/users/userSettings"}
)
public class UserSettings extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Get user's current settings
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");
        Boolean includeHolidays = user.isIncludeHolidays();

        req.setAttribute("includeHolidays", includeHolidays);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/users/userSettings.jsp");
        dispatcher.forward(req, resp);
    }
}
