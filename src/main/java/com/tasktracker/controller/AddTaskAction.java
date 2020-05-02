package com.tasktracker.controller;

import com.tasktracker.entity.Task;
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

/**
 * //TODO: COMMENT HERE
 * @author kpalese
 */

@WebServlet(
        urlPatterns = {"/users/addTaskAction"}
)
public class AddTaskAction extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        //Get user
        User user = (User)session.getAttribute("user");

        //Convert date variable from String
        LocalDate taskDate = LocalDate.parse(req.getParameter("taskDate"));

        //If a weekly task, get day of week it should repeat on
        String dayOfWeek = "";
        if (req.getParameter("frequency").equals("weekly")) {
           dayOfWeek = taskDate.getDayOfWeek().toString();
        }

        //Create a Task object and insert into database
        Task task = new Task(req.getParameter("taskName"), taskDate, req.getParameter("frequency"), dayOfWeek, req.getParameter("notes"), null, user);
        GenericDao taskDao = new GenericDao(Task.class);
        taskDao.insert(task);

        //Add message that event was successfully added
        session.setAttribute("userMessage", "The task was successfully added!");
        session.setAttribute("messageClass", "alert-success");

        //Set the planner date to return the user to
        String goToDate = taskDate.toString();
        req.setAttribute("goToDate", goToDate);

        //Forward to viewPlanner via GoToDate servlet
        RequestDispatcher dispatcher = req.getRequestDispatcher("/users/go");
        dispatcher.forward(req, resp);
    }
}
