package com.tasktracker.controller;

import com.tasktracker.entity.Event;
import com.tasktracker.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * //TODO: COMMENT HERE
 * @author kpalese
 */

@WebServlet(
        urlPatterns = {"/users/editEventAction"}
)
public class EditEventAction extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());

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
        if (req.getParameter("endTime") !=null && req.getParameter("endTime") != "") {
            endTime = LocalTime.parse(req.getParameter("endTime"));
        }

        //Update event information in database
        eventToEdit.setName(req.getParameter("eventName"));
        eventToEdit.setDate(eventDate);
        eventToEdit.setStartTime(startTime);
        eventToEdit.setEndTime(endTime);
        eventToEdit.setNotes(req.getParameter("notes"));
        eventDao.saveOrUpdate(eventToEdit);

        //TODO: Message that event was successfully updated?

        resp.sendRedirect(req.getContextPath() + "/users/viewPlanner");
    }
}
