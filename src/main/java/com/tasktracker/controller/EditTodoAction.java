package com.tasktracker.controller;

import com.tasktracker.entity.Todo;
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

/**
 * //TODO: COMMENT HERE
 * @author kpalese
 */

@WebServlet(
        urlPatterns = {"/users/editTodoAction"}
)
public class EditTodoAction extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        //Get to-do to be edited
        String todoId = req.getParameter("id");
        GenericDao todoDao = new GenericDao(Todo.class);
        Todo todoToEdit = (Todo)todoDao.getById(Integer.parseInt(todoId));

        //Update to-do information in database
        todoToEdit.setName(req.getParameter("todoName"));
        todoToEdit.setNotes(req.getParameter("notes"));
        todoDao.saveOrUpdate(todoToEdit);

        //Add message that to-do item was successfully edited
        session.setAttribute("userMessage", "The item was successfully updated!");
        session.setAttribute("messageClass", "alert-success");

        //Forward to viewPlanner
        RequestDispatcher dispatcher = req.getRequestDispatcher("/users/viewPlanner");
        dispatcher.forward(req, resp);
    }
}