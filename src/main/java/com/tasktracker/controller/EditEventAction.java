package com.tasktracker.controller;

import com.tasktracker.entity.Event;
import com.tasktracker.persistence.GenericDao;

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
 * Updates the event in the database and then forwards to the user's planner
 * @author kpalese
 */

@WebServlet(
        urlPatterns = {"/users/editEventAction"}
)
public class EditEventAction extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Get event to be edited
        String eventId = req.getParameter("id");
        GenericDao eventDao = new GenericDao(Event.class);
        Event eventToEdit = (Event)eventDao.getById(Integer.parseInt(eventId));

        //Convert user-entered date and time variables from Strings
        LocalDate eventDate = LocalDate.parse(req.getParameter("eventDate"));

        LocalTime startTime = LocalTime.parse(req.getParameter("startTime"));
        LocalTime endTime = null;
        if (req.getParameter("endTime") !=null && !req.getParameter("endTime").equals("")) {
            endTime = LocalTime.parse(req.getParameter("endTime"));
        }

        //Update event information in database
        eventToEdit.setName(req.getParameter("eventName"));
        eventToEdit.setDate(eventDate);
        eventToEdit.setStartTime(startTime);
        eventToEdit.setEndTime(endTime);
        eventToEdit.setNotes(req.getParameter("notes"));
        eventDao.saveOrUpdate(eventToEdit);

        //Add message that event was successfully edited
        HttpSession session = req.getSession();
        session.setAttribute("userMessage", "The event was successfully updated!");
        session.setAttribute("messageClass", "alert-success");

        //Set the planner date to return the user to
        String goToDate = eventDate.toString();
        req.removeAttribute("goToDate");
        req.setAttribute("goToDate", goToDate);

        //Forward to viewPlanner via GoToDate servlet
        RequestDispatcher dispatcher = req.getRequestDispatcher("/users/go");
        dispatcher.forward(req, resp);
    }
}
