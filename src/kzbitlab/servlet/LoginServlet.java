package kzbitlab.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kzbitlab.database.DBManager;
import kzbitlab.model.User;

import java.io.IOException;

@WebServlet(value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("login");
        String password = req.getParameter("password");
      if (email != null) {
        User user = DBManager.getUserByEmail(email);
        if (user != null && password.equals(user.getPassword())) {
            req.setAttribute("Full_name", user.getFullName());
            req.getRequestDispatcher("hello.jsp").forward(req, resp);
        } else {
            req.setAttribute("FAIL", "incorrect email or password!");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }

           }else { req.getRequestDispatcher("login.jsp").forward(req, resp);}
    }
}
