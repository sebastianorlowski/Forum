package pl.orlowski.sebastian.util;

import pl.orlowski.sebastian.dao.UserDao;
import pl.orlowski.sebastian.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class LoggedFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        request.setCharacterEncoding("UTF-8");
        String login = request.getRemoteUser();
        if (login != null) {
            User u = (User)request.getSession().getAttribute("user");
            if (u != null) {
                UserDao userDao = (UserDao)request.getAttribute("userDao");
                u = userDao.getByLogin(login);
                request.getSession().setAttribute("user", u);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
