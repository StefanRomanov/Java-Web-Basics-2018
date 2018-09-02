package org.softuni.fdmc.servlets.users;

import org.softuni.fdmc.data.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/users/register")
public class UserRegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("username") != null){
            resp.sendRedirect("/");
        }
        req.getRequestDispatcher("/WEB-INF/pages/users/register.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String repPassword = req.getParameter("repPassword");
        String role = req.getParameter("role");

        Map<String,User> users = (HashMap<String, User>)this.getServletContext().getAttribute("users");


        if(!password.equals(repPassword) || users.containsKey(username)){
            resp.sendRedirect("/users/register");
        } else {
            User user = new User(username,password, role);
            users.put(username,user);
            this.getServletContext().setAttribute("users",users);
            resp.sendRedirect("/users/login");
        }
    }
}
