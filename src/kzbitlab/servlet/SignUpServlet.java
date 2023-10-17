package kzbitlab.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kzbitlab.database.DBManager;
import kzbitlab.model.User;

import java.io.IOException;

@WebServlet ("/sign-up")
public class SignUpServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("user_email");
        String password = req.getParameter("user_password");
        String rePassword = req.getParameter("user_repassword");
        String fullName = req.getParameter("user_full_name");
        User user = DBManager.getUserByEmail(email);
        String  redirect = "/?regErrorEmail";
        if (user == null){
            redirect = "/?regErrorPasswords";
            if (password.equals(rePassword)){
                redirect = "/?success";
                user = new User();
                user.setEmail(email);
                user.setPassword(password);
                user.setFullName(fullName);
                DBManager.addUser(user);
            }
        }
        resp.sendRedirect(redirect);
    }
}
