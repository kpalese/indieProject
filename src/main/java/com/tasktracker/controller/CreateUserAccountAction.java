package com.tasktracker.controller;

import com.tasktracker.entity.Role;
import com.tasktracker.entity.User;
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
        urlPatterns = {"/createUserAccountAction"}
)
public class CreateUserAccountAction extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //private final Logger logger = LogManager.getLogger(this.getClass());

        //Check to see if user already exists?

        //Front end validation for passwords matching

        GenericDao userDao = new GenericDao(User.class);
        GenericDao roleDao = new GenericDao(Role.class);

        User user = new User();
        user.setUserName(req.getParameter("userName"));
        user.setUserPassword(req.getParameter("password"));
        //logger.debug("Adding User: " + user);
        Role role = new Role();
        role.setUser(user);
        role.setRoleName("user");
        role.setUserName(user.getUserName());

        user.addRole(role);

        userDao.insert(user);
        roleDao.insert(role);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/createAccountConfirmation.jsp");
        dispatcher.forward(req, resp);
    }
}
