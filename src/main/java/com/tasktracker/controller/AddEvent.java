package com.tasktracker.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This servlet forwards the user to the Add Event jsp, and sends the expected date of the event along with the request.
 * @author kpalese
 */

@WebServlet(
        urlPatterns = {"/users/addEvent"}
)
public class AddEvent extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Get date of event
        String eventDate = req.getParameter("eventDate");

        //Send event date to addEvent jsp
        req.setAttribute("eventDate", eventDate);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/users/addEvent.jsp");
        dispatcher.forward(req, resp);
    }
}
