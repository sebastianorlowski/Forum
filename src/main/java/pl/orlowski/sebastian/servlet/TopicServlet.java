package pl.orlowski.sebastian.servlet;

import pl.orlowski.sebastian.dao.InscriptionDao;
import pl.orlowski.sebastian.dao.TopicDao;
import pl.orlowski.sebastian.entity.Inscription;
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

@WebServlet("/topic")
public class TopicServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String stringId = req.getParameter("id");
        if (stringId != null) {
            int id = Integer.parseInt(stringId);
            TopicDao topicDao = (TopicDao) req.getAttribute("topic");
            Topic t = topicDao.getTopic(id);
            req.setAttribute("topic", t);
            req.getRequestDispatcher("/web/view/topic.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String text = req.getParameter("text");
        String stringId = req.getParameter("id");
        if (text != null && stringId != null) {
            int id = Integer.parseInt(stringId);
            InscriptionDao inscriptionDao = (InscriptionDao)req.getAttribute("inscriptionDao");
            TopicDao topicDao = (TopicDao)req.getAttribute("topicDao");
            User loggedIn = (User)req.getSession().getAttribute("user");
            Topic topic = topicDao.getTopic(id);
            Inscription inscription = new Inscription();
            inscription.setTimestamp(new Timestamp(new Date().getTime()));
            inscription.setText(text);
            inscription.setTopic(topic);
            inscription.setUser(loggedIn);
            inscriptionDao.addInscription(inscription);
        }
        doGet(req, resp);
    }
}
