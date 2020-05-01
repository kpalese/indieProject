package com.tasktracker.controller;

import com.tasktracker.entity.Task;
import com.tasktracker.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(
        urlPatterns = {"/users/removeTaskAction"}
)
public class RemoveTaskAction extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //Get task to be removed
        String taskId = req.getParameter("id");
        GenericDao taskDao = new GenericDao(Task.class);
        Task taskToRemove = (Task) taskDao.getById(Integer.parseInt(taskId));

        //If it's a 'once' task, delete it
        if (req.getParameter("frequency").equals("once")) {
            taskDao.delete(taskToRemove);
        }

        //If it's a recurring task, determine which instances the user wants to remove
        if (!req.getParameter("frequency").equals("once")) {
            if (req.getParameter("instances").equals("onlyThis")) {
                LocalDate removeDate = LocalDate.parse(req.getParameter("removeDate"));
                taskToRemove.setLastDateCompleted(removeDate);
                taskDao.saveOrUpdate(taskToRemove);

            } else if (req.getParameter("instances").equals("allInstances")) {
                taskDao.delete(taskToRemove);
            }
        }

        //TODO: Message that task was successfully updated?

        resp.sendRedirect(req.getContextPath() + "/users/viewPlanner");
    }
}
