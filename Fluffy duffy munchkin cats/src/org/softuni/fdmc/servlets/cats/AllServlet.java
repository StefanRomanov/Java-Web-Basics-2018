package org.softuni.fdmc.servlets.cats;

import org.softuni.fdmc.data.Cat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;

@WebServlet("/cats/all")
public class AllServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(req.getSession().getAttribute("username") == null){
            resp.sendRedirect("/");
        }

        Map<String, Cat> catsMap = (Map<String, Cat>) this.getServletConfig().getServletContext().getAttribute("cats");

        Collection<Cat> cats = catsMap.values();

        req.setAttribute("cats", cats);

        req.getRequestDispatcher("/WEB-INF/pages/cats/all.jsp").forward(req, resp);
    }
}
