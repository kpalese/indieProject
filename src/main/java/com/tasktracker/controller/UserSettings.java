package com.tasktracker.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Forwards the user to the user settings jsp
 * @author kpalese
 */

@WebServlet(
        urlPatterns = {"/users/userSettings"}
)
public class UserSettings extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Forward to the user settings jsp
        RequestDispatcher dispatcher = req.getRequestDispatcher("/users/userSettings.jsp");
        dispatcher.forward(req, resp);
    }
}
