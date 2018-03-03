package ua.botsCrew.daoImpl;

import ua.botsCrew.dao.DepartmentDao;
import ua.botsCrew.entity.Department;

import javax.persistence.EntityManager;
import java.util.List;

public class DepartmentDaoImpl implements DepartmentDao {

    private final EntityManager entityManager;

    public DepartmentDaoImpl(EntityManager entityManager){
        super();
        this.entityManager = entityManager;
    }

    @Override
    public void save(Department department) {
        entityManager.getTransaction().begin();
        entityManager.persist(department);
        entityManager.getTransaction().commit();

    }

    @Override
    public List<Department> findAll() {
        entityManager.getTransaction().begin();
        List<Department> departments = entityManager.createQuery("from Department").getResultList();
        entityManager.getTransaction().commit();

        return departments;
    }

    @Override
    public Department findOne(int id) {
        entityManager.getTransaction().begin();
        Department department = (Department) entityManager.createQuery("select d from Department d where d.id =:id")
                .setParameter("id", id)
                .getSingleResult();
        entityManager.getTransaction().commit();

        return department;
    }
}
