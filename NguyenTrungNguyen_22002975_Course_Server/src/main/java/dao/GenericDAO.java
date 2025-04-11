package dao;

import java.util.List;

public interface GenericDAO<T, ID> {
    boolean save(T t);
    boolean update(T t);
    boolean delete(ID id);
    T findById(ID id);
    List<T> getAll();
}
