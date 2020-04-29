package com.tasktracker.controller;

import com.tasktracker.entity.Task;
import com.tasktracker.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(
        urlPatterns = {"/users/completeTaskAction"}
)
public class CompleteTaskAction extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //Get task to be completed
        String taskId = req.getParameter("id");
        GenericDao taskDao = new GenericDao(Task.class);
        Task taskToComplete = (Task) taskDao.getById(Integer.parseInt(taskId));

        //Get the completed date
        LocalDate completedDate = LocalDate.parse(req.getParameter("date"));

        //Update task information in database
        taskToComplete.setLastDateCompleted(completedDate);
        taskDao.saveOrUpdate(taskToComplete);

        resp.sendRedirect(req.getContextPath() + "/users/viewPlanner");
    }
}
