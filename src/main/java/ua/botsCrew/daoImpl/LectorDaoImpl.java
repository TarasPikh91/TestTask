package ua.botsCrew.daoImpl;

import ua.botsCrew.dao.DepartmentDao;
import ua.botsCrew.dao.LectorDao;
import ua.botsCrew.entity.Department;
import ua.botsCrew.entity.Lector;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class LectorDaoImpl implements LectorDao {

    private final EntityManager entityManager;

    public LectorDaoImpl(EntityManager entityManager) {
        super();
        this.entityManager = entityManager;
    }

    @Override
    public void save(Lector lector) {
        entityManager.getTransaction().begin();
        entityManager.persist(lector);
        entityManager.getTransaction().commit();
    }


    @Override
    public List<Lector> findAll() {
        entityManager.getTransaction().begin();
        List<Lector> lectors = entityManager.createQuery("from Lector").getResultList();
        entityManager.getTransaction().commit();
        return lectors;
    }

    @Override
    public Lector findOne(int id) {
        entityManager.getTransaction().begin();
        Lector lector = (Lector) entityManager.
                createQuery("select l from Lector l where l.id =:id")
                .setParameter("id", id)
                .getSingleResult();
        entityManager.getTransaction().commit();


        return lector;
    }
}
