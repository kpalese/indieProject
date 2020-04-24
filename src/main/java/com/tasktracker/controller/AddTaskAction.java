package com.tasktracker.controller;

import com.tasktracker.entity.Task;
import com.tasktracker.entity.User;
import com.tasktracker.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

        //Create task object and insert into database
        //If frequency is once
        if (req.getParameter("frequency").equals("Once")) {
            Task task = new Task(req.getParameter("taskName"), taskDate, req.getParameter("frequency"), req.getParameter("notes"), user);
            GenericDao taskDao = new GenericDao(Task.class);
            taskDao.insert(task);
        }






        //If frequency is daily/weekly...



        //TODO: Message that event was successfully added?

        resp.sendRedirect(req.getContextPath() + "/users/viewPlanner");
    }
}
