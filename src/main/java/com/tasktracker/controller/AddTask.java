package com.tasktracker.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * //TODO: COMMENT HERE
 * @author kpalese
 */

@WebServlet(
        urlPatterns = {"/users/addTask"}
)
public class AddTask extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());

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
