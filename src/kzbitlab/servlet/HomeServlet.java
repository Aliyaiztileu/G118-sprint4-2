package kzbitlab.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kzbitlab.database.DBManager;
import kzbitlab.model.City;
import kzbitlab.model.Item;
import kzbitlab.model.User;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User currentUser = (User) req.getSession().getAttribute("currentUser");
        if (currentUser == null){
            req.getRequestDispatcher("sign-in.jsp").forward(req,resp);
            return;
        }
        List<Item> items = DBManager.getItems();
        List<City> cities = DBManager.getCities();
        req.setAttribute("cities", cities);
        req.setAttribute("items", items);
        req.getRequestDispatcher("home.jsp").forward(req, resp);


    }


}
