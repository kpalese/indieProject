package com.tasktracker.controller;

import com.tasktracker.entity.Task;
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
 * Deletes "once" tasks from database. If it is a recurring task, checks whether user selected to remove all instances
 * or only past instances. If user selected all instances, task is removed from database. If user selected past instances,
 * then the last completed date is updated to the date on which the user removed the task.
 * @author Kelly Palese
 */
@WebServlet(
        urlPatterns = {"/users/removeTaskAction"}
)
public class RemoveTaskAction extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        //Get task to be removed
        String taskId = req.getParameter("id");
        GenericDao taskDao = new GenericDao(Task.class);
        Task taskToRemove = (Task) taskDao.getById(Integer.parseInt(taskId));

        //If it's a 'once' task, delete it
        if (req.getParameter("frequency").equals("once")) {
            taskDao.delete(taskToRemove);
        }

        //If it's a recurring task, determine which instances the user wants to remove
        logger.info("Frequency of task {} to be removed is {}", taskId, req.getParameter("frequency"));
        if (!req.getParameter("frequency").equals("once")) {
            if (req.getParameter("instances").equals("onlyThis")) {
                LocalDate removeDate = LocalDate.parse(req.getParameter("removeDate"));
                taskToRemove.setLastDateCompleted(removeDate);
                taskDao.saveOrUpdate(taskToRemove);

            } else if (req.getParameter("instances").equals("allInstances")) {
                taskDao.delete(taskToRemove);
            }
        }

        //Set a success message for the user
        session.setAttribute("userMessage", "The task was successfully removed!");
        session.setAttribute("messageClass", "alert-success");

        //Get the remove date so user can return to the proper week
        String removeDate = req.getParameter("removeDate");
        req.removeAttribute("goToDate");
        req.setAttribute("goToDate", removeDate);

        //Forward to viewPlanner via GoToDate servlet
        RequestDispatcher dispatcher = req.getRequestDispatcher("/users/go");
        dispatcher.forward(req, resp);
    }
}
