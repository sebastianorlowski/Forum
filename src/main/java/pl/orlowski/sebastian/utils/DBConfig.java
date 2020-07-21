package pl.orlowski.sebastian.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class DBConfig implements ServletContextListener {

    private static EntityManagerFactory emf;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        emf = Persistence.createEntityManagerFactory("Forum");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }

    public static EntityManager createEntityManager() {
        if (emf != null) {
            return createEntityManager();
        } else {
            return null;
        }
    }
}
