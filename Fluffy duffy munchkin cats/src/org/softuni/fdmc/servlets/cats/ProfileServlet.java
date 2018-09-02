package org.softuni.fdmc.servlets.cats;

import org.softuni.fdmc.data.Cat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/cats/profile")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(req.getSession().getAttribute("username") == null){
            resp.sendRedirect("/");
        }

        Map<String, Cat> catsMap = (Map<String, Cat>) this.getServletConfig().getServletContext().getAttribute("cats");

        String queryString = req.getQueryString();

        String name = queryString.split("=")[1];

        Cat neededCat = catsMap.get(name);

        neededCat.setViews(neededCat.getViews() + 1);

        req.setAttribute("cat", neededCat);
        req.setAttribute("catName", name);

        req.getRequestDispatcher("/WEB-INF/pages/cats/profile.jsp").forward(req, resp);
    }
}
