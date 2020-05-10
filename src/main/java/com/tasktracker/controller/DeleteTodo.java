package com.tasktracker.controller;

import com.tasktracker.entity.Todo;
import com.tasktracker.persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Forwards the user to the delete to do jsp along with the to do item to be deleted
 */
@WebServlet(
        urlPatterns = {"/users/deleteTodo"}
)
public class DeleteTodo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Get to-do to be deleted
        int todoId = Integer.parseInt(req.getParameter("id"));
        GenericDao todoDao = new GenericDao(Todo.class);
        Todo todoToDelete = (Todo)todoDao.getById(todoId);

        //Add to-do to the request
        req.setAttribute("todoToDelete", todoToDelete);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/users/deleteTodo.jsp");
        dispatcher.forward(req, resp);
    }
}
