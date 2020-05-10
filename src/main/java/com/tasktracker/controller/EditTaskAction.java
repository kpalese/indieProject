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
 * //TODO: COMMENT HERE
 * @author kpalese
 */

@WebServlet(
        urlPatterns = {"/users/editTaskAction"}
)
public class EditTaskAction extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        //Get task to be edited
        String taskId = req.getParameter("id");
        GenericDao taskDao = new GenericDao(Task.class);
        Task taskToEdit = (Task)taskDao.getById(Integer.parseInt(taskId));

        //Convert date variable from String
        LocalDate taskDate = LocalDate.parse(req.getParameter("taskDate"));

        //If a weekly task, get day of week it should repeat on
        String dayOfWeek = "";
        if (req.getParameter("frequency").equals("weekly")) {
            dayOfWeek = taskDate.getDayOfWeek().toString();
        }

        //Update task information in database
        taskToEdit.setName(req.getParameter("taskName"));
        taskToEdit.setDate(taskDate);
        taskToEdit.setFrequency(req.getParameter("frequency"));
        taskToEdit.setWeeklyTaskDayOfWeek(dayOfWeek);
        taskToEdit.setNotes(req.getParameter("notes"));
        taskDao.saveOrUpdate(taskToEdit);

        //Add message that task was successfully edited
        session.setAttribute("userMessage", "The task was successfully updated!");
        session.setAttribute("messageClass", "alert-success");


        //Set the date that the user should return to
        String goToDate = req.getParameter("goToDate");
        req.removeAttribute("goToDate");
        req.setAttribute("goToDate", goToDate);

        //Forward to viewPlanner via GoToDate servlet
        RequestDispatcher dispatcher = req.getRequestDispatcher("/users/go");
        dispatcher.forward(req, resp);
    }
}
