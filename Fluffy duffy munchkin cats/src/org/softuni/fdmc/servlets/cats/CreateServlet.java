package org.softuni.fdmc.servlets.cats;

import org.softuni.fdmc.data.Cat;
import org.softuni.fdmc.data.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/cats/create")
public class CreateServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("username") == null || !req.getSession().getAttribute("role").equals("ADMIN")){
            resp.sendRedirect("/");
        }

        req.getRequestDispatcher("/WEB-INF/pages/cats/create.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String breed = req.getParameter("breed");
        String color = req.getParameter("color");
        int legs = Integer.parseInt(req.getParameter("legs"));

        Map<String,User> users = (HashMap<String, User>)req.getServletContext().getAttribute("users");

        User creator = users.get(req.getSession().getAttribute("username"));

        Cat cat = new Cat(name, breed, color, legs, creator);

        Map<String, Cat> cats = (Map<String, Cat>) this.getServletConfig().getServletContext().getAttribute("cats");

        cats.putIfAbsent(name, cat);

        this.getServletConfig().getServletContext().setAttribute("cats", cats);

        resp.sendRedirect("/cats/profile?catName=" + cats.get(name).getName());
    }
}
