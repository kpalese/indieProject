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
 * Forwards the user to the edit event jsp along with the event to be edited
 */
@WebServlet(
        urlPatterns = {"/users/editEvent"}
)
public class EditEvent extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Get event to be edited
        int eventId = Integer.parseInt(req.getParameter("id"));
        GenericDao eventDao = new GenericDao(Event.class);
        Event eventToEdit = (Event)eventDao.getById(eventId);

        //Add event to the request
        req.setAttribute("eventToEdit", eventToEdit);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/users/editEvent.jsp");
        dispatcher.forward(req, resp);
    }
}
