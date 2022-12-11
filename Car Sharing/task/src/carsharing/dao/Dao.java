package carsharing.dao;

import java.util.List;

public interface Dao<T> {
    List<T> findAll();
    List<T> findByOwner(String ownerField, int id);
    boolean create(T obj);
    T findById(int id);
}
