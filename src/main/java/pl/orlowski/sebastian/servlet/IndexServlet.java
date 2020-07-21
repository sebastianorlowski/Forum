package pl.orlowski.sebastian.servlet;

import pl.orlowski.sebastian.dao.TopicDao;
import pl.orlowski.sebastian.entity.Topic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TopicDao dao = (TopicDao)req.getAttribute("topicDao");
        List<Topic> topics = dao.loadTopics();
        req.setAttribute("topics", topics);
        req.getRequestDispatcher("/web/view/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
