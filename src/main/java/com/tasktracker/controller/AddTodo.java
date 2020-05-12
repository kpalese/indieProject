package com.tasktracker.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Forwards the user to the add to do page
 * @author kpalese
 */

@WebServlet(
        urlPatterns = {"/users/addTodo"}
)
public class AddTodo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Get return date (so user returns to correct week in planner)
        String returnDate = req.getParameter("returnDate");

        //Send return date to addTask jsp
        req.setAttribute("returnDate", returnDate);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/users/addTodo.jsp");
        dispatcher.forward(req, resp);
    }
}
