package ua.botsCrew.dao;

import ua.botsCrew.entity.Lector;

import java.util.List;

public interface LectorDao {


    void save(Lector lector);
//    void save(Lector lector, List<Integer> departmentIds);
    List<Lector> findAll();
    Lector findOne(int id);
}
