package pl.orlowski.sebastian.dao;

import pl.orlowski.sebastian.entity.Inscription;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class InscriptionDao {
    private EntityManager em;

    public InscriptionDao(EntityManager em) {
        this.em = em;
    }

    public boolean addInscription(Inscription i) {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.persist(i);
            et.commit();
            return true;
        } catch (Exception e) {
            et.rollback();
            e.printStackTrace();
            return false;
        }
    }


}
