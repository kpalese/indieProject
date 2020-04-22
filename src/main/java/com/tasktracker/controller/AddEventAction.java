package com.tasktracker.controller;

import com.tasktracker.entity.Event;
import com.tasktracker.entity.Role;
import com.tasktracker.entity.User;
import com.tasktracker.persistence.GenericDao;
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
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * //TODO: COMMENT HERE
 * @author kpalese
 */

@WebServlet(
        urlPatterns = {"/users/addEventAction"}
)
public class AddEventAction extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        //Get user
        User user = (User)session.getAttribute("user");

        //Convert date and time variables from Strings
        LocalDate eventDate = LocalDate.parse(req.getParameter("eventDate"));
        LocalTime startTime = LocalTime.parse(req.getParameter("startTime"));
        LocalTime endTime = LocalTime.parse(req.getParameter("endTime"));

        //Create event object and insert into database
        Event event = new Event(req.getParameter("eventName"), eventDate, startTime, endTime, req.getParameter("notes"), user);
        GenericDao eventDao = new GenericDao(Event.class);
        eventDao.insert(event);

        //TODO: Message that event was successfully added?

        resp.sendRedirect(req.getContextPath() + "/users/viewPlanner.jsp");
    }
}
