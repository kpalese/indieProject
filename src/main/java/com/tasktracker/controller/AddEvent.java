package com.tasktracker.controller;

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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * //TODO: COMMENT HERE
 * @author kpalese
 */

@WebServlet(
        urlPatterns = {"/users/addEvent"}
)
public class AddEvent extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Get date of event
        String stringEventDate = req.getParameter("eventDate");

        //Format date of event to LocalDate
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate eventDate = LocalDate.parse(stringEventDate, dtf);


        //Send event date to addEvent jsp
        HttpSession session = req.getSession();
        session.setAttribute("eventDate", eventDate);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/users/addEvent.jsp");
        dispatcher.forward(req, resp);
    }
}
