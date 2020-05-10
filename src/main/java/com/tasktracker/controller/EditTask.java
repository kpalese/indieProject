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
 * Forwards the user to the edit task jsp along with the task that is being edited
 */
@WebServlet(
        urlPatterns = {"/users/editTask"}
)
public class EditTask extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Get task to be edited
        int taskId = Integer.parseInt(req.getParameter("id"));
        GenericDao taskDao = new GenericDao(Task.class);
        Task taskToEdit = (Task)taskDao.getById(taskId);

        //Add task to the request
        req.setAttribute("taskToEdit", taskToEdit);

        //Add planner date that the user should return to after completing Edit Task workflow
        String goToDate = req.getParameter("goToDate");
        req.setAttribute("goToDate", goToDate);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/users/editTask.jsp");
        dispatcher.forward(req, resp);
    }
}
