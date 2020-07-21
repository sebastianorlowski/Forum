package pl.orlowski.sebastian.utils;

import pl.orlowski.sebastian.dao.InscriptionDao;
import pl.orlowski.sebastian.dao.TopicDao;
import pl.orlowski.sebastian.dao.UserDao;

import javax.persistence.EntityManager;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class InitiatorDB implements ServletRequestListener {

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {

    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        EntityManager em = DBConfig.createEntityManager();
        TopicDao topicDao = new TopicDao(em);
        UserDao userDao = new UserDao(em);
        InscriptionDao inscriptionDao = new InscriptionDao(em);
        ServletRequest request = sre.getServletRequest();
        request.setAttribute("TopicDao", topicDao);
        request.setAttribute("UserDao", userDao);
        request.setAttribute("InscriptionDao", inscriptionDao);


    }
}
