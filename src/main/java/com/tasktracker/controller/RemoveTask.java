package com.tasktracker.controller;

import com.tasktracker.entity.Task;
import com.tasktracker.persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Sends the user to the remove task jsp alond with the task to be removed
 * @author Kelly Palese
 */
@WebServlet(
        urlPatterns = {"/users/removeTask"}
)
public class RemoveTask extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Get task to be removed
        int taskId = Integer.parseInt(req.getParameter("id"));
        GenericDao taskDao = new GenericDao(Task.class);
        Task taskToRemove = (Task)taskDao.getById(taskId);

        //Add task to the request
        req.setAttribute("taskToRemove", taskToRemove);
        req.setAttribute("removeDate", req.getParameter("removeDate"));

        //Forward to the remove task jsp
        RequestDispatcher dispatcher = req.getRequestDispatcher("/users/removeTask.jsp");
        dispatcher.forward(req, resp);
    }
}
