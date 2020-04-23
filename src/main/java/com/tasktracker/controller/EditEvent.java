package com.tasktracker.controller;

import com.tasktracker.entity.Event;
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

@WebServlet(
        urlPatterns = {"/users/editEvent"}
)
public class EditEvent extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Get event to be edited
        int eventId = Integer.parseInt(req.getParameter("id"));
        GenericDao eventDao = new GenericDao(Event.class);
        Event eventToEdit = (Event)eventDao.getById(eventId);

        //Add event to the session
        HttpSession session = req.getSession();
        session.setAttribute("eventToEdit", eventToEdit);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/users/editEvent.jsp");
        dispatcher.forward(req, resp);
    }
}
