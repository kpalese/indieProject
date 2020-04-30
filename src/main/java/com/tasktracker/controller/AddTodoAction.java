package com.tasktracker.controller;

import com.tasktracker.entity.Todo;
import com.tasktracker.entity.User;
import com.tasktracker.persistence.GenericDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * //TODO: COMMENT HERE
 * @author kpalese
 */

@WebServlet(
        urlPatterns = {"/users/addTodoAction"}
)
public class AddTodoAction extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();

        //Get user
        User user = (User)session.getAttribute("user");

        //Create a to-do object and insert into database
        Todo todo = new Todo(req.getParameter("todoName"), req.getParameter("notes"), user);
        GenericDao todoDao = new GenericDao(Todo.class);
        todoDao.insert(todo);

        //TODO: Message that todo was successfully added?

        resp.sendRedirect(req.getContextPath() + "/users/viewPlanner");
    }
}
