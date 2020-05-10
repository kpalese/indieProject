package com.tasktracker.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Forwards the user to the create account jsp
 * @author kpalese
 */

@WebServlet(
        urlPatterns = {"/createUserAccount"}
)
public class CreateUserAccount extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/createAccount.jsp");
        dispatcher.forward(req, resp);
    }
}
