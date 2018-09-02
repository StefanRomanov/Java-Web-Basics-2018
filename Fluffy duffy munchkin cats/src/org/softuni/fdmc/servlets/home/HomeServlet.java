package org.softuni.fdmc.servlets.home;

import org.softuni.fdmc.data.Cat;
import org.softuni.fdmc.data.Order;
import org.softuni.fdmc.data.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet("/")
public class HomeServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        this.getServletContext().setAttribute("cats",new HashMap<String,Cat>());
        this.getServletContext().setAttribute("users",new HashMap<String,User>());
        this.getServletContext().setAttribute("orders",new ArrayList<Order>());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(req,resp);
    }
}