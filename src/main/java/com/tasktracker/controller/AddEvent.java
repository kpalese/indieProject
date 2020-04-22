package com.tasktracker.controller;

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
        logger.debug("****stringEventDate = " + stringEventDate);

        //Get user info and add event to user
        HttpSession session = req.getSession();

        //testing
        session.setAttribute("stringEventDate", stringEventDate);

        GenericDao userDao = new GenericDao(User.class);


        RequestDispatcher dispatcher = req.getRequestDispatcher("/users/addEvent.jsp");
        dispatcher.forward(req, resp);
    }
}
