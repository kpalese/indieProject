package com.tasktracker.controller;

import com.tasktracker.entity.Event;
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
import java.io.IOException;

@WebServlet(
        urlPatterns = {"/users/editTask"}
)
public class EditTask extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Get task to be edited
        int taskId = Integer.parseInt(req.getParameter("id"));
        GenericDao taskDao = new GenericDao(Task.class);
        Task taskToEdit = (Task)taskDao.getById(taskId);

        //Add task to the request
        req.setAttribute("taskToEdit", taskToEdit);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/users/editTask.jsp");
        dispatcher.forward(req, resp);
    }
}
