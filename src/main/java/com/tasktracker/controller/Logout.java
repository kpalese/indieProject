package com.tasktracker.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Logs out the user and redirects to the homepage
 * @author kpalese
 */

@WebServlet(
        urlPatterns = {"/logout"}
)
public class Logout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        session.invalidate();

        resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }
}
