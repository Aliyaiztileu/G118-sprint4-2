package kzbitlab.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kzbitlab.database.DBManager;
import kzbitlab.model.City;
import kzbitlab.model.Item;

import java.io.IOException;

@WebServlet("/edit-item")
public class EditItemServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("item_name");
        String description = req.getParameter("item_description");
        Double price = Double.parseDouble(req.getParameter("item_price"));
        Long cityId = Long.parseLong(req.getParameter("item_city_id"));

//        Item item = new Item();
//        item.setName(name);
//        item.setDescription(description);
//        item.setPrice(price);
//        City city = DBManager.getCityById(cityId);
//        item.setCity(city);

        DBManager.editItemById(id, name, description, price, cityId);
        resp.sendRedirect("/");
    }
}
