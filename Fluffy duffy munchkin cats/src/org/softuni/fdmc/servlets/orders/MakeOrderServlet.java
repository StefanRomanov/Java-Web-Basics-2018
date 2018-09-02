package org.softuni.fdmc.servlets.orders;

import org.softuni.fdmc.data.Cat;
import org.softuni.fdmc.data.Order;
import org.softuni.fdmc.data.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/makeOrder")
public class MakeOrderServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        if(req.getSession().getAttribute("role").equals("ADMIN")){
            resp.sendRedirect("/");
        }

        Map<String, User> users = (HashMap<String, User>)req.getServletContext().getAttribute("users");
        Map<String, Cat> cats = (HashMap<String, Cat>)req.getServletContext().getAttribute("cats");
        List<Order> orders = (List<Order>) req.getServletContext().getAttribute("orders");

        User user = users.get(req.getSession().getAttribute("username"));
        Cat cat = cats.get(req.getQueryString().split("=")[1]);

        orders.add(new Order(user,cat));

        resp.sendRedirect("/cats/all");
    }
}
