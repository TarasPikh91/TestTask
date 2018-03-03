package ua.botsCrew.dao;

import ua.botsCrew.entity.Department;

import java.util.List;

public interface DepartmentDao {

        void save(Department department);
        List<Department> findAll();
        Department findOne(int id);
}
