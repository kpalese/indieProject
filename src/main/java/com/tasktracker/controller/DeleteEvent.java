package com.tasktracker.controller;

import com.tasktracker.entity.Event;
import com.tasktracker.persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Forwards the user to the delete event jsp along with the event to be deleted
 */
@WebServlet(
        urlPatterns = {"/users/deleteEvent"}
)
public class DeleteEvent extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Get event to be deleted
        int eventId = Integer.parseInt(req.getParameter("id"));
        GenericDao eventDao = new GenericDao(Event.class);
        Event eventToDelete = (Event)eventDao.getById(eventId);

        //Add event to the request
        req.setAttribute("eventToDelete", eventToDelete);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/users/deleteEvent.jsp");
        dispatcher.forward(req, resp);
    }
}
