package org.softuni.fdmc.servlets.orders;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/orders/all")
public class ViewOrdersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("role").equals("USER")){
            resp.sendRedirect("/");
        }

        req.getRequestDispatcher("/WEB-INF/pages/order/orders.jsp").forward(req,resp);
    }
}
