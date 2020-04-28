package com.tasktracker.controller;

import com.tasktracker.entity.Task;
import com.tasktracker.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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

        //TODO: Message that task was successfully updated?

        resp.sendRedirect(req.getContextPath() + "/users/viewPlanner");
    }
}
