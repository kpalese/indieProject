package com.tasktracker.controller;

import com.tasktracker.entity.Todo;
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
 * Inserts the new to-do item into the database and then forwards the user back to view their planner
 * @author kpalese
 */

@WebServlet(
        urlPatterns = {"/users/addTodoAction"}
)
public class AddTodoAction extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        //Get user
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");

        //Create a to-do object and insert into database
        Todo todo = new Todo(req.getParameter("todoName"), req.getParameter("notes"), user);
        GenericDao todoDao = new GenericDao(Todo.class);
        int id = todoDao.insert(todo);
        logger.info("Inserted todo item id: {}", id);

        //Add message that item was successfully added
        session.setAttribute("userMessage", "The item was successfully added!");
        session.setAttribute("messageClass", "alert-success");

        //Set the planner date to return the user to
        String goToDate = req.getParameter("returnDate");
        req.setAttribute("goToDate", goToDate);

        //Forward to viewPlanner via GoToDate servlet
        RequestDispatcher dispatcher = req.getRequestDispatcher("/users/go");
        dispatcher.forward(req, resp);
    }
}
