package pl.orlowski.sebastian.servlet;

import pl.orlowski.sebastian.dao.TopicDao;
import pl.orlowski.sebastian.entity.Topic;
import pl.orlowski.sebastian.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

@WebServlet("/newTopic")
public class NewTopicServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/web/view/newTopic.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String text = req.getParameter("text");
        if (!"".equals(title) && !"".equals(text)) {
            Timestamp t = new Timestamp(new Date().getTime());
            User loggedIn = (User)req.getSession().getAttribute("user");
            TopicDao topicDao = (TopicDao)req.getSession().getAttribute("topicDao");
            Topic topic = new Topic();
            topic.setTimestamp(t);
            topic.setTitle(title);
            topic.setText(text);
            topic.setUser(loggedIn);
            if (topicDao.addTopic(topic)) {
                resp.sendRedirect(req.getContextPath() + "/topic?id=" + topic.getId());
            }
        }
    }
}
