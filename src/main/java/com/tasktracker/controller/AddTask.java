package com.tasktracker.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Gets the task date from the request, and then forwards the user to the add task jsp
 * @author kpalese
 */

@WebServlet(
        urlPatterns = {"/users/addTask"}
)
public class AddTask extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Get date of task
        String taskDate = req.getParameter("taskDate");

        //Send task date to addTask jsp
        req.setAttribute("taskDate", taskDate);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/users/addTask.jsp");
        dispatcher.forward(req, resp);
    }
}
