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
import java.io.IOException;
import java.util.List;

/**
 * Verifies that the username is unique and that the passwords match. Then creates a new user and a new role of type 'user'.
 * @author kpalese
 */

@WebServlet(
        urlPatterns = {"/createUserAccountAction"}
)
public class CreateUserAccountAction extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Check whether the user name already exists
        GenericDao userDao = new GenericDao(User.class);
        List<User> userNameMatches = userDao.getByPropertyEqual("userName", req.getParameter("userName"));

        //If user name already exists, set user message and return to the create account page
        if (userNameMatches.size() > 0) {
            req.setAttribute("userMessage", "This user name is unavailable. Please choose a different user name.");
            req.setAttribute("messageClass", "alert-danger");

            RequestDispatcher dispatcher = req.getRequestDispatcher("/createAccount.jsp");
            dispatcher.forward(req, resp);
        }   //If passwords do not match, set user message and return to the create account page
            else if (!req.getParameter("password").equals(req.getParameter("confirmPassword"))) {
                req.setAttribute("userMessage", "Please enter matching passwords.");
                req.setAttribute("messageClass", "alert-danger");

                //Send username back to form (so user doesn't need to re-enter)
                String enteredName = req.getParameter("userName");
                req.setAttribute("userName", enteredName);

                RequestDispatcher dispatcher = req.getRequestDispatcher("/createAccount.jsp");
                dispatcher.forward(req, resp);
            } //If user does not already exist, and if the passwords match, then create a new user
                else {
                    GenericDao roleDao = new GenericDao(Role.class);

                    User user = new User();
                    user.setUserName(req.getParameter("userName"));
                    user.setUserPassword(req.getParameter("password"));

                    logger.info("Adding User: " + user);

                    //Add the role 'user' to the new user
                    Role role = new Role();
                    role.setUser(user);
                    role.setRoleName("user");
                    role.setUserName(user.getUserName());

                    user.addRole(role);

                    userDao.insert(user);
                    roleDao.insert(role);

                    //Forward to the create account confirmation jsp
                    RequestDispatcher dispatcher = req.getRequestDispatcher("/createAccountConfirmation.jsp");
                    dispatcher.forward(req, resp);
                }
    }
}
