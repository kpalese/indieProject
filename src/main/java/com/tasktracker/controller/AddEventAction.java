package com.tasktracker.controller;

import com.tasktracker.entity.Event;
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

/**
 * This servlet gets the event information entered by the user, inserts the event into the database, and forwards the user
 * to the calendar week that they were on previously
 * @author kpalese
 */

@WebServlet(
        urlPatterns = {"/users/addEventAction"}
)
public class AddEventAction extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Get user
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");

        //Convert date and time variables from Strings
        LocalDate eventDate = LocalDate.parse(req.getParameter("eventDate"));
        LocalTime startTime = LocalTime.parse(req.getParameter("startTime"));
        LocalTime endTime = null;
        if (req.getParameter("endTime") !=null && !req.getParameter("endTime").equals("")) {
            endTime = LocalTime.parse(req.getParameter("endTime"));
        }

        //Create event object and insert into database
        Event event = new Event(req.getParameter("eventName"), eventDate, startTime, endTime, req.getParameter("notes"), user);
        GenericDao eventDao = new GenericDao(Event.class);
        int id = eventDao.insert(event);
        logger.info("Inserted event id: {}", id);

        //Add message that event was successfully added
        session.setAttribute("userMessage", "The event was successfully added!");
        session.setAttribute("messageClass", "alert-success");

        //Set the planner date to return the user to
        String goToDate = eventDate.toString();
        req.setAttribute("goToDate", goToDate);

        //Forward to viewPlanner via GoToDate servlet
        RequestDispatcher dispatcher = req.getRequestDispatcher("/users/go");
        dispatcher.forward(req, resp);
    }
}
