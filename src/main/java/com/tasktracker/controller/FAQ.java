package com.tasktracker.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Forwards the user to the FAQ jsp
 * @author Kelly Palese
 */
@WebServlet(
        urlPatterns = {"/users/faq"}
)
public class FAQ extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Forward user to the faq jsp
        RequestDispatcher dispatcher = req.getRequestDispatcher("/users/FAQ.jsp");
        dispatcher.forward(req, resp);
    }
}
