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

@WebServlet("/users/login")
public class UserLoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("username") != null){
            resp.sendRedirect("/");
        }
        req.getRequestDispatcher("/WEB-INF/pages/users/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Map<String,User> users = (HashMap<String, User>) this.getServletContext().getAttribute("users");

        if(users.containsKey(username) && users.get(username).getPassword().equals(password)){
            req.getSession().setAttribute("username",username);
            req.getSession().setAttribute("role",users.get(username).getRole());
            resp.sendRedirect("/");
        } else {
            resp.sendRedirect("/users/login");
        }
    }
}
