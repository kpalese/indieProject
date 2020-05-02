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
import java.time.LocalDate;

@WebServlet(
        urlPatterns = {"/users/deleteEventAction"}
)
public class DeleteEventAction extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        //Get event to be deleted
        String eventId = req.getParameter("id");
        GenericDao eventDao = new GenericDao(Event.class);
        Event eventToDelete = (Event) eventDao.getById(Integer.parseInt(eventId));

        //Get the event date so that user can return to the proper week in the planner
        LocalDate eventDate = eventToDelete.getDate();

        //Delete event
        eventDao.delete(eventToDelete);

        session.setAttribute("userMessage", "The event was successfully deleted!");
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
