package com.tasktracker.controller;

import com.tasktracker.entity.Todo;
import com.tasktracker.persistence.GenericDao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(
        urlPatterns = {"/users/deleteTodoAction"}
)
public class DeleteTodoAction extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();

        //Get to-do to be deleted
        String todoId = req.getParameter("id");
        GenericDao todoDao = new GenericDao(Todo.class);
        Todo todoToDelete = (Todo) todoDao.getById(Integer.parseInt(todoId));

        //Delete to-do
        todoDao.delete(todoToDelete);

        session.setAttribute("userMessage", "The item was successfully deleted!");
        session.setAttribute("messageClass", "alert-success");

        resp.sendRedirect(req.getContextPath() + "/users/viewPlanner");
    }
}
