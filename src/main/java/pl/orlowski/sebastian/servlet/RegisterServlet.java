package pl.orlowski.sebastian.servlet;

import pl.orlowski.sebastian.dao.UserDao;
import pl.orlowski.sebastian.entity.User;

import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/web/view/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String password2 = req.getParameter("password2");

        if (login != null && password != null && password.equals(password2) && !"".equals(password)) {
            UserDao dao = (UserDao)req.getAttribute("userDao");
            try {
                dao.getByLogin(login);
                req.setAttribute("Error", "Login is exist!");
                doGet(req, resp);
            } catch (NoResultException e) {
                User u = new User();
                u.setLogin(login);
                u.setPassword(password);
                if (dao.addUser(u)) {
                    resp.sendRedirect(req.getContextPath() + "/index");
                } else {
                    req.setAttribute("Error", "You cannot registration");
                        doGet(req, resp);

                    }
                }

            }
        }

    }

