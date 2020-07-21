package pl.orlowski.sebastian.dao;

import pl.orlowski.sebastian.entity.Topic;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class TopicDao {

    private EntityManager em;

    public TopicDao(EntityManager em) {
        this.em = em;
    }

    public List<Topic> getTopics() {
        return (List<Topic>) this.em.createQuery("SELECT t from Topic t").getResultList();
    }

    public Topic getTopic(int id) {
        this.em.clear();
        return this.em.find(Topic.class, id);
    }

    public boolean addTopic(Topic t) {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.persist(t);
            et.commit();
            return true;
        } catch (Exception e) {
            et.rollback();
            e.printStackTrace();
            return false;
        }
    }

}
