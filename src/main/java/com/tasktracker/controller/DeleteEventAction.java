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

@WebServlet(
        urlPatterns = {"/users/deleteEventAction"}
)
public class DeleteEventAction extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Get event to be deleted
        String eventId = req.getParameter("id");
        GenericDao eventDao = new GenericDao(Event.class);
        Event eventToDelete = (Event) eventDao.getById(Integer.parseInt(eventId));

        //Delete event
        eventDao.delete(eventToDelete);

        //TODO: Message that event was successfully deleted?

        resp.sendRedirect(req.getContextPath() + "/users/viewPlanner");
    }
}
